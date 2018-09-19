package com.stackroute.tldm.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tldm.controller.UserAuthController;
import com.stackroute.tldm.exception.UserAlreadyExistsException;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.UserAuthService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserAuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private User user;
	@MockBean
	UserAuthService userAuthService;
	@InjectMocks
	UserAuthController userAuthController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userAuthController).build();
		user = new User();
		user.setUserId("impku");
		user.setEmail("pankaju713@gmail.com");
		user.setPassword("1234567");
		
		
	}

	@Test
	public void registerUserSuccess() throws Exception {

		when(userAuthService.registerUser(user)).thenReturn(user);
		mockMvc.perform(post("/user/auth").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void registerUserFailure() throws Exception {

		when(userAuthService.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
		mockMvc.perform(post("/user/auth").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void loginUserSuccess() throws Exception {
		when(userAuthService.findUserByUserIdAndPassword(user.getUserId(),
				user.getPassword())).thenReturn(user);
		mockMvc.perform(
				post("/user/auth/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void loginUserFailure() throws Exception {
		when(userAuthService.findUserByUserIdAndPassword(user.getUserId(),
				user.getPassword())).thenReturn(null);
		mockMvc.perform(
				post("/user/auth/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());

	}

}