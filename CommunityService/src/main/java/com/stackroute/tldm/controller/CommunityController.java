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
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.exception.CommunityAlreadyExistsException;
import com.stackroute.tldm.exception.CommunityNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.Community;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.CommunityService;

@RestController
@RequestMapping("api/v1/community")
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


   


    //update community:channels get added into the community using communityId
    
    
    @PutMapping("/updated/{communityId")
    public ResponseEntity<?> updateByCommunityId(@PathVariable String communityId, @RequestBody Channel channel, Community community) {
        ResponseEntity<?> responseEntity = null;
        try {
            communityService.updateByCommunityId(communityId, channel);

            responseEntity = new ResponseEntity<>(communityId, HttpStatus.OK);

        } catch (CommunityNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        }

        return responseEntity;
    }
}
