package com.stackroute.tldm.controller;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin("*")
public class MessageController {

	private KafkaTemplate<String, Message> kafkaTemplate;
	private KafkaTemplate<String, ChannelMessage> channelKafkaTemplate;

	@Value("${topic1.boot}")
	private String BOOT_TOPIC;

	@Value("${topic2.boot}")
	private String CHANNEL_TOPIC;

	@Autowired
	public MessageController(KafkaTemplate<String, Message> kafkaTemplate,
			KafkaTemplate<String, ChannelMessage> channelKafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.channelKafkaTemplate = channelKafkaTemplate;
	}

	@MessageMapping("/chat")
	public void sendMessage(Message message) throws Exception {
		UUID newMessageId = UUID.randomUUID();
		message.setMessageId(newMessageId);
		message.setTimestamp(new Date());
		kafkaTemplate.send(BOOT_TOPIC, message);
	}

	@MessageMapping("/channel-chat")
	public void sendMessageToChannel(ChannelMessage channelMessage) throws Exception {
		UUID newChannelMessageId = UUID.randomUUID();
		channelMessage.setMessageId(newChannelMessageId);
		channelMessage.setTimestamp(new Date());
		channelKafkaTemplate.send(CHANNEL_TOPIC, channelMessage);
	}
}
