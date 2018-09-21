package com.stackroute.tldm.consumer;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private SimpMessagingTemplate template;

    @Autowired
    public Receiver(SimpMessagingTemplate template) {
        this.template = template;
    }

    @KafkaListener(topics = "${topic1.boot}", groupId = "${groupId1.boot}")
    public void receive(@Payload Message message) {
        System.out.println("Message:" + message);
        template.convertAndSend("/topic/response/" + message.getSender().getUserId(), message);
        template.convertAndSend("/topic/response/" + message.getReceiver().getUserId(), message);
    }

    @KafkaListener(topics = "${topic2.boot}", groupId = "${groupId2.boot}")
    public void receiveGroupMessages(@Payload ChannelMessage channelMessage) {
        template.convertAndSend("/topic-group/response/" + channelMessage.getSender().getUserId(), channelMessage);
        template.convertAndSend("/topic-group/response/" + channelMessage.getChannel().getChannelId(), channelMessage);
    }


}