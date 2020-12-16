package com.stackroute.tldm.test.service;

import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserAuthRepository;
import com.stackroute.tldm.service.UserAuthServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import static springfox.documentation.builders.RequestHandlerSelectors.any;

public class UserAuthServiceImplTest {

	@Mock
	UserAuthRepository userAuth;
  User user , user1;

	@InjectMocks
	UserAuthServiceImpl userImpl;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		 user = new User();
		 user1 = new User();
	}
   @Ignore
	@Test
	public void registerUserSuccess() throws UserAlreadyExistsException {
		when(userAuth.save((User) any())).thenReturn(user);
		User regUser = userImpl.registerUser(user);
		assertEquals(user, regUser);
	}
    @Ignore
	@Test
	public void registerUserFailure() throws UserAlreadyExistsException {
		when(userAuth.save((User) any())).thenReturn(user);
		User regUser = userImpl.registerUser(user);
		assertNotEquals(user1, regUser);

	}

}
