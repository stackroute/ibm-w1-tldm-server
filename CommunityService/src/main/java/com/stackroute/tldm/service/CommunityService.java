package com.stackroute.tldm.service;

import java.util.List;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;

public interface CommunityService {

    Community createCommunity(Community community) throws CommunityAlreadyExistsException;

    Community updateCommunity(String communityId, Community community) throws CommunityNotFoundException;

    //Community updateByCommunityName(String communityName, Channel channel) throws CommunityNotFoundException;

    Community updateByCommunityId(String communityId, Channel channel) throws CommunityNotFoundException;

   

    Community getCommunityByCommunityName(String communityName) throws CommunityNotFoundException;
    
    Community getCommunityByCommunityId(String communityId) throws CommunityNotFoundException;

    boolean delCommunity(String communityId) throws CommunityNotFoundException;

    List<User> findAllCommunityUsersByCommunityName(String communityName) throws CommunityNotFoundException;

    List<Channel> findAllChannelsByCommunityName(String communityName) throws CommunityNotFoundException;
    
  
    
   // List<User> findAllCommunityUsersByCommunityId(String communityId) throws CommunityNotFoundException;


}
