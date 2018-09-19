package com.stackroute.tldm.service;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.repository.GroupChatRepository;

@Service
public class ChannelServiceImpl implements ChannelService{
	
	private GroupChatRepository groupChatRepository;
	
	@Autowired
	public ChannelServiceImpl(GroupChatRepository groupChatRepository) {
		this.groupChatRepository = groupChatRepository;
	}
	
	@Override
	public void saveMessage(ChannelMessage channelMessage) {
		UUID newMessageId = UUID.randomUUID();
		channelMessage.setMessageId(newMessageId);
		channelMessage.setCreatedAt(new Date());
		groupChatRepository.insert(channelMessage);
	}

}
