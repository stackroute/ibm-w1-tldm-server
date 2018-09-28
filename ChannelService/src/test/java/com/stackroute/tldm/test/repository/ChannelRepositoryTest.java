package com.stackroute.tldm.test.repository;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
    private List<Channel> allChannel = new ArrayList<>();
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

		allChannel.add(channel);

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


	@Test
	 public void createChannelTest() {
	       channelRepository.insert(channel);
	       Channel fetchUser = channelRepository.findById(channel.getChannelId()).get();
	       Assert.assertEquals("tldm123", fetchUser.getChannelId());
	   }
	@Test
	public void getChannelByChannelIdTest() {
		channelRepository.insert(channel);
		Channel fetchChannel = channelRepository.findById("tldm123").get();
		Assert.assertEquals(channel.getChannelId(), fetchChannel.getChannelId());
	}

	@Test
	public void updatechannelTest() {
		channelRepository.insert(channel);
		Channel fetchChannel = channelRepository.findById("tldm123").get();
		fetchChannel.setChannelDescription("product works");
		channelRepository.save(fetchChannel);
		fetchChannel = channelRepository.findById("tldm123").get();
		Assert.assertEquals("product works", fetchChannel.getChannelDescription());
	}
	
	@Test(expected = NoSuchElementException.class)
    public void deleteChanneltest() {
        channelRepository.insert(channel);
        Channel fetchedChannel = channelRepository.findById(channel.getChannelId()).get();
        Assert.assertEquals("tldm123", fetchedChannel.getChannelId());
        channelRepository.delete(fetchedChannel);
        fetchedChannel = channelRepository.findById(channel.getChannelId()).get();

    }

}
