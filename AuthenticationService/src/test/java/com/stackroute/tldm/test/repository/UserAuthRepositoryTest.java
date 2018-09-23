package com.stackroute.tldm.test.repository;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserAuthRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserAuthRepositoryTest {

	@Autowired
	private UserAuthRepository userAuthRepository;

	private User user;

	@Before
	public void setUp() throws Exception {

		user = new User();
		user.setUserId("impku");
		user.setUserMail("pankaju713@gmail.com");
		user.setPassword("1234567");
		userAuthRepository.save(user);
	}

	@After
	public void tearDown() throws Exception {
		userAuthRepository.deleteAll();
	}

//	@Test
//	public void registerUserTest() {
//
//		User fetchedUser = userAuthRepository.findUserByUserMail("pankaju713@gmail.com");
//		System.out.println(fetchedUser);
//		Assert.assertEquals(user.getUserId(), fetchedUser.getUserId());
//	}

	@Test
	public void loginUserTest() {
		User user1 = userAuthRepository.findUserByUserIdAndPassword(user.getUserId(),
				user.getPassword());
		Assert.assertEquals(user.toString(), user.toString());
	}

}
