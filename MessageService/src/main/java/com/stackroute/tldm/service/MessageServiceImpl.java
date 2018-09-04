package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.Reciever;
import com.stackroute.tldm.model.Sender;
import com.stackroute.tldm.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

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
        Reciever reciever = new Reciever("2", "adiwakar", "Diwakar", "adiwakar11@gmail.com", "9778095607");
        message.setSender(sender);
        message.setReciever(reciever);
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
    public List<Message> getMessagesByUserIdAndRecieverId(String senderId, String recieverId) throws MessageNotFoundException {
        List<Message> messageList = chatRepository.findAll();
        List<Message> messages = new ArrayList<>();
        if (messageList != null) {
            ListIterator<Message> iterator = messageList.listIterator();
            while (iterator.hasNext()) {
                if (senderId.equals(iterator.next().getSender().getUserId()) && recieverId.equals(iterator.next().getReciever().getUserId())) {
                    messages.add(iterator.next());
                }
            }
            return messages;
        } else {
            throw new MessageNotFoundException("Message Not Found!");
        }

    }

}
