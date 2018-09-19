package com.stackroute.tldm.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.CommunityRepository;

@Service
public class CommunityServiceImpl implements CommunityService {

	private CommunityRepository communityRepo;

	//private User user;

	@Autowired
	public CommunityServiceImpl(CommunityRepository communityRepo) {
		this.communityRepo = communityRepo;
	}

	@Override
	public Community createCommunity(Community community) throws CommunityAlreadyExistsException {
		Community createCommunity = null;

		if (!communityRepo.existsById(community.getCommunityId())) {
             community.setCommunityCreatedDate(new Date());
            // community.setCommunityCreatedBy(user.getUserName());;
         
			createCommunity = communityRepo.insert(community);
		} else {
			throw new CommunityAlreadyExistsException("community already exists");
		}

		return createCommunity;
	}

	@Override
	public boolean deleteCommunity(String communityId) throws CommunityNotFoundException {
		if (communityRepo.findById(communityId) != null) {

			communityRepo.deleteById(communityId);
		} else {
			throw new CommunityNotFoundException("community not found");
		}

		return true;

	}

	@Override
	public Community updateCommunity(String communityId, Community community)throws CommunityNotFoundException {

		Community fetch = communityRepo.findById(communityId).get();

		if (fetch != null) {

			communityRepo.save(community);
		}
		return community;
	}


	@Override
	public Community getCommunityById(String communityId)throws CommunityNotFoundException {
		 Community fetchCommunity;
	        try {
	            fetchCommunity= communityRepo.findById(communityId).get();
	        } catch (NoSuchElementException exception) {
	            throw new CommunityNotFoundException("Community not found");
	        }

	        return fetchCommunity;
	
	}
// all channels within the community.
	
	@Override
	public List<Channel> getAllChannels() {
//	List<Channel> list;
		return null;
	}
	
//all users in the community
	
	@Override
	public List<User> getAllUsers() {
		//List<User> userList
		return null;
	}

	@Override
	public Community getCommunityByCommunityName(String communityName) throws CommunityNotFoundException {
		Community community;
		try {
			community=communityRepo.getCommunityByCommunityName(communityName);
			
		}catch(NoSuchElementException e)
		{
			throw new CommunityNotFoundException("community not found");
		}
		return community;
	}

	


}
