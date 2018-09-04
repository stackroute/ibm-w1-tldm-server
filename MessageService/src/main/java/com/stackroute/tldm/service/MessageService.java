package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;

import java.util.List;

public interface MessageService {

    void saveMessage(Message message);

    boolean deleteMessage(String messageId) throws MessageNotFoundException;

    List<Message> getMessagesByUserIdAndReceiverId(String senderId, String receiverId) throws MessageNotFoundException;
}
