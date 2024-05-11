package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        MessageProcessorInterface messageProcessor = new PrintMessageProcessor();
        MessageProcessorFramework framework = new MessageProcessorFramework(4, 1000, messageProcessor);

        framework.start();

        // Produce messages
        for (int i = 0; i < 10; i++) {
            framework.enqueueMessage( new Message("Message " + i, 10000)); // 1 second TTL
        }

        // Stop the framework after some time
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        framework.stop();
    }
}