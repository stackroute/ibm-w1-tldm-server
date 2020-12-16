package com.stackroute.tldm.test.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

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
	@MockBean
	private Community community;
	@MockBean
	private User user;
	

	@InjectMocks
	private CommunityServiceImpl communityServiceImpl;
	
	private List<Channel> channelsList;
	private List<User> communityUsers;
	private Optional<Community> options;

	

	@Before
	public void setUp() throws Exception{
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
//	@Ignore
	
    public void createCommunitySuccess() throws CommunityAlreadyExistsException {
        when(communityRepo.insert((Community) any())).thenReturn(community);
        Community communitySaved = communityServiceImpl.createCommunity(community);
        assertEquals(community, communitySaved);

    }

	@Test
//	@Ignore
	
    public void updateCommunitySuccess() throws CommunityNotFoundException {
        when(communityRepo.findById(community.getCommunityId())).thenReturn(options);
        community.setCommunityName("product");
        Community fetchCommunity = communityServiceImpl.updateCommunity(community.getCommunityId(),community);
        assertEquals(community, fetchCommunity);

    }
	@Test
	 public void updateCommunityFailure() throws CommunityNotFoundException {
	        when(communityRepo.findById(community.getCommunityId())).thenReturn(options);
	        community.setCommunityName("product");
	        Community fetchCommunity = communityServiceImpl.updateCommunity(community.getCommunityId(),community);
	        assertEquals(community, fetchCommunity);

	    }
	
	 @Test
//	 @Ignore
	    public void deleteCommunitySuccess() throws CommunityNotFoundException {
	        when(communityRepo.findById(community.getCommunityId())).thenReturn(options);
	        boolean flag = communityServiceImpl.delCommunity(community.getCommunityId());
	        assertEquals(true, flag);

	    }
       
	 
	 @Test
	 public void getCommunityByCommunityIdSuccess()throws CommunityNotFoundException{
		 when(communityRepo.findById(community.getCommunityId())).thenReturn(options);
		 Community fetchCommunity=communityServiceImpl.getCommunityByCommunityId(community.getCommunityId());
	     assertEquals(community,fetchCommunity);
	 }
   
	 @Test(expected = CommunityNotFoundException.class)
	 public void getCommunityByCommunityIdFailure()throws CommunityNotFoundException{
		 when(communityRepo.findById(community.getCommunityId())).thenThrow(NoSuchElementException.class);
		 Community fetchCommunity=communityServiceImpl.getCommunityByCommunityId(community.getCommunityId());
	     assertEquals(community,fetchCommunity);
	 }
	 
	 

	 
	
	

}
