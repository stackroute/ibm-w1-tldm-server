package com.stackroute.tldm.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.service.MessagePersistService;

public class Receiver {
	
	MessagePersistService messagePersists;

	@Autowired
	public Receiver(MessagePersistService messagePersists) {
		super();
		this.messagePersists = messagePersists;
	}
	
	@KafkaListener(topics = "message_persist")
	public void receive(@Payload Message message) {
		
		System.out.println("message is ::"+ message);
		messagePersists.saveMessage(message);
		System.out.println("Message:" + message);
	}

}
