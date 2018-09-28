package com.stackroute.tldm.test.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.CommunityRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CommunityRepositoryTest {

	@Autowired
	private CommunityRepository communityRepo;
	private User user;
	private Community community;
	private Channel channel;
	private List<Channel> channelsList;
	private List<User> communityUsers;

	@Before
	public void setUp() throws Exception {

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

	@After
	public void tearDown() throws Exception {

		communityRepo.deleteAll();
	}

	@Test
//    @Ignore

	public void createCommunityTest() {
		communityRepo.insert(community);
		Community fetchUser = communityRepo.findById(community.getCommunityId()).get();
		Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchUser.getCommunityId());
	}

	@Test
//    @Ignore

	public void updateCommunityTest() {
		communityRepo.insert(community);
		Community fetchUser = communityRepo.findById(community.getCommunityId()).get();
		fetchUser.setCommunityName("Product-2");
		communityRepo.save(fetchUser);
		fetchUser = communityRepo.findById("5b04f7411764e3765c35f8f6").get();
		Assert.assertEquals("Product-2", fetchUser.getCommunityName());
	}

	@Test
//    @Ignore

	public void getCommunityByCommunityIdTest() {
		communityRepo.insert(community);
		Community fetchUser = communityRepo.findById(community.getCommunityId()).get();
		Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchUser.getCommunityId());
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteCommunityTest()
	{
		communityRepo.insert(community);
		Community fetchCommunity=communityRepo.findById(community.getCommunityId()).get();
		assertEquals("5b04f7411764e3765c35f8f6",fetchCommunity.getCommunityId());
		communityRepo.delete(fetchCommunity);
		fetchCommunity=communityRepo.findById(community.getCommunityId()).get();
	}
}
