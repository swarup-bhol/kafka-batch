package com.example.kafka_consumer_app;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaUserEventListener {

  int count = 0;
//    @KafkaListener(topics = "user-events", groupId = "user-event-group", containerFactory = "kafkaListenerContainerFactory")
//    public void listen(List<UserEvent> events) {
//        for (UserEvent event : events) {
//            System.out.println("Consumed event: " + event);
//        }
//    }
    @KafkaListener(topics = "custom-events", groupId = "custom-event-group", containerFactory = "kafkaListenerContainerFactoryForCustom")
    public void consume(CustomEvent event) {
        count++;
        System.out.println("🎯 Received: " + event);
    }

    @Scheduled(fixedRate = 5000)
    public void print(){
        System.out.println("No of Events : "+count);
    }



}