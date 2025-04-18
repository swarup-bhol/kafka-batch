package com.example.kafka_producer_app;

import com.example.kafka_producer_app.custom.CustomEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Value("${kafka.topic.name:test-topic}")
    private String topicName;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserEvent(UserEvent event) {
        kafkaTemplate.send(topicName, event); // ✅ no manual JSON
        System.out.println("Sent: " + event);
    }

    public void send(CustomEvent events) {
        kafkaTemplate.send("custom-events", events.getId(), events);
    }


}
