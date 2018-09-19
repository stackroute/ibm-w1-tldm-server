package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;


@Table("channelMessage")
public class ChannelMessage {
	
	@PrimaryKey
	@CassandraType(type = DataType.Name.TEXT)
	private UUID messageId;
	
	@CassandraType(type = DataType.Name.TEXT)
	private String messageContent;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "user")
	private User sender;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "user")
	private List<User> receiver;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "Channel")
	private Channel channel;
	
	@CassandraType(type = DataType.Name.DATE)
	private Date createdAt;

	public ChannelMessage() {

	}

	public ChannelMessage(UUID messageId, String messageContent, User sender, List<User> receiver, Channel channel,
			Date createdAt) {
		super();
		this.messageId = messageId;
		this.messageContent = messageContent;
		this.sender = sender;
		this.receiver = receiver;
		this.channel = channel;
		this.createdAt = createdAt;
	}

	public UUID getMessageId() {
		return messageId;
	}

	public void setChannelMessageId(UUID messageId) {
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

	public List<User> getReceiver() {
		return receiver;
	}

	public void setReceiver(List<User> receiver) {
		this.receiver = receiver;
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
				+ ", receiver=" + receiver + ", channel=" + channel + ", createdAt=" + createdAt + "]";
	}

}
