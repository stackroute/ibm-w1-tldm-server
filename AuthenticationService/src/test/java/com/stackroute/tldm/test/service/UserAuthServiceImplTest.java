package com.stackroute.tldm.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserAuthRepository;
import com.stackroute.tldm.service.UserAuthServiceImpl;

public class UserAuthServiceImplTest {

	@Mock
	UserAuthRepository userAuth;

	User user, user1;

	@InjectMocks
	UserAuthServiceImpl userImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user1 = new User();
	}

	@Test
	public void registerUserSuccess() throws UserAlreadyExistsException {
		when(userAuth.save((User) any())).thenReturn(user);
		User regUser = userImpl.registerUser(user);
		assertEquals(user, regUser);
	}

	@Test
	public void registerUserFailure() throws UserAlreadyExistsException {
		when(userAuth.save((User) any())).thenReturn(user);
		User regUser = userImpl.registerUser(user);
		assertNotEquals(user1, regUser);

	}

}
