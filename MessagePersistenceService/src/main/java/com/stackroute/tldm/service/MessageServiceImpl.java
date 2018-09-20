package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.repository.UserChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    private UserChatRepository userChatRepository;

    @Autowired
    public MessageServiceImpl(UserChatRepository userChatRepository) {
        super();
        this.userChatRepository = userChatRepository;
    }

    @Override
    public void saveMessage(Message message) {
        UUID newMessageId = UUID.randomUUID();
        message.setMessageId(newMessageId);
        message.setCreatedAt(new Date());
        userChatRepository.save(message);
    }

    @Override
    public boolean deleteMessage(UUID messageId) throws MessageNotFoundException {
        if (userChatRepository.existsById(messageId)) {
            userChatRepository.deleteById(messageId);
            return true;
        } else {
            throw new MessageNotFoundException("Message Not Found!");
        }
    }

    @Override
    public List<Message> getMessagesBySenderIdAndReceiverId(String senderId, String receiverId)
            throws MessageNotFoundException {
        List<Message> messageList = userChatRepository.findAll();
        List<Message> messages = new ArrayList<>();
        if (messageList != null) {
            ListIterator<Message> iterator = messageList.listIterator();
            while (iterator.hasNext()) {
                Message messageToGet = iterator.next();
                String sender = messageToGet.getSender().getUserId();
                String receiver = messageToGet.getReceiver().getUserId();
                if ((senderId.equals(sender) || senderId.equals(receiver))
                        && (receiverId.equals(receiver) || receiverId.equals(sender))) {
                    messages.add(messageToGet);
                }
            }
            return messages;
        } else {
            throw new MessageNotFoundException("Message Not Found!");
        }
    }


}