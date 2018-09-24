package com.stackroute.tldm.service;

import java.util.List;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;

public interface CommunityService {

	Community createCommunity(Community community) throws CommunityAlreadyExistsException;                                    //create a community

	Community updateCommunity(String communityId, Community community) throws CommunityNotFoundException;                     //update a community



	Community getCommunityById(String communityId) throws CommunityNotFoundException;                                       

	Community getCommunityByCommunityName(String communityName) throws CommunityNotFoundException;                         //get community details by communityNmae

	// delete

	boolean delCommunity(String communityId) throws CommunityNotFoundException;                                      //delete a community
	
	List<User> findAllCommunityUsersByCommunityName(String communityName)throws CommunityNotFoundException;            //list of users within a community
	
	List<Channel> findAllChannelsByCommunityName(String communityName)throws CommunityNotFoundException;           //list of channels within a community

}
