package com.stackroute.tldm.model;

import java.util.Date;
import java.util.UUID;

// Model for User-Message.

/*
 * The class "Message" will be acting as the data model for the Message Table in the database. 
 * Java object to recreate it as a table in your database.
 */

public class Message {

    private UUID messageId;
    private String messageContent;
    private User sender;
    private User receiver;
    private Date timestamp;
    /*
     * This class should have five fields
     * (messageId, messageContent, sender, receiver, timestamp)
     * This class should
	 * also contain the getters and setters for the fields,
	 * parameterized constructor and toString method.
     */

    public Message() {
        super();
    }

    public Message(UUID messageId, String messageContent, User sender, User receiver, Date timestamp) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", timestamp=" + timestamp +
                '}';
    }
}
