package com.stackroute.tldm.test.controller;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.util.Date;
import java.util.List;

import org.junit.Before;

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

import com.stackroute.tldm.controller.CommunityController;
import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;

import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.CommunityService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CommunityController.class, secure = false)
public class CommunityControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CommunityService communityService;
	@MockBean
	private Community community;
	@MockBean
	private User user;
	@MockBean
	private Channel channel;
	@InjectMocks
	private CommunityController communityController;
	private List<Channel> channelsList;
	private List<User> communityUsers;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(communityController).build();
		community = new Community();
		community.setCommunityId("5b04f7411764e3765c35f8f6");
		community.setCommunityName("PRODUCT");
		community.setCommunityCreatedDate(new Date());
		community.setCommunityCreatedBy(user);

		community.setChannelsList(channelsList);

		community.setCommunityUsers(communityUsers);

		// users
		user = new User();
		user.setUserId("swedha12");
		user.setUserPhoneNum("56528769987");
		user.setUserName("swetha");
		user.setUserMail("swedha87@gmail.com");
		user.setUserCreatedAt(new Date());

		// channels

		channel = new Channel();
		channel.setChannelId("tldm123");
		channel.setChannelName("tldm");
		channel.setchannelCreatedBy("Gayathri");
		channel.setChannelDescription("product works");

	}
	

	@Test
//	@Ignore
	public void deleteCommunitySuccess() throws Exception {
		when(communityService.delCommunity("5b04f7411764e3765c35f8f6")).thenReturn(true);
		mockMvc.perform(delete("/api/v1/community/delete/5b04f7411764e3765c35f8f6")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(community)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
//	@Ignore
	public void deletecommunityFailure() throws Exception {
		when(communityService.delCommunity("5b04f7411764e3765c35f8f6")).thenThrow(CommunityNotFoundException.class);
		mockMvc.perform(delete("/api/v1/community/delete/5b04f7411764e3765c35f8f6")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(community)))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
	}


	@Test
	public void getCommunityByCommunityIdFailure() throws Exception {
		when(communityService.getCommunityByCommunityId(community.getCommunityId())).thenThrow(CommunityNotFoundException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/community/get/5b04f7411764e3765c35f8f6")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}
	

	private byte[] asJsonString(Community community2) {
		// TODO Auto-generated method stub
		return null;
	}

}
