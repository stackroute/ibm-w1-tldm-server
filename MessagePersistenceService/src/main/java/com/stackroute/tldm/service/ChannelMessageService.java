package com.stackroute.tldm.service;

import java.util.List;
import java.util.UUID;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.ChannelMessage;

public interface ChannelMessageService {

    void saveMessage(ChannelMessage channelMessage);
    
    boolean deleteChannelMessage(UUID messageId, String senderId) throws MessageNotFoundException;
    
    List<ChannelMessage> getChannelMessagesByChannelId(String channelId) throws MessageNotFoundException;
}
