package com.stackroute.tldm.test.repository;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserId("sanju");
        user.setUserName("Swetha Selvaraj");
        user.setPhoneNum("12345");
        user.setUserMail("swe@gmail.com");
        user.setPassword("123456");
       // userRepository.save(user);//
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    
    public void registerUserTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals("sanju", fetchUser.getUserId());
    }

    @Test
    
    public void getUserByIdTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals("sanju", fetchUser.getUserId());
    }

    @Test
    
    public void getUserByUserNameTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.getUserByUserName("Swetha Selvaraj");
        Assert.assertEquals(user.getUserName(), fetchUser.getUserName());
    }

    @Test
    
    public void updateUserTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        fetchUser.setUserMail("swe@gmail.com");
        userRepository.save(fetchUser);
        fetchUser = userRepository.findById("sanju").get();
        Assert.assertEquals("swe@gmail.com", fetchUser.getUserMail());
    }
}
