package com.stackroute.tldm.service;

import java.util.ArrayList;
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

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepo) {
        this.communityRepo = communityRepo;
    }

    // create community
    @Override
    public Community createCommunity(Community community) throws CommunityAlreadyExistsException {
        Community createCommunity = null;
        if (communityRepo.getCommunityByCommunityName(community.getCommunityName()) == null) {
            community.setCommunityCreatedDate(new Date());
            createCommunity = communityRepo.insert(community);
        } else {
            throw new CommunityAlreadyExistsException("community already exists");
        }

        return createCommunity;
    }

    // update community
    @Override
    public Community updateCommunity(String communityId, Community community) throws CommunityNotFoundException {
        Community fetch = communityRepo.findById(communityId).get();
        if (fetch != null) {
            communityRepo.save(community);
        }

        return community;
    }

    
   

    // delete community using communityId
    @Override
    public boolean delCommunity(String communityId) throws CommunityNotFoundException {
        if (communityRepo.findById(communityId) != null) {
            communityRepo.deleteById(communityId);
        } else {
            throw new CommunityNotFoundException("community not found");
        }

        return true;
    }

    //all users in the community
    @Override
    public List<User> findAllCommunityUsersByCommunityName(String communityName) throws CommunityNotFoundException {
        List<User> fetchUsers = null;
        try {
            fetchUsers = communityRepo.getCommunityByCommunityName(communityName).getCommunityUsers();
        } catch (NoSuchElementException e) {
            throw new CommunityNotFoundException("community not found");
        }

        return fetchUsers;
    }

    // all channels within the community.
   @Override
    public List<Channel> findAllChannelsByCommunityName(String communityName) throws CommunityNotFoundException {
        List<Channel> fetchChannels = null;
        try {
            fetchChannels = communityRepo.getCommunityByCommunityName(communityName).getChannelsList();
        } catch (NoSuchElementException e) {
            throw new CommunityNotFoundException("community not found");
        }

        return fetchChannels;
    }

    

    //update community using communityId
    @Override
    public Community updateByCommunityId(String communityId, Channel channel) {
        List<Channel> fetchChannel = new ArrayList<>();
        Community fetchCommunity;
        fetchCommunity = communityRepo.findById(communityId).get();
        fetchChannel = fetchCommunity.getChannelsList();
        fetchChannel.add(channel);
        fetchCommunity.setChannelsList(fetchChannel);
        communityRepo.save(fetchCommunity);

        return fetchCommunity;
    }
//get community details using communityId

	@Override
	public Community getCommunityByCommunityId(String communityId) throws CommunityNotFoundException {
		Community community;
      try {
          community = communityRepo.findById(communityId).get();

      } catch (NoSuchElementException e) {
          throw new CommunityNotFoundException("community not found");
      }

      return community;
  }



	

	

	
}
