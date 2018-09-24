package com.stackroute.tldm.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.CommunityService;

@RestController
@RequestMapping("api/v1/community")                                      //default api for community service
@CrossOrigin("*")

public class CommunityController {

	private CommunityService communityService;

	@Autowired

	public CommunityController(CommunityService communityService) {
		this.communityService = communityService;
	}

	
	
	//creating a community
	
	@PostMapping                
	public ResponseEntity<?> createCommunity(@RequestBody Community community) {
		ResponseEntity<?> responseEntity = null;
		try {
			if (communityService.createCommunity(community) != null) {
				responseEntity = new ResponseEntity<>(community, HttpStatus.CREATED);
			}
		} catch (CommunityAlreadyExistsException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);

		}

		return responseEntity;
	}
	
	
	
	//deleting a community

@DeleteMapping("/delete/{communityId}")
	public ResponseEntity<?> deleteCommunity(@PathVariable String communityId) {

		ResponseEntity<?> responseEntity = null;
		try {
			if (communityService.delCommunity(communityId)) {
				responseEntity = new ResponseEntity<>("Community deleted Successfully", HttpStatus.OK);
			}
		} catch (CommunityNotFoundException exception) {
			responseEntity = new ResponseEntity<>("Community not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	

	
	
	

//	@GetMapping("/{communityId}")
//	public ResponseEntity<?> getCommunityById(@PathVariable String communityId) {
//
//		ResponseEntity<?> responseEntity = null;
//		Community fetchCommunity;
//		try {
//			fetchCommunity = communityService.getCommunityById(communityId);
//			if (fetchCommunity != null) {
//				responseEntity = new ResponseEntity<>(fetchCommunity, HttpStatus.OK);
//			} else {
//				responseEntity = new ResponseEntity<>("community not found", HttpStatus.NOT_FOUND);
//			}
//		} catch (CommunityNotFoundException e) {
//			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//		}
//
//		return responseEntity;
//	}


//updating a community


	@PutMapping("/{communityId}")
	public ResponseEntity<?> updateCommunity(@PathVariable String communityId, @RequestBody Community community) {
		ResponseEntity<?> responseEntity = null;
		try {
			communityService.updateCommunity(communityId, community);
			responseEntity = new ResponseEntity<>(community, HttpStatus.OK);

		} catch (CommunityNotFoundException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}
	
	
	//getting community details
	
	@GetMapping("/get/{communityName}")
	public ResponseEntity<?> getCommunityName(@PathVariable String communityName) throws CommunityNotFoundException{
		ResponseEntity<?> responseEntity = null;	
		try {
			if(communityService.getCommunityByCommunityName(communityName)!=null)
			{
				responseEntity = new ResponseEntity<>(communityService.getCommunityByCommunityName(communityName), HttpStatus.OK);
			}
		

		} catch (CommunityNotFoundException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

		}
		return responseEntity;
}
	
	
	
	//get users present in the community
	
	@GetMapping("/users/{communityName}")
	public ResponseEntity<?>getUsersByCommunityName(@PathVariable String communityName) throws CommunityNotFoundException
	{
		ResponseEntity<?> responseEntity;
		List<User>communityUsers;
		
		communityUsers=communityService.findAllCommunityUsersByCommunityName(communityName);
		if(communityUsers!=null)
		{
			responseEntity=new ResponseEntity<>(communityUsers,HttpStatus.OK);
			
		
		}
		else
		{
			responseEntity=new ResponseEntity<>("community name not found",HttpStatus.NOT_FOUND);
		}
			
		return responseEntity;
		
	}
	
	
	//get list of channels within  a community 
	
	@GetMapping("/channels/{communityName}")
	public ResponseEntity<?> getChannelsByCommunityName(@PathVariable String communityName) throws CommunityNotFoundException
	{
		ResponseEntity<?> responseEntity;
		List<Channel> channelList;
		 
		channelList=communityService.findAllChannelsByCommunityName(communityName);
		if(channelList!=null)
		{
			responseEntity=new ResponseEntity<>(channelList,HttpStatus.OK);
		
		}
		else
		{
			responseEntity=new ResponseEntity<>("community not found",HttpStatus.NOT_FOUND);
			
		}
		
		
		
		
		
		return responseEntity;
	}
	
	

}
