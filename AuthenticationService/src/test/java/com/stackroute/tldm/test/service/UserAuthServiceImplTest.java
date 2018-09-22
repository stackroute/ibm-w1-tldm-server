package com.stackroute.tldm.test.service;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.UserAuthRepository;
import com.stackroute.tldm.service.UserAuthServiceImpl;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

	

}
