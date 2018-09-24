package com.stackroute.tldm.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.ChannelChatRepository;

import com.stackroute.tldm.service.ChannelMessageServiceImpl;



public class ChannelMessageServiceImplTest {

	@MockBean
	private ChannelMessage channelMessage;
	@MockBean
	private Channel channel;
	@MockBean
	private Community community;
	@MockBean
	private User user;
	@MockBean
	private ChannelChatRepository channelChatRepository;
	
	List<ChannelMessage> options;

	@InjectMocks
	private ChannelMessageServiceImpl channelMessageService;
	private UUID uuid;
	private List<Channel> channelList = null;
	private List<ChannelMessage> channelMessageList = null;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		channelMessage = new ChannelMessage();
		uuid = mock(UUID.class);

		// Channel Message
		channelMessage = new ChannelMessage();
		channelMessage.setMessageId(uuid);
		channelMessage.setMessageContent("channel message");
		channelMessage.setSender(user);
		channelMessage.setChannel(channel);
		channelMessage.setTimestamp(new Date());

		channelMessageList = new ArrayList<>();
		channelMessageList.add(channelMessage);

		// Community
		community = new Community();
		community.setCommunityId("prod789");
		community.setCommunityName("product");
		community.setCommunityCreatedBy(user);
		community.setCommunityCreatedDate(new Date());
		community.setChannelList(channelList);
		List<User> userList = new ArrayList<>();
		community.setCommunityUsers(userList);
		userList.add(user);

		// Channel
		channel = new Channel();
		channel.setChannelId("xyz987");
		channel.setChannelName("random");
		channel.setChannelCreatedBy("abc");
		channel.setChannelCreatedDate(new Date());
		channel.setChannelDescription("group message");
		channel.setCommunity(community);
		channel.setChannelUsers(userList);

		// User
		user = new User();
		user.setUserId("John123");
		user.setUserMail("john2578@gmail.com");
		user.setUserName("Ganga");
		user.setPhoneNum("7401323395");
		user.setCreatedAt(new Date());
		
	}
	
	 @Test
	    public void saveMessageSuccess() {
		 when(channelChatRepository.save(channelMessage).getMessageId()).thenReturn(uuid);
	      ChannelMessage status = channelMessageService.saveMessage(channelMessage);
	        Assert.assertEquals(channelMessage, status);
	      
	        
	      
	   
	    }
	
	 @Test
	    public void deleteChannelMessageSuccess() throws MessageNotFoundException {
	        Optional<ChannelMessage> options = null;
			when(channelChatRepository.findById(uuid)).thenReturn(options);
	        when(channelChatRepository.save(channelMessage)).thenReturn(channelMessage);
	        boolean flag = channelMessageService.deleteChannelMessage(channelMessage.getMessageId(),"john123");
	        Assert.assertEquals(true, flag);

	}
	 
	  @Test
	    public void deleteChannelMessageFailure() throws MessageNotFoundException {
	        when(channelChatRepository.findById(channelMessage.getMessageId()));
	        when(channelChatRepository.save(channelMessage)).thenReturn(channelMessage);
	        boolean flag = channelMessageService.deleteChannelMessage(channelMessage.getMessageId(), "Jhon123");
	        Assert.assertEquals(true, flag);
	    }

	
	 @Test
	    public void getChannelMessagesByChannelId() throws MessageNotFoundException {
	        when(channelChatRepository.findAll()).thenReturn(options);
	        List<ChannelMessage> channels = channelMessageService.getChannelMessagesByChannelId("john123");
	        Assert.assertEquals(channelList, channels);
	    }
}
