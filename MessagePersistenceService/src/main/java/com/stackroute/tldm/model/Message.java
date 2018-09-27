package com.stackroute.tldm.model;

import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

@Table("Message")
public class Message implements Serializable {

	@PrimaryKey
	@CassandraType(type = DataType.Name.TEXT)
	private UUID messageId;

	@CassandraType(type = DataType.Name.TEXT)
	private String messageContent;

	@CassandraType(type = DataType.Name.UDT, userTypeName = "user")
	private User sender;

	@CassandraType(type = DataType.Name.UDT, userTypeName = "user")
	private User receiver;

	@CassandraType(type = DataType.Name.TIMESTAMP)
	private Date timestamp;

	public Message() {
		super();
	}

	public Message(String messageContent) {
		this.messageContent = messageContent;
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
