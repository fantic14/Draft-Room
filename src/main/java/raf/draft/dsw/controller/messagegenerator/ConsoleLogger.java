package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.model.messages.Message;

public class ConsoleLogger implements Logger {

    private Message message;

    public ConsoleLogger() {
    }

    @Override
    public void log() {
        System.out.println(message.getFinalMessage());
    }

    @Override
    public void update(Object data) {
        if (data instanceof Message)
            this.message = (Message) data;
        log();
    }

    public Message getMessage() {
        return message;
    }
}
