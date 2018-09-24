package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.repository.ChannelChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ChannelMessageServiceImpl implements ChannelMessageService {

    private ChannelChatRepository channelChatRepository;

    @Autowired
    public ChannelMessageServiceImpl(ChannelChatRepository channelChatRepository) {
        this.channelChatRepository = channelChatRepository;
    }

    // this method is used to save a message in a channel

    // Saving a Channel Message
    @Override
    public ChannelMessage saveMessage(ChannelMessage channelMessage) {
        return channelChatRepository.insert(channelMessage);
    }

    // this method is used to delete the channel messages

    // Deleting a particular Channel Message By Sender
    @Override

    public boolean deleteChannelMessage(UUID messageId, String senderId) throws MessageNotFoundException {
        if (channelChatRepository.existsById(messageId)) {
            ChannelMessage message = channelChatRepository.findById(messageId).get();
            String sender = message.getSender().getUserId();
            if (sender.equals(senderId) && message != null) {
                channelChatRepository.deleteById(messageId);
                return true;
            } else {
                throw new MessageNotFoundException("Message Not found!!");
            }
        } else {
            throw new MessageNotFoundException("Message Not found!!");
        }
    }

    // this method is used to get all the channel messages using specific channel ID
    // Get All Channel Messages By channelId
    @Override
    public List<ChannelMessage> getChannelMessagesByChannelId(String channelId) throws MessageNotFoundException {
        List<ChannelMessage> channelMessages = channelChatRepository.findAll();
        List<ChannelMessage> messageList = new ArrayList<>();
        if (channelMessages != null) {
            Iterator<ChannelMessage> messageIterator = channelMessages.iterator();
            while (messageIterator.hasNext()) {
                ChannelMessage groupMessages = messageIterator.next();
                String channel = groupMessages.getChannel().getChannelId();
                if ((channelId.equals(channel))) {
                    messageList.add(groupMessages);
                }
            }
            return messageList;
        } else {
            throw new MessageNotFoundException("Message Not Found!!");
        }
    }
}
