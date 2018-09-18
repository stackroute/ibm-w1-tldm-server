package com.stackroute.tldm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tldm.config.SecurityTokenGenerator;
import com.stackroute.tldm.exception.UserUserIdAndPasswordMismatchException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.UserAuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user/auth")
@CrossOrigin("*")
public class UserAuthController {

	private UserAuthService userService;

	public UserAuthController(UserAuthService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		ResponseEntity<?> responseEntity = null;

		try {
			if (userService.registerUser(user) != null) {
				responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
			}
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
		}

		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
		try {
			String userId = loginDetails.getUserId();
			String password = loginDetails.getPassword();
			if (userId == null || password == null) {
				throw new UserNotFoundException("userid and Password cannot be empty");
			}
			User user = userService.findUserByUserIdAndPassword(userId, password);
			if (user == null) {
				throw new UserNotFoundException("User with given Id does not exists");
			}
			String fetchedPassword = user.getPassword();
			if (!password.equals(fetchedPassword)) {
				throw new UserUserIdAndPasswordMismatchException(
						"Invalid login credential, Please check email and password ");
			}
			// generating token
			SecurityTokenGenerator securityTokenGenrator = (User userDetails) -> {
				String jwtToken = "";
				jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
				Map<String, String> map1 = new HashMap<>();
				map1.put("token", jwtToken);
				map1.put("userId", user.getUserId());
				map1.put("message", "User successfully logged in");
				return map1;
			};
			Map<String, String> map = securityTokenGenrator.generateToken(user);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (UserNotFoundException | UserUserIdAndPasswordMismatchException exception) {
			return new ResponseEntity<>("{ \"message\": \"" + exception.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}

}
