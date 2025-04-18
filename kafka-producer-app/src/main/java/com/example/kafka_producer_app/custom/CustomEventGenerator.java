package com.example.kafka_producer_app.custom;


import com.example.kafka_producer_app.KafkaProducerConfig;
import com.example.kafka_producer_app.KafkaProducerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CustomEventGenerator {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private final Random random = new Random();

    private final String[] baseActions = {"LOGIN", "LOGOUT", "CLICK", "VIEW"};

    @PostConstruct
    public void startGenerating() {
        Runnable task = () -> {
            while (true) {
                for (int i = 0; i < 10; i++) {
                    CustomEvent event = new CustomEvent(
                            UUID.randomUUID().toString(),
                            baseActions[random.nextInt(baseActions.length)],
                            System.currentTimeMillis()
                    );
                    // Add 1–5 random custom fields
                    int fieldCount = 1 + random.nextInt(5);
                    for (int j = 0; j < fieldCount; j++) {
                        event.addCustomField("field_" + j, "value_" + random.nextInt(100));
                    }
                    kafkaProducerService.send(event);
                }

                try {
                    TimeUnit.SECONDS.sleep(1); // wait 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        new Thread(task).start(); // run in background
    }
}

