package com.stackroute.tldm.service;

import java.util.List;

import com.stackroute.tldm.exception.ChannelAlreadyExistsException;
import com.stackroute.tldm.exception.ChannelNotFoundException;

import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.User;

public interface ChannelService {

	Channel createChannel(Channel channel) throws ChannelAlreadyExistsException;

	Channel updateChannel(String channelId, Channel channel) throws ChannelNotFoundException;

	boolean deleteChannel(String channelId) throws ChannelNotFoundException;

	List<Channel> getAllChannels();

	Channel getChannelByChannelName(String channelName) throws ChannelNotFoundException;

	List<User> findAllChannelUsersByChannelName(String channelName);

	Channel updateChannelUser(String channelId, List<User> user) throws ChannelNotFoundException;

	boolean removeChannelUser(String channelId, String userId);

	
	
	List<Channel> getListOfChannelsByUser(String userId);
	
	Channel getChannelByChannelId(String channelId) throws ChannelNotFoundException;
}
