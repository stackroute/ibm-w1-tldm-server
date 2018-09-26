package com.stackroute.tldm.controller;

import java.util.UUID;

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
import com.stackroute.tldm.service.ChannelMessageService;

@RestController
@RequestMapping("api/v1/channel-message")
@CrossOrigin("*")
@Api(value = "Channel Message Resource")
public class ChannelMessagePersistenceController {

    private ChannelMessageService channelMessageService;

    @Autowired
    public ChannelMessagePersistenceController(ChannelMessageService channelMessageService) {
        this.channelMessageService = channelMessageService;
    }

    // Delete a particular Channel Message By Sender
    @DeleteMapping("/{messageId}/{senderId}")
    @ApiOperation("Delete Messages for One to Many communication")
    public ResponseEntity<?> deleteChannelMessages(@PathVariable("messageId") UUID messageId,
                                                   @PathVariable("senderId") String senderId) {
        ResponseEntity<?> responseEntity;
        try {
            if (channelMessageService.deleteChannelMessage(messageId, senderId)) {
                responseEntity = new ResponseEntity<>(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    // Get All Channel Messages By ChannelId
    @GetMapping("/{channelId}")
    @ApiOperation("Get Messages for One to Many communication")
    public ResponseEntity<?> getChannelMessagesByChannelId(@PathVariable("channelId") String channelId) {
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(channelMessageService.getChannelMessagesByChannelId(channelId),
                    HttpStatus.OK);
        } catch (MessageNotFoundException e) {
            responseEntity = new ResponseEntity<>("message Not Found!", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

}
