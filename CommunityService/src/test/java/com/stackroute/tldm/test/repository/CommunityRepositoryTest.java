package com.stackroute.tldm.test.repository;

import java.util.Date;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Ignore;
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
    public void setUp()  throws Exception{
    	
    	community = new Community();
		community.setCommunityId("swetha123");
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
 
    public void createCommunityTest() {
        communityRepo.insert(community);
        Community fetchUser = communityRepo.findById("swetha123").get();
        Assert.assertEquals(community.getCommunityId(), fetchUser.getCommunityId());
    }
    @Test
    
    public void updateCommunityTest() {
        communityRepo.insert(community);
        Community fetchUser = communityRepo.findById("swetha123").get();
        fetchUser.setCommunityName("Product-2");
        communityRepo.save(fetchUser);
        fetchUser = communityRepo.findById("swetha123").get();
        Assert.assertEquals("swetha123", fetchUser.getCommunityName());
    }
    
    @Test
 
    public void getCommunityByCommunityNameTest() {
        communityRepo.insert(community);
        Community fetchUser = communityRepo.getCommunityByCommunityName("Product");
        Assert.assertEquals(community.getCommunityName(), fetchUser.getCommunityName());
    }
    
    @Test
   
    public void getCommunityByIdTest() {
        communityRepo.insert(community);
        Community fetchUser = communityRepo.findById("swetha123").get();
        Assert.assertEquals(community.getCommunityId(), fetchUser.getCommunityId());
    }
    
    

    
    }
	
	

