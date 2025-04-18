package com.example.kafka_producer_app;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;

@Component
public class MessageGenerator {

    private final KafkaProducerService producerService;
    private final Random random = new Random();
    private final ExecutorService executor = Executors.newFixedThreadPool(10); // tune this based on CPU cores

    public MessageGenerator(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

//    @PostConstruct
    public void startGenerating() {
        Runnable task = () -> {
            while (true) {
                for (int i = 0; i < 10_000; i++) {
                    executor.submit(() -> {
                        UserEvent event = new UserEvent(
                                UUID.randomUUID().toString(),
                                randomAction(),
                                System.currentTimeMillis()
                        );
//                        producerService.sendUserEvent(event);
                    });
                }
                try {
                    TimeUnit.SECONDS.sleep(1); // throttle to 10k per second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        new Thread(task).start(); // non-blocking background producer
    }

    private String randomAction() {
        String[] actions = {"LOGIN", "LOGOUT", "PURCHASE", "CLICK"};
        return actions[random.nextInt(actions.length)];
    }
}
