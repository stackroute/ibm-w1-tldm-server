package com.stackroute.tldm.controller;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.service.ChannelPersistService;
import com.stackroute.tldm.service.MessagePersistService;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin("*")
public class MessagePersistenceController {
	
	private MessagePersistService messagePersistenceService;
//	private ChannelService channelService;
	private KafkaTemplate<String, Message> kafkaTemplate;
//	private KafkaTemplate<String, ChannelMessage> channelKafkaTemplate;
	private static String BOOT_TOPIC = "message";
	private static String CHANNEL_TOPIC = "channel";
	
	@Autowired
	public MessagePersistenceController(MessagePersistService messagePersistenceService, ChannelPersistService channelService, KafkaTemplate<String, Message> kafkaTemplate,
			KafkaTemplate<String, ChannelMessage> channelKafkaTemplate) {
		this.messagePersistenceService = messagePersistenceService;
	//	this.channelService = channelService;
		this.kafkaTemplate = kafkaTemplate;
	//	this.channelKafkaTemplate = channelKafkaTemplate;
	}


	@MessageMapping("/chat")
	public void sendMessage(Message message){
		messagePersistenceService.saveMessage(message);
		kafkaTemplate.send(BOOT_TOPIC, message);
	}
	
//	@MessageMapping("/channel-chat")
//	public void sendMessageToChannel(ChannelMessage channelMessage) throws Exception {
//		channelService.saveMessage(channelMessage);
//		channelKafkaTemplate.send(CHANNEL_TOPIC, channelMessage);
//	}

	// Delete a particular messageById.
	@DeleteMapping("/{messageId}")
	public ResponseEntity<?> deleteMessage(@PathVariable("messageId") UUID m_id) {
		ResponseEntity<?> responseEntity;
		try {
			if (messagePersistenceService.deleteMessage(m_id)) {
				responseEntity = new ResponseEntity<>(HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (MessageNotFoundException e) {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// Get a conversation between Sender and the Receiver.
	@GetMapping("/{senderId}/{receiverId}")
	public ResponseEntity<?> getMessagesByUserAndReceiver(@PathVariable("senderId") String senderId,
			@PathVariable("receiverId") String receiverId) {
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = new ResponseEntity<>(messagePersistenceService.getMessagesByUserIdAndReceiverId(senderId, receiverId),
					HttpStatus.OK);
		} catch (MessageNotFoundException e) {
			responseEntity = new ResponseEntity<>("Message Not Found!", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

}
