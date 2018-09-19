package com.stackroute.tldm.service;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.repository.GroupChatPersistRepository;

@Service
public class ChannelPersistServiceImpl implements ChannelPersistService{
	
	private GroupChatPersistRepository groupChatRepository;
	
	@Autowired
	public ChannelPersistServiceImpl(GroupChatPersistRepository groupChatRepository) {
		this.groupChatRepository = groupChatRepository;
	}
	
	@Override
	public void saveMessage(ChannelMessage channelMessage) {
		UUID newMessageId = UUID.randomUUID();
		channelMessage.setChannelMessageId(newMessageId);
		channelMessage.setCreatedAt(new Date());
		groupChatRepository.insert(channelMessage);
	}

}
