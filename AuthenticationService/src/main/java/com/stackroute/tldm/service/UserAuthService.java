package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;

public interface UserAuthService {

	public User registerUser(User user) throws  UserAlreadyExistsException;

	public User findUserByEmail(String email);

	public User findUserByUserIdAndPassword(String userId, String password);

}
