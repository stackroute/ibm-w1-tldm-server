package com.stackroute.tldm.test.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.stackroute.tldm.model.ChannelMessage;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ChannelMessagePersistenceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ChannelMessage channelMessage;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
