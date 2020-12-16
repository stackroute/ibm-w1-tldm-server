package com.stackroute.tldm.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tldm.controller.ChannelController;
import com.stackroute.tldm.exception.ChannelAlreadyExistsException;
import com.stackroute.tldm.exception.ChannelNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.ChannelService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ChannelController.class, secure = false)
public class ChannelControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ChannelService channelService;
	@MockBean
	private Channel channel;
	@InjectMocks
	private ChannelController channelController;
	private User user;
	private User updateUser;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(channelController).build();
		channel = new Channel();
		channel.setChannelId("tldm123");
		channel.setChannelName("tldm");
		channel.setChannelDescription("Product works");
		channel.setChannelCreatedBy("Gayathri");
		channel.setChannelCreatedDate(new Date());
		List<User> userList = new ArrayList<>();
		channel.setChannelUsers(userList);
		userList.add(user);

		// users
		user = new User();
		user.setUserId("swedha12");
		user.setPhoneNum("56528769987");
		user.setUserName("swetha");
		user.setUserMail("swedha87@gmail.com");
		user.setCreatedAt(new Date());

		updateUser = new User();
		updateUser.setUserId("swedha12");
		updateUser.setPhoneNum("56528769987");
		updateUser.setUserName("swetha");
		updateUser.setUserMail("swedha87@gmail.com");
		updateUser.setCreatedAt(new Date());

	}

	@Test
	public void createChannelSuccess() throws Exception {
		when(channelService.createChannel(channel)).thenReturn(channel);
		mockMvc.perform(post("/api/v1/channel").contentType(MediaType.APPLICATION_JSON).content(asJsonString(channel)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void deleteChannelSuccess() throws Exception {
		when(channelService.deleteChannel(channel.getChannelId())).thenReturn(true);
		mockMvc.perform(delete("/api/v1/channel/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteChannelFailure() throws Exception {
		when(channelService.deleteChannel(channel.getChannelId())).thenThrow(ChannelNotFoundException.class);
		mockMvc.perform(delete("/api/v1/channel/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updatechannelSuccess() throws Exception {
		channel.setChannelDescription("product works");
		when(channelService.updateChannel(eq(channel.getChannelId()), any())).thenReturn(channel);
		mockMvc.perform(put("/api/v1/channel/update/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updatechannelFailure() throws Exception {
		when(channelService.updateChannel(eq(channel.getChannelId()), any())).thenThrow(ChannelNotFoundException.class);
		mockMvc.perform(put("/api/v1/channel/update/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateuserSuccess() throws Exception {

		when(channelService.updateChannel(eq(channel.getChannelId()), any())).thenReturn(channel);
		mockMvc.perform(put("/api/v1/channel/update/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	

	@Test
	public void updateuserFailure() throws Exception {

		when(channelService.updateChannel(eq(channel.getChannelId()), any())).thenThrow(ChannelNotFoundException.class);
		mockMvc.perform(put("/api/v1/channel/update/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
		
	}

	@Test
	public void getByChannelIdSuccess() throws Exception {
		when(channelService.getChannelByChannelId(channel.getChannelId())).thenReturn(channel);
		mockMvc.perform(get("/api/v1/channel/getchannel/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getByChannelIdFailure() throws Exception {
		when(channelService.getChannelByChannelId(channel.getChannelId())).thenThrow(ChannelNotFoundException.class);
		mockMvc.perform(get("/api/v1/channel/getchannel/tldm123").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}
	

	@Test
	public void deleteChannelUsersSuccess() throws Exception {
		when(channelService.removeChannelUser(channel.getChannelId(),user.getUserId())).thenReturn(true);
		mockMvc.perform(delete("/api/v1/channel/tldm123/swedha12").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void deleteChannelUsersFailure() throws Exception {
		when(channelService.removeChannelUser(channel.getChannelId(),user.getUserId())).thenReturn(false);
		mockMvc.perform(delete("/api/v1/channel/tldm123/swedha12").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(channel))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}