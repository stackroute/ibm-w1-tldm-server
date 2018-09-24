package com.stackroute.tldm.controller;

import java.util.List;
import com.stackroute.tldm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/user")
@Api(value="User Resource")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // this handler method is mapped to the URL "/api/v1/user/{userId}" using  HTTP PUT method
    @PutMapping("/{userId}")
    @ApiOperation("Updating users details")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody User user) {
        ResponseEntity<?> responseEntity;
        try {
            service.updateUser(userId, user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.OK);

        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        }

        return responseEntity;
    }

    // this handler method is mapped to the URL "/api/v1/user/{Id}" using  HTTP GET method
    @GetMapping("/{id}")
    @ApiOperation("Getting User Details by UserId")
    public ResponseEntity<?> getUserByUserId(@PathVariable String id) {
        ResponseEntity<?> responseEntity;
        User fetch;
        try {
            fetch = service.getUserById(id);
            if (fetch != null) {
                responseEntity = new ResponseEntity<>(fetch, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("user details for id is not found", HttpStatus.NOT_FOUND);
            }
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    // this handler method is mapped to the URL "/api/v1/user/name/{userName}" using  HTTP GET method
    @GetMapping("/name/{userName}")
    @ApiOperation("Getting User Details By User Name")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
        ResponseEntity<?> responseEntity;

        try {
            if (service.getUserByUserName(userName) != null) {
                responseEntity = new ResponseEntity<>(service.getUserByUserName(userName), HttpStatus.OK);

            } else {
                responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
            }
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    // this handler method is mapped to the URL "/api/v1/user/{userId}" using  HTTP DELETE method
    @DeleteMapping("/{userId}")
    @ApiOperation("To Delete the User Details")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        ResponseEntity<?> responseEntity = null;
        try {
            if (service.deleteUser(userId)) {
                responseEntity = new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
            }
        } catch (UserNotFoundException exception) {
            responseEntity = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    // this handler method is mapped to the URL "/api/v1/user" using  HTTP GET method
    @GetMapping()
    @ApiOperation("To get the list of users")
    public ResponseEntity<?> getAllUserDetails() {
        ResponseEntity<?> responseEntity;
        List<User> nameList = service.getAllUsers();
        if (nameList != null) {
            responseEntity = new ResponseEntity<>(nameList, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
}
