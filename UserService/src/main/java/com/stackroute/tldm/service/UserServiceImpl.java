package com.stackroute.tldm.service;

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
        if (userRepo.existsById(user.getUserId())) {
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

    public UserModel getUserById(String userId) {
        UserModel fetchMovie = userRepo.findById(userId).get();
        return fetchMovie;
    }

    public UserModel getUserByUserName(String movieName) {
        UserModel user = userRepo.getUserByUserName(movieName);
        return user;
    }

}
