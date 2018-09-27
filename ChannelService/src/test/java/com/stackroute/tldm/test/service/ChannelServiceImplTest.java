package com.stackroute.tldm.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.tldm.exception.ChannelAlreadyExistsException;
import com.stackroute.tldm.exception.ChannelNotFoundException;
import com.stackroute.tldm.model.Channel;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.ChannelRepository;
import com.stackroute.tldm.service.ChannelServiceImpl;

public class ChannelServiceImplTest {

	@MockBean
	private Channel channel;
	@MockBean
	private User user;
	
	@Mock
	private ChannelRepository channelRepository;
	@InjectMocks
	private ChannelServiceImpl channelService;
	private List<Channel> channelList = null;
	private Optional<Channel> options;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		channel = new Channel();
		channel.setChannelId("tldm123");
		channel.setChannelName("tldm");
		channel.setChannelCreatedBy("Gayathri");
		channel.setChannelDescription("product works");
		channel.setChannelCreatedDate(new Date());
		
		List<User> userList = new ArrayList<>();
		channel.setChannelUsers(userList);
		
		// users

		user = new User();
		user.setUserId("swedha12");
		user.setPhoneNum("56528769987");
		user.setUserName("swetha");
		user.setUserMail("swedha87@gmail.com");
		user.setCreatedAt(new Date());

		// userList.add(user);
	}

	
	@Test
	public void createChannelSuccess() throws ChannelAlreadyExistsException {
		when(channelRepository.insert((Channel) any())).thenReturn(channel);
		Channel createChannel = channelService.createChannel(channel);
		assertEquals(channel, createChannel);
	}

	
	@Test
	public void createChannelFailure() throws ChannelAlreadyExistsException {
		when(channelRepository.insert((Channel) any())).thenReturn(channel);
		Channel createChannel1 = channelService.createChannel(channel);
		assertEquals(channel, createChannel1);

	}

	
	@Test
	public void deleteChannelSuccess() throws ChannelNotFoundException {
		when(channelRepository.findById(channel.getChannelId())).thenReturn(options);
		boolean flag = channelService.deleteChannel("tldm");
		assertEquals(true, flag);
	}
	
	@Test
	public void deleteChannelFailure() throws ChannelNotFoundException {
		when(channelRepository.findById(channel.getChannelId())).thenReturn(options);
		boolean flag = channelService.deleteChannel("tldm");
		assertEquals(true, flag);
	}

	
	@Test
	public void updateChannelSuccess() throws ChannelNotFoundException {
		when(channelRepository.findById("tldm123")).thenReturn(options);
		//channel.ChannelDescription("product discussion");
		channelList.add(channel);
		Channel updateChannels = channelService.updateChannel(channel.getChannelId(),channel);
		assertEquals(channel, updateChannels);
	}
	
	@Test
	public void getChannelByChannelId() throws ChannelNotFoundException {
		when(channelRepository.findById(channel.getChannelId())).thenReturn(options);
		Channel fetchChannel = channelService.getChannelByChannelId(channel.getChannelId());
		assertEquals(channel, fetchChannel);
	}

	
	

}
