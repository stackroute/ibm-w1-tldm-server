package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;

public interface UserAuthService {

    User registerUser(User user) throws UserAlreadyExistsException;

    User findUserByUserEmail(String email);

    User findUserByUserIdAndPassword(String userId, String password);


}
