package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private KafkaTemplate<String, User> kafkaTemplate;

    @Value("${search-topic.boot}")
    private String searchTopic;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepo, KafkaTemplate<String, User> kafkaTemplate) {
        this.userRepo = userRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    // this method is used to save a new user
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        kafkaTemplate.send(searchTopic, user);
        return userRepo.insert(user);
    }

    // this method is used update a existing user
    @Override
    public User updateUser(String userId, User user) throws UserNotFoundException {
        User fetch = userRepo.findById(userId).get();
        if (fetch != null) {
            userRepo.save(user);
        } else {
            throw new UserNotFoundException("user not found");
        }

        return user;
    }

    // this method is used to delete an existing user
    @Override
    public boolean deleteUser(String userId) throws UserNotFoundException {
        if (userRepo.findById(userId) != null) {
            userRepo.deleteById(userId);
        } else {
            throw new UserNotFoundException("user not found");
        }

        return true;
    }

    // this method is used to get a user by userId
    @Override
    public User getUserById(String userId) throws UserNotFoundException {
        User fetchUser;
        try {
            fetchUser = userRepo.findById(userId).get();
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("User not found");
        }

        return fetchUser;
    }

    // this method is used to get a user by userName
    @Override
    public User getUserByUserName(String userName) throws UserNotFoundException {
        User user;
        try {
            user = userRepo.getUserByUserName(userName);
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

    // this method is used to get the list of users
    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return userList;
    }
}
