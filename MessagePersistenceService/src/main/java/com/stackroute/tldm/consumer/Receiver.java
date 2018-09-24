package com.stackroute.tldm.consumer;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.service.ChannelMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private MessageService messageService;
    private ChannelMessageService channelMessageService;

    @Autowired
    public Receiver(MessageService messageService, ChannelMessageService channelMessageService) {
        this.messageService = messageService;
        this.channelMessageService = channelMessageService;
    }

    // this represents the topic-1 used in kafka
    @KafkaListener(topics = "${topic1.boot}", groupId = "${groupId1.boot}")
    public void receiveUserToUser(@Payload Message message) {
        System.out.println("Message:" + message);
        messageService.saveMessage(message);
    }

    // this represents the topic-2 used in kafka
    @KafkaListener(topics = "${topic2.boot}", groupId = "${groupId2.boot}")
    public void receiveGroupMessages(@Payload ChannelMessage channelMessage) {
        channelMessageService.saveMessage(channelMessage);
    }

}
