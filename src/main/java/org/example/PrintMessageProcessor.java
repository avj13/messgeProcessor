package org.example;

public class PrintMessageProcessor implements MessageProcessorInterface{

    @Override
    public void process(Message message) {
        System.out.println("Processing message: " + message.getInput() );//+ ".\tTime to Live: " + message.getTimeTillExpiry());
    }
}
