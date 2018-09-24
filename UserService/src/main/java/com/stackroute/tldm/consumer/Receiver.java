package com.stackroute.tldm.consumer;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private UserService userService;

    @Autowired
    public Receiver(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "${topic.boot}", groupId = "${groupId.boot}")
    public void receive(@Payload User user) {
        try {
            userService.registerUser(user);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}

