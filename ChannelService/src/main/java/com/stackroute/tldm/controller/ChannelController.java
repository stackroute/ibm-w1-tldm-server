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

import com.stackroute.tldm.exception.ChannelAlreadyExistsException;
import com.stackroute.tldm.exception.ChannelNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.ChannelService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/channel")
public class ChannelController {

	private ChannelService channelService;

	@Autowired
	public ChannelController(ChannelService channelService) {
		this.channelService = channelService;

	}

	// this handler method is mapped to the URL using HTTP POST method //
	@PostMapping
	public ResponseEntity<?> createChannel(@RequestBody Channel channel) {
		ResponseEntity<?> responseEntity = null;
		try {
			if (channelService.createChannel(channel) != null) {
				responseEntity = new ResponseEntity<>(channel, HttpStatus.CREATED);

			}

		} catch (ChannelAlreadyExistsException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);

		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/{channelId}" using HTTP DELETE
	// method //
	@DeleteMapping("/{channelId}")
	public ResponseEntity<?> deleteChannel(@PathVariable String channelId) {
		ResponseEntity<?> responseEntity = null;
		try {
			if (channelService.deleteChannel(channelId)) {
				responseEntity = new ResponseEntity<>("Channel Deleted Successfully", HttpStatus.OK);
			}
		} catch (ChannelNotFoundException exception) {
			responseEntity = new ResponseEntity<>("Channel Not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL using HTTP GET method //
	@GetMapping
	public ResponseEntity<?> getAllchannels() {
		ResponseEntity<?> responseEntity;
		List<Channel> channelList = channelService.getAllChannels();
		if (channelList != null) {
			responseEntity = new ResponseEntity<>(channelList, HttpStatus.OK);

		} else {
			responseEntity = new ResponseEntity<>("No channels Found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/update/{channelId}" using HTTP PUT
	// method //
	@PutMapping("/update/{channelId}")
	public ResponseEntity<?> updateChannel(@PathVariable String channelId, @RequestBody Channel channel) {
		ResponseEntity<?> responseEntity = null;
		try {

			channelService.updateChannel(channelId, channel);
			responseEntity = new ResponseEntity<>(channel, HttpStatus.OK);

		} catch (ChannelNotFoundException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/{channelId}" using HTTP PUT method
	// //
	@PutMapping("/{channelId}")
	public ResponseEntity<?> updateChannelUser(@PathVariable String channelId, @RequestBody User user) {
		ResponseEntity<?> responseEntity = null;
		try {

			channelService.updateChannelUser(channelId, user);
			responseEntity = new ResponseEntity<>(channelId, HttpStatus.OK);

		} catch (ChannelNotFoundException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/{channelName}" using HTTP GET
	// method //
	@GetMapping("/{channelName}")
	public ResponseEntity<?> getChannelByChannelName(@PathVariable String channelName) {
		ResponseEntity<?> responseEntity;
		Channel fetch;
		try {
			fetch = channelService.getChannelByChannelName(channelName);
			if (fetch != null) {
				responseEntity = new ResponseEntity<>(fetch, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("channel name not found", HttpStatus.NOT_FOUND);
			}
		} catch (ChannelNotFoundException e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/getuser/{channelName}" using HTTP
	// GET method"
	@GetMapping("/getuser/{channelName}")
	public ResponseEntity<?> getUsersByChannelName(@PathVariable String channelName) {
		ResponseEntity<?> responseEntity;
		List<User> allUsers;
		allUsers = channelService.findAllChannelUsersByChannelName(channelName);
		if (allUsers != null) {
			responseEntity = new ResponseEntity<>(allUsers, HttpStatus.OK);
			
		} else {
			responseEntity = new ResponseEntity<>("channel name not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/user/{userName}" using HTTP GET
	// method //
	@GetMapping("/user/{userName}")
	public ResponseEntity<?> getchannelByUserName(@PathVariable String userName) {
		ResponseEntity<?> responseEntity;
		List<Channel> fetchChannel = channelService.getListOfChannelsByUsers(userName);
		if (fetchChannel != null) {
			responseEntity = new ResponseEntity<>(fetchChannel, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("user name not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/{channelId}/{userId}" using HTTP
	// DELETE method //
	@DeleteMapping("/{channelId}/{userId}")
	public ResponseEntity<?> removeChannelUser(@PathVariable String channelId, @PathVariable String userId) {
		ResponseEntity<?> responseEntity = null;
		
		if (channelService.removeChannelUser(channelId, userId)) {
			responseEntity = new ResponseEntity<>("Channel User Deleted Successfully", HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// this handler method is mapped to the URL "/users/{userId}" using HTTP GET
	// method //
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getchannelByUserId(@PathVariable String userId) {
		ResponseEntity<?> responseEntity;
		List<Channel> fetchChannel = channelService.getListOfChannelsByUser(userId);
		if (fetchChannel != null) {
			responseEntity = new ResponseEntity<>(fetchChannel, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("user name not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
