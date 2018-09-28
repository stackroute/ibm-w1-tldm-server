package com.stackroute.tldm.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.stackroute.tldm.model.User;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.repository.UserRepository;
import com.stackroute.tldm.service.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;
    @MockBean
    private User user;

    @InjectMocks
    private UserServiceImpl userService;
    private List<User> userList = null;
    private Optional<User> options;

    @Before
    public void setUp() throws Exception {
    	
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setUserId("swedha123");
        user.setPassword("swe123");
        user.setPhoneNum("56528769987");
        user.setUserName("swetha");
        user.setUserMail("swedha87@gmail.com");
        user.setCreatedAt(new Date());
        //userRepo.save(user);
        userList = new ArrayList<>();
       userList.add(user);
       
        options = Optional.of(user);
    }
   
    


    

    @Test
    public void getUserByUserNameSuccess() throws UserNotFoundException {
        when(userRepo.getUserByUserName("swetha")).thenReturn(user);
        User fetchUser = userService.getUserByUserName("swetha");
        assertEquals(user, fetchUser);
    }

    @Test
    public void deleteUserSuccess() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
        boolean flag = userService.deleteUser(user.getUserId());
        assertEquals(true, flag);
    }
    
    
    
    @Test
    public void updateChannelSuccess() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
         User updateUser = userService.updateUser(user.getUserId(), user);
        assertEquals(user, updateUser);
    }
    @Test
    public void updateUserFailure() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
        User updateUser = userService.updateUser(user.getUserId(), user);
        assertEquals(user, updateUser);
    }
    @Test
    public void deleteChannelFailure() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
        boolean flag = userService.deleteUser(user.getUserId());
        assertEquals(true, flag);
    }


    @Test
    public void getUserByNameSucess() throws UserNotFoundException {
        when(userRepo.findById(user.getUserName())).thenReturn(options);
               User fetchUser = userService.getUserById(user.getUserName());
        assertEquals(user, fetchUser);
    }
    

    @Test
    public void getUserByNameFailure() throws UserNotFoundException {
        when(userRepo.findById(user.getUserName())).thenReturn(options);
               User fetchUser = userService.getUserById(user.getUserName());
        assertEquals(user, fetchUser);
    }
    @Test
    public void getUserByIdSucess() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
               User fetchUser = userService.getUserById(user.getUserId());
        assertEquals(user, fetchUser);
    }
    

    @Test
    public void getUserByIdFailure() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
               User fetchUser = userService.getUserById(user.getUserId());
        assertEquals(user, fetchUser);
    }
}