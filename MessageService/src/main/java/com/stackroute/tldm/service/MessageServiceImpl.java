package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.Receiver;
import com.stackroute.tldm.model.Sender;
import com.stackroute.tldm.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    private ChatRepository chatRepository;

    @Autowired
    public MessageServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @Override
    public void saveMessage(Message message) {
        message.setCreatedAt(new Date());
        Sender sender = new Sender("1", "mggmanik", "Manik", "mggmanik@gmail.com", "7060186830");
        Receiver receiver = new Receiver("2", "adiwakar", "Diwakar", "adiwakar11@gmail.com", "9778095607");
        message.setSender(sender);
        message.setReceiver(receiver);
        chatRepository.insert(message);
    }

    @Override
    public boolean deleteMessage(String messageId) throws MessageNotFoundException {
        if (chatRepository.existsById(messageId)) {
            chatRepository.deleteById(messageId);
            return true;
        }
        throw new MessageNotFoundException("Message Not Found!");
    }

    @Override
    public List<Message> getMessagesByUserIdAndReceiverId(String senderId, String receiverId) throws MessageNotFoundException {
        List<Message> messageList = chatRepository.findAll();
        List<Message> messages = new ArrayList<>();
        if (messageList != null) {
            ListIterator<Message> iterator = messageList.listIterator();
            while (iterator.hasNext()) {
                Message messageToGet = iterator.next();
                String sender = messageToGet.getSender().getUserId();
                String receiver = messageToGet.getReceiver().getUserId();
                if (senderId.equals(sender) && receiverId.equals(receiver)) {
                    messages.add(messageToGet);
                }
            }
            return messages;
        } else {
            throw new MessageNotFoundException("Message Not Found!");
        }
    }

}
