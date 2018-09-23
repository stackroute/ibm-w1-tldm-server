package com.stackroute.tldm.consumer;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private UserAuthService userAuthService;

    @Autowired
    public Receiver(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @KafkaListener(topics = "${topic.boot}", groupId = "${groupId.boot}")
    public void receive(@Payload User user) {
        System.out.println("User:"+user);
        try {
            userAuthService.registerUser(user);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
