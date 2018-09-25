package com.stackroute.tldm.test.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.tldm.controller.ChannelMessagePersistenceController;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.ChannelMessageService;
import com.stackroute.tldm.service.MessageService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ChannelMessagePersistenceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ChannelMessage channelMessage;
	@MockBean
	private User user;
	@MockBean
	private Channel channel;
	@MockBean
	private Community community;
	@MockBean
	private ChannelMessageService channelMessageService;
	@MockBean
	private MessageService messageService;
	@InjectMocks
	private ChannelMessagePersistenceController channelMessagePersistenceController;

	private UUID uuid;
	private List<Channel> channelList = null;
	private List<ChannelMessage> channelMessageList = null;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(channelMessagePersistenceController).build();
		channelMessage = new ChannelMessage();
		uuid= UUID.randomUUID();

		// Community
		community = new Community();
		community.setCommunityId("swe123");
		community.setCommunityName("PRODUCT");
		community.setCommunityCreatedDate(new Date());
		community.setCommunityCreatedBy(user);
		community.setChannelList(channelList);
		List<User> userList = new ArrayList<>();
		community.setCommunityUsers(userList);
		userList.add(user);

		// Channel
		channel = new Channel();
		channel.setChannelId("tldm123");
		channel.setChannelName("tldm");
		channel.setChannelCreatedBy("Gayathri");
		channel.setChannelDescription("product works");
		channel.setChannelCreatedDate(new Date());
		channel.setCommunity(community);
		channel.setChannelUsers(userList);

		// User
		User user = new User();
		user.setUserId("kamali12");
		user.setPhoneNum("56528769987");
		user.setUserName("kamali");
		user.setUserMail("kamali87@gmail.com");

		// Channel Message
		channelMessage.setMessageId(uuid);
		channelMessage.setMessageContent("Hi!!");
		channelMessage.setSender(user);
		channelMessage.setChannel(channel);
		channelMessage.setTimestamp(new Date());

		channelMessageList = new ArrayList<>();
		channelMessageList.add(channelMessage);

	}

	@Test
	public void deleteChannelMessagesSuccess() throws Exception {

		when(channelMessageService.deleteChannelMessage(uuid, "swedha12")).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/channel-message/uuid/swedha12")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Ignore
	@Test
	public void deleteChannelMessageFailure() throws Exception {

		when(channelMessageService.deleteChannelMessage(uuid, "swedha12")).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/channel-message/uuid/swedha12")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
	}

	@Ignore
	@Test
	public void getChannelMessagesByChannelIdSucces() throws Exception {

		when(channelMessageService.getChannelMessagesByChannelId("tldm123")).thenReturn(channelMessageList);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/channel-message/tldm123").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Ignore
	@Test
	public void getChannelMessagesBYChannelIdFailure() throws Exception {

		when(channelMessageService.getChannelMessagesByChannelId("tldm123")).thenReturn(null);
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/channel-message/tldm123").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

}