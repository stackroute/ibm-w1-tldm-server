package com.stackroute.tldm.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.stackroute.tldm.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.exception.UserNotFoundException;
import com.stackroute.tldm.repository.UserRepository;
import com.stackroute.tldm.service.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;
    private User user, user1;

    @InjectMocks
    private UserServiceImpl userService;
    private List<User> userList = null;
    private Optional<User> options;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setUserId("swedha12");
        user.setPhoneNum("56528769987");
        user.setUserName("swetha");
        user.setUserMail("swedha87@gmail.com");
        user.setCreatedAt(new Date());
        
        userList.add(user);
        user1 = new User();
        options = Optional.of(user);
    }
   
    
    @Ignore
   

    @Test
    public void registerUserSuccess() throws UserAlreadyExistsException {
        when(userRepo.insert((User) any())).thenReturn(user);
        User registerUser = userService.registerUser(user);
        assertEquals(user, registerUser);
    }
@Ignore
    @Test
    public void registerUserFailure() throws UserAlreadyExistsException {
        when(userRepo.insert((User) any())).thenReturn(user);
        User registerUser1 = userService.registerUser(user);
        assertNotEquals(user1, registerUser1);
    }
@Ignore
    @Test
    public void getUserByUserName() throws UserNotFoundException {
        when(userRepo.getUserByUserName(user.getUserName())).thenReturn(user);
        User fetchUser = userService.getUserByUserName(user.getUserName());
        assertEquals(user, fetchUser);
    }
@Ignore
    @Test
    public void deleteUserSuccess() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
        boolean flag = userService.deleteUser(user.getUserId());
        assertEquals(true, flag);
    }
@Ignore

    @Test
    public void getUserById() throws UserNotFoundException {
        when(userRepo.findById(user.getUserId())).thenReturn(options);
        User fetchUser = userService.getUserById(user.getUserId());
        assertEquals(user, fetchUser);
    }
}
	
	
