package com.example.kafka_consumer_app;


public class UserEvent {
    private String userId;
    private String action;
    private long timestamp;

    public UserEvent() {
        // No-args constructor required by Jackson
    }

    public UserEvent(String userId, String action, long timestamp) {
        this.userId = userId;
        this.action = action;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "userId='" + userId + '\'' +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
