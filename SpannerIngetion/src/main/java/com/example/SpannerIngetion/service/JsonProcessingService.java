package com.example.SpannerIngetion.service;


import com.example.SpannerIngetion.entity.User;
import com.example.SpannerIngetion.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class JsonProcessingService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final ExecutorService executorService;
    private final int batchSize;
    private final int maxRecords;
    private final String filePath;

    @Autowired
    public JsonProcessingService(UserRepository userRepository,
                                 ObjectMapper objectMapper,
                                 @Value("${app.thread-pool.size}") int threadPoolSize,
                                 @Value("${app.batch.size}") int batchSize,
                                 @Value("${app.max-records}") int maxRecords,
                                 @Value("${app.json.file-path}") String filePath) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.batchSize = batchSize;
        this.maxRecords = maxRecords;
        this.filePath = filePath;
    }

    public void processJsonToSpanner() {
        try {
            log.info("Starting JSON processing...");
            long startTime = System.currentTimeMillis();

            // Read JSON file
            File file = new File(filePath);
            List<User> allUsers = objectMapper.readValue(file, new TypeReference<List<User>>() {});

            // Limit to maxRecords if needed
            if (maxRecords > 0 && allUsers.size() > maxRecords) {
                allUsers = allUsers.subList(0, maxRecords);
            }

            AtomicInteger counter = new AtomicInteger(0);
            int totalBatches = (int) Math.ceil((double) allUsers.size() / batchSize);

            log.info("Processing {} users in {} batches of {} each", allUsers.size(), totalBatches, batchSize);

            // Process in batches
            for (int i = 0; i < allUsers.size(); i += batchSize) {
                int end = Math.min(i + batchSize, allUsers.size());
                List<User> batch = allUsers.subList(i, end);

                // Submit batch to thread pool
                CompletableFuture.runAsync(() -> {
                    int batchNumber = counter.incrementAndGet();
                    long batchStart = System.currentTimeMillis();

                    try {
                        userRepository.saveAll(batch);
                        long batchEnd = System.currentTimeMillis();
                        log.info("Batch {}/{} processed ({} users) in {} ms",
                                batchNumber, totalBatches, batch.size(), (batchEnd - batchStart));
                    } catch (Exception e) {
                        log.error("Error processing batch {}: {}", batchNumber, e.getMessage(), e);
                    }
                }, executorService);

                // Rate limiting - sleep to achieve ~10k records per second
                if (i > 0 && i % (batchSize * 5) == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // Shutdown executor and wait for completion
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }

            long endTime = System.currentTimeMillis();
            log.info("Processing completed in {} seconds", (endTime - startTime) / 1000);

        } catch (IOException e) {
            log.error("Error reading JSON file: {}", e.getMessage(), e);
        }
    }
}
