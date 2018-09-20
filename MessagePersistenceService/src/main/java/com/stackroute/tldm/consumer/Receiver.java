package com.stackroute.tldm.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.service.MessageService;

public class Receiver {
	
	private MessageService messageService;

	@Autowired
	public Receiver(MessageService messageService) {
		super();
		this.messageService = messageService;
	}

	
	@KafkaListener(topics = "message", groupId = "message_group_persist")
	public void receive(@Payload Message message) {
		messageService.saveMessage(message);
		System.out.println("Message:" + message);
	}

}
