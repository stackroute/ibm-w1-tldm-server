package com.stackroute.tldm.test.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.CommunityRepository;
import com.stackroute.tldm.service.CommunityService;
import com.stackroute.tldm.service.CommunityServiceImpl;
import java.util.Optional;

public class CommunityServiceImplTest {

	@Mock
	private CommunityRepository communityRepo;
	private Community community;
	private User user;

	@InjectMocks
	private CommunityServiceImpl communityServiceImpl;
	private CommunityService communityService;
	private List<Channel> channelsList;
	private List<User> communityUsers;
	 Optional<Community> options;

	private List<User> userList = null;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		community = new Community();
		community.setCommunityId("swe123");
		community.setCommunityName("PRODUCT");
		community.setCommunityCreatedDate(new Date());
		community.setCommunityCreatedBy(user);

		community.setChannelsList(channelsList);

		community.setCommunityUsers(communityUsers);
		 options = Optional.of(community);

	}
	
	@Test
	@Ignore
	
    public void createCommunitySuccess() throws CommunityAlreadyExistsException {
        when(communityRepo.insert((Community) any())).thenReturn(community);
        Community communitySaved = communityService.createCommunity(community);
        assertEquals(community, communitySaved);

    }
	@Ignore
	  @Test(expected = CommunityAlreadyExistsException.class)
	    public void createCommunityFailure() throws CommunityAlreadyExistsException
	    {
	        when(communityRepo.insert((Community) any())).thenReturn(null);
	        Community communitySaved = communityService.createCommunity(community);
	        Assert.assertEquals(community, communitySaved);
	    }

	@Test
	@Ignore
	
    public void updateCommunity() throws CommunityNotFoundException {
        when(communityRepo.findById(community.getCommunityId())).thenReturn(options);
        community.setCommunityName("product");
        Community fetchCommunity = communityService.updateCommunity(community.getCommunityName(),community);
        assertEquals(community, fetchCommunity);

    }
	
	 @Test
	 @Ignore
	    public void deleteCommunitySuccess() throws CommunityNotFoundException {
	        when(communityRepo.findById(community.getCommunityId())).thenReturn(options);
	        boolean flag = communityService.delCommunity(community.getCommunityId());
	        assertEquals(true, flag);

	    }


	
	

}
