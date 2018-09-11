package com.stackroute.tldm.service;

import java.util.List;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;

public interface UserService {
	boolean registerUser(UserModel user) throws UserAlreadyExistsException;

	UserModel updateUser(String userId, UserModel user) throws UserNotFoundException;

	boolean deleteUser(String userId) throws UserNotFoundException;

	public UserModel getUserById(String userId) throws UserNotFoundException;

	public UserModel getUserByUserName(String userName) throws UserNotFoundException;
	
	public UserModel getUserByName(String name) throws UserNotFoundException;
	
	public List<UserModel> getAllUsers();
}
