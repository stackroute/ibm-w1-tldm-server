package com.stackroute.tldm.service;



import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;
import com.stackroute.tldm.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean registerUser(UserModel user) throws UserAlreadyExistsException {
        boolean flag = false;
        if (userRepo.getUserByUserName(user.getUserName()) != null) {
            throw new UserAlreadyExistsException("user exists");
        } else {
            userRepo.insert(user);
            flag = true;
        }
        return flag;

    }

    public UserModel updateUser(String movieId, UserModel user) throws UserNotFoundException {
        UserModel fetch = userRepo.findById(user.getUserId()).get();
        if (fetch != null) {
            userRepo.save(user);

        } else {
            throw new UserNotFoundException("user not found");
        }
        return user;
    }

    public boolean deleteUser(String userId) throws UserNotFoundException {
        boolean flag = false;
        if (userRepo.findById(userId) != null) {
            userRepo.deleteById(userId);
            flag = true;
        } else {
            throw new UserNotFoundException("user not found");
        }

        return flag;
    }

    public UserModel getUserById(String userId) throws UserNotFoundException {
    	UserModel fetchUser;
    	try {
        fetchUser = userRepo.findById(userId).get();
    	}
    	catch(NoSuchElementException exception){
    		throw new UserNotFoundException("User not found");
    	}
        return fetchUser;
    }

    public UserModel getUserByUserName(String userName) throws UserNotFoundException {
    	UserModel user;
    	try {
        user = userRepo.getUserByUserName(userName);
    	}
    	catch(NoSuchElementException exception) {
    		 throw new UserNotFoundException("User not found");
    	}
        return user;
    }

    
    public List<UserModel> getAllUsers(){
        List<UserModel> userList = userRepo.findAll();
        return userList;
    }
}
