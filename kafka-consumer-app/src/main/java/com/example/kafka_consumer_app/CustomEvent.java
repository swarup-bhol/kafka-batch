package com.example.kafka_consumer_app;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CustomEvent {
    private String id;
    private String eventType;
    private Long timestamp;

    private Map<String, Object> customFields = new HashMap<>();

    public CustomEvent() {
    }

    public CustomEvent(String id, String eventType, Long timestamp) {
        this.id = id;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    @JsonAnySetter
    public void addCustomField(String key, Object value) {
        customFields.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getCustomFields() {
        return customFields;
    }




    @Override
    public String toString() {
        return "CustomEvent{" +
                "id='" + id + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                ", customFields=" + customFields +
                '}';
    }
}

