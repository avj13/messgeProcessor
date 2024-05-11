package org.example;

import java.util.concurrent.*;

public class MessageProcessorFramework {

    private static int NUM_THREADS = 0;
    private static int threadTimeout = 1; //second

    private final BlockingQueue<Message> messageQueue;
    private final MessageProcessorInterface messageProcessor;

    private final ExecutorService executorService;

    public MessageProcessorFramework(int initalThreads, int queueSize, MessageProcessorInterface messageProcessor) {
        NUM_THREADS = initalThreads;
        this.messageProcessor = messageProcessor;
        this.messageQueue = new LinkedBlockingQueue<>(queueSize);
        this.executorService = Executors.newFixedThreadPool(NUM_THREADS);
    }

    public void start() {

        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.submit(() -> {
                while (true) {
                    try {
                        Message message = messageQueue.poll(threadTimeout, TimeUnit.SECONDS);
                        if (message!= null) {
                            long currentTime = System.currentTimeMillis();
                            if (currentTime - message.getTimeTillExpiry() < 0) { // Check if TTL has not expired
                                messageProcessor.process(message); // Custom Logic to process
                            } else {
                                System.out.println("Skipping expired message: " + message.getInput());
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    public void stop() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public void enqueueMessage(Message message) {
        messageQueue.offer(message); //true if possible to add else, false
        // custom queue blocking handling logic can be added here.
    }



}
