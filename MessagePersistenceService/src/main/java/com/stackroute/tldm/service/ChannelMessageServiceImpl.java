package com.stackroute.tldm.service;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.repository.ChannelChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ChannelMessageServiceImpl implements ChannelMessageService {

    private ChannelChatRepository channelChatRepository;

    @Autowired
    public ChannelMessageServiceImpl(ChannelChatRepository channelChatRepository) {
        this.channelChatRepository = channelChatRepository;
    }

    @Override
    public void saveMessage(ChannelMessage channelMessage) {
        UUID newMessageId = UUID.randomUUID();
        channelMessage.setMessageId(newMessageId);
        channelMessage.setTimestamp(new Date());
        channelChatRepository.insert(channelMessage);
    }
}
