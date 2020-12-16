package com.stackroute.tldm.model;


import com.datastax.driver.core.DataType;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("ChannelMessage")
public class ChannelMessage {

    @PrimaryKey
    @CassandraType(type = DataType.Name.TEXT)
    private UUID messageId;
    @CassandraType(type = DataType.Name.TEXT)
    private String messageContent;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "user")
    private User sender;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "Channel")
    private Channel channel;
    @CassandraType(type = DataType.Name.TIMESTAMP)
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
