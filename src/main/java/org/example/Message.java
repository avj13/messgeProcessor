package org.example;

public class Message {
    private final String input;
    private final long timeTillExpiry;

    public Message(String input, long processingTime) {
        this.input = input;
        timeTillExpiry = System.currentTimeMillis() + processingTime;
    }

    public String getInput() {
        return input;
    }

    public long getTimeTillExpiry() {
        return timeTillExpiry;
    }
}
