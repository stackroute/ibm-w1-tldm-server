package com.stackroute.tldm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.InvitationService;

@RestController
@RequestMapping("/api/v1/community")
@CrossOrigin("*")
public class InviteController {

    private Logger logger = LoggerFactory.getLogger(InviteController.class);

    @Autowired
    private InvitationService invitationService;

    @RequestMapping("/signup")
    public String signup() {
        return "Please sign up for our service";
    }

    @PostMapping("/signup-success/{userMail}")
    public String signupSuccess(@PathVariable String userMail, User user) {
        user.setUserMail(userMail);
        user.setUserName("isam");
        try {
            invitationService.sendNotification(userMail, user);
        } catch (MailException e) {
            logger.info("error msg" + e.getMessage());
        }

        return "Thank you for registering with us..Check your Mail";
    }
}