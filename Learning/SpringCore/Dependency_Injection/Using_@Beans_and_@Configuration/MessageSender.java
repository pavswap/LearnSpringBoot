package com.pavswap.LearningSpringBoot.DI;

public class MessageSender{

    private final Message m;

    public MessageSenderIMPLI(Message m) {
        this.m = m;
    }
    @Override
    public String sendMessage() {
        return m.say();
    }
}
