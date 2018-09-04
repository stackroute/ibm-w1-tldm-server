package com.stackroute.tldm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
public class Message {

    @Id
    private String id;
    private String messageContent;
    private Sender sender;
    private Receiver receiver;
    private Date createdAt;

    public Message() {
    }

    public Message(String id, String messageContent, Sender sender, Receiver receiver, Date createdAt) {
        this.id = id;
        this.messageContent = messageContent;
        this.sender = sender;
        this.receiver = receiver;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", createdAt=" + createdAt +
                '}';
    }
}
