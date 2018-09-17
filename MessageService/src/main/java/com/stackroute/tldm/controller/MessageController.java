package com.stackroute.tldm.controller;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin("*")
public class MessageController {

    private MessageService messageService;
    private KafkaTemplate<String, Message> kafkaTemplate;
    private static String BOOT_TOPIC = "message";

    @Autowired
    public MessageController(MessageService messageService, KafkaTemplate<String, Message> kafkaTemplate) {
        this.messageService = messageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @MessageMapping("/chat")
    public void sendMessage(Message message) throws Exception {
        messageService.saveMessage(message);
        kafkaTemplate.send(BOOT_TOPIC, message);
    }

    // Delete a particular messageById.
    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") UUID m_id) {
        ResponseEntity<?> responseEntity;
        try {
            if (messageService.deleteMessage(m_id)) {
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
    public ResponseEntity<?> getMessagesByUserAndReceiver(@PathVariable("senderId") String senderId, @PathVariable("receiverId") String receiverId) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(messageService.getMessagesByUserIdAndReceiverId(senderId, receiverId), HttpStatus.OK);
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity<>("Message Not Found!", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
}
