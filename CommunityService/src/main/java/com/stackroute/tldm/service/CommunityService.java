package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;

import com.stackroute.tldm.model.Community;

public interface CommunityService {

	Community createCommunity(Community community) throws CommunityAlreadyExistsException;

	Community updateCommunity(String communityId, Community community) throws CommunityNotFoundException;

	// List<User> getAllUsers(); //all users in the community

	// List<Channel> getAllChannels(); //all channels within the community

	Community getCommunityById(String communityId) throws CommunityNotFoundException;

	Community getCommunityByCommunityName(String communityName) throws CommunityNotFoundException;

	// delete

	boolean delCommunity(String communityId) throws CommunityNotFoundException;

}
