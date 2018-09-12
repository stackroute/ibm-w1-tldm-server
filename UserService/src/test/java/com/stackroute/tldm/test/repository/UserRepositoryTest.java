package com.stackroute.tldm.test.repository;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUserId("john123");
        user.setUserName("Swetha Selvaraj");
        user.setPhoneNum("12345");
        user.setName("swe");
        user.setUserMail("swe@gmail.com");
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @Ignore
    public void registerUserTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.findById("john123").get();
        Assert.assertEquals(user.getUserId(), fetchUser.getUserId());
    }

    @Test
    @Ignore
    public void getUserByIdTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.findById("john123").get();
        Assert.assertEquals(user.getUserId(), fetchUser.getUserId());
    }

    @Test
    @Ignore
    public void getUserByUserNameTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.getUserByUserName("Swetha Selvaraj");
        Assert.assertEquals(user.getUserName(), fetchUser.getUserName());
    }

    @Test
    @Ignore
    public void updateUserTest() {
        userRepository.insert(user);
        User fetchUser = userRepository.findById("john123").get();
        fetchUser.setUserMail("swe@gmail.com");
        userRepository.save(fetchUser);
        fetchUser = userRepository.findById("john123").get();
        Assert.assertEquals("swe@gmail.com", fetchUser.getUserMail());
    }
}
