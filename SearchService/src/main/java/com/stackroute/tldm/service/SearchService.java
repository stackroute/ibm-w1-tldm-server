package com.stackroute.tldm.service;


import com.stackroute.tldm.model.User;

import java.util.List;

public interface SearchService {

    List<User> getAllUsersByUserNameRegex(String userName);
}
