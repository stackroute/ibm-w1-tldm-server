package com.stackroute.tldm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;
import com.stackroute.tldm.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody UserModel user) {
//		ResponseEntity<?> responseEntity = null;
//		try {
//			if (service.registerUser(user)) {
//				responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
//			}
//		} catch (UserAlreadyExistsException exception) {
//			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
//
//		}
//		return responseEntity;

		try {
			return new ResponseEntity<>(service.registerUser(user), HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> updateMethod(@PathVariable String userId, @RequestBody UserModel user) {
		ResponseEntity<?> responseEntity = null;
		try {
			service.updateUser(userId, user);
			responseEntity = new ResponseEntity<>(user, HttpStatus.OK);

		} catch (UserNotFoundException exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

		}

		return responseEntity;
	}

	@GetMapping("/id/{userName}")
	public ResponseEntity<?> showMethod(@PathVariable String userName) {
		ResponseEntity<?> responseEntity = null;

		if (service.getUserByUserName(userName) != null) {
			responseEntity = new ResponseEntity<>(service.getUserByUserName(userName), HttpStatus.OK);

		} else {
			responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id) {
		ResponseEntity<?> responseEntity = null;
		UserModel fetch = service.getUserById(id);
		if (fetch != null) {
			responseEntity = new ResponseEntity<>(fetch, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>("user details for id is not found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;

	}

}