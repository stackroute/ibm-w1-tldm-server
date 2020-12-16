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

    // this method is used to save a message
    @Override
    public Message saveMessage(Message message) {
        return userChatRepository.insert(message);
    }

    // this method is used to delete a message
    @Override
    public boolean deleteMessage(UUID messageId, String senderId) throws MessageNotFoundException {
        if (userChatRepository.existsById(messageId)) {
            Message message = userChatRepository.findById(messageId).get();
            String sender = message.getSender().getUserId();
            if (sender.equals(senderId)) {
                userChatRepository.deleteById(messageId);

                return true;
            } else {
                throw new MessageNotFoundException("Message Not Found!");
            }
        } else {
            throw new MessageNotFoundException("Message Not Found!");
        }
    }

    // this method is used to get all the messages using specific senderID and receiverID
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