package com.stackroute.tldm.test.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.CommunityRepository;
import com.stackroute.tldm.service.CommunityService;

public class CommunityServiceImplTest {

	@Mock
	private CommunityRepository communityRepo;
	private Community community;
	private User user;

	@InjectMocks
	private CommunityService communityService;
	private List<Channel> channelsList;
	private List<User> communityUsers;

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

	}
	
	
	@Test
    public void createCommunitySuccess() throws CommunityAlreadyExistsException {
        when(communityRepo.insert((Community) any())).thenReturn(community);
        Community communitySaved = communityService.createCommunity(community);
        assertEquals(community, communitySaved);

    }
	
	
	

}
