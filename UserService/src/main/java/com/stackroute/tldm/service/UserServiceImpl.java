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
 //this method should be used to save a new user//

	public UserModel registerUser(UserModel user) throws UserAlreadyExistsException {
                UserModel registeredUser;
		if (userRepo.getUserByUserName(user.getUserName()) != null) {
			throw new UserAlreadyExistsException("user exists");
		} else {
		registeredUser = userRepo.insert(user);
		}
		return registeredUser;

	}

    //this method is used update a existing user//

	public UserModel updateUser(String movieId, UserModel user) throws UserNotFoundException {
		UserModel fetch = userRepo.findById(user.getUserId()).get();
		if (fetch != null) {
			userRepo.save(user);

		} else {
			throw new UserNotFoundException("user not found");
		}
		return user;
	}
 //this method is used to delete an existing user//


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
//this method is used to get a user by userId//


	public UserModel getUserById(String userId) throws UserNotFoundException {
		UserModel fetchUser;
		try {
			fetchUser = userRepo.findById(userId).get();
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("User not found");
		}
		return fetchUser;
	}
//this method is used to get a user by userName//

	public UserModel getUserByUserName(String userName) throws UserNotFoundException {
		UserModel user;
		try {
			user = userRepo.getUserByUserName(userName);
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}

	@Override
	public UserModel getUserByName(String name) throws UserNotFoundException {
		UserModel user;
		try {
			user = userRepo.getUserByName(name);
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}
//this method is used to get the list of users//

	public List<UserModel> getAllUsers() {
		List<UserModel> userList = userRepo.findAll();
		return userList;
	}
}
