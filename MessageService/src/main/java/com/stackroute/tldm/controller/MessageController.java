package com.stackroute.tldm.controller;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.MessageResponse;
import com.stackroute.tldm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin("*")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/response")
    public MessageResponse messageResponse(Message message) throws Exception {
//        System.out.println("messages:::"+message);
        messageService.saveMessage(message);
        String time = new SimpleDateFormat("h:mm a").format(message.getCreatedAt());
//        String sender_id = message.getSender().getUserId();
        System.out.println(message.getMessageContent());
        return new MessageResponse(message.getMessageContent(), time);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") String m_id) {
        ResponseEntity<?> responseEntity;
        try {
            if (messageService.deleteMessage(m_id)) {
                responseEntity = new ResponseEntity(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

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
