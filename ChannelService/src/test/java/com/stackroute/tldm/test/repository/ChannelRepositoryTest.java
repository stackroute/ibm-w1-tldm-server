package com.stackroute.tldm.test.repository;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.tldm.exception.ChannelNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.ChannelRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ChannelRepositoryTest {

	@Autowired
	private ChannelRepository channelRepository;
	private Channel channel;
	private User user;

	@Before
	public void setUp() throws Exception {
		channel = new Channel();
		channel.setChannelId("tldm123");
		channel.setChannelName("tldm");
		channel.setChannelCreatedBy("Gayathri");
		channel.setChannelDescription("product works");
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

	}

	@After
	public void tearDown() throws Exception {
		channelRepository.deleteAll();
	}

	@Ignore
	@Test
	public void createChannelTest() {
		channelRepository.insert(channel);
		Channel allChannel = channelRepository.findById("tldm123").get();
		Assert.assertEquals(channel.getChannelId(), allChannel.getChannelId());
	}

	@Ignore
	@Test
	public void getChannelByChannelIdTest() {
		channelRepository.insert(channel);
		Channel fetchChannel = channelRepository.findById("tldm123").get();
		Assert.assertEquals(channel.getChannelId(), fetchChannel.getChannelId());
	}

	@Ignore
	@Test
	public void getChannelByChannelNameTest() throws ChannelNotFoundException {
		channelRepository.insert(channel);
		//Channel fetchChannel1 = channelRepository.getChannelByChannelName("TLDM");
		//Assert.assertEquals(channel.getChannelName(), fetchChannel1.getChannelName());
	}

	@Ignore
	@Test
	public void updatechannelTest() {
		channelRepository.insert(channel);
		Channel fetchChannel = channelRepository.findById("tldm123").get();
		fetchChannel.setChannelDescription("product works");
		channelRepository.save(fetchChannel);
		fetchChannel = channelRepository.findById("tldm123").get();
		Assert.assertEquals("product works", fetchChannel.getChannelDescription());
	}
}
