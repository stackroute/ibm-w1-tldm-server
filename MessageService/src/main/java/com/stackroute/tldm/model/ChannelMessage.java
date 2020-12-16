package com.stackroute.tldm.model;

import java.util.Date;
import java.util.UUID;

// Model for Channel-Message.

public class ChannelMessage {

    private UUID messageId;
    private String messageContent;
    private User sender;
    private Channel channel;
    private Date timestamp;

    public ChannelMessage() {
        super();
    }

    public ChannelMessage(UUID messageId, String messageContent, User sender, Channel channel, Date timestamp) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.sender = sender;
        this.channel = channel;
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

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChannelMessage{" +
                "messageId=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                ", sender=" + sender +
                ", channel=" + channel +
                ", timestamp=" + timestamp +
                '}';
    }
}
