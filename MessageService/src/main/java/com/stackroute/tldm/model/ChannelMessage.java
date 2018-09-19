package com.stackroute.tldm.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ChannelMessage {
	@Id
	private UUID messageId;
	private String messageContent;
	private User sender;
	private Channel channel;
	private Date createdAt;

	public ChannelMessage() {

	}

	public ChannelMessage(UUID messageId, String messageContent, User sender, Channel channel,
			Date createdAt) {
		super();
		this.messageId = messageId;
		this.messageContent = messageContent;
		this.sender = sender;
		this.channel = channel;
		this.createdAt = createdAt;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ChannelMessage [messageId=" + messageId + ", messageContent=" + messageContent + ", sender=" + sender
				+ ", channel=" + channel + ", createdAt=" + createdAt + "]";
	}

}
