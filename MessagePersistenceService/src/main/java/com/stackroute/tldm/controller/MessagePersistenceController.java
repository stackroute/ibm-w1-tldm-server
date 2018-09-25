package com.stackroute.tldm.controller;

import java.util.UUID;

import com.stackroute.tldm.service.ChannelMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.exception.MessageNotFoundException;
//import com.stackroute.tldm.model.ChannelMessage;
//import com.stackroute.tldm.service.ChannelPersistService;
import com.stackroute.tldm.service.MessageService;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin("*")
@Api(value = "MessagePersistence Resource")
public class MessagePersistenceController {
	
	private MessageService messageService;

	@Autowired
	public MessagePersistenceController(MessageService messageService, ChannelMessageService channelMessageService) {
		this.messageService = messageService;
	}
	
    // Delete a particular messageById.
    @DeleteMapping("/{messageId}/{senderId}")
    @ApiOperation("Deleting Messages for One to One messages")
    public ResponseEntity<?> deleteMessage(@PathVariable("messageId") UUID messageId, @PathVariable("senderId") String senderId) {
        ResponseEntity<?> responseEntity;
        try {
            if (messageService.deleteMessage(messageId, senderId)) {
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
    @ApiOperation("Get Messages for One to One communication")
    public ResponseEntity<?> getMessagesBySenderAndReceiver(@PathVariable("senderId") String senderId,
                                                            @PathVariable("receiverId") String receiverId) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(messageService.getMessagesBySenderIdAndReceiverId(senderId, receiverId),
                    HttpStatus.OK);
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity<>("Message Not Found!", HttpStatus.NOT_FOUND);
        }
		return responseEntity;
	}

}
