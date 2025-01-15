package raf.draft.dsw.model.messages;


import java.time.LocalDateTime;

public class Message {

    private String finalMessage;
    private MessageType type;
    private LocalDateTime timestamp;

    public Message(String message, MessageType type, LocalDateTime timestamp) {
        this.finalMessage = message;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public MessageType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
