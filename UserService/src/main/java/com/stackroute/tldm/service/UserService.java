package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.model.UserModel;

public interface UserService {
	UserModel registerUser(UserModel user) throws UserAlreadyExistsException;

	UserModel updateUser(String userId, UserModel user) throws UserNotFoundException;

	boolean deleteUser(String userId) throws UserNotFoundException;

	public UserModel getUserById(String userId);

	public UserModel getUserByUserName(String userName);

}
