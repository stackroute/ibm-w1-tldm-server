package com.stackroute.tldm.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

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

import com.stackroute.tldm.controller.MessageController;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.MessageResponse;
import com.stackroute.tldm.model.Receiver;
import com.stackroute.tldm.model.Sender;
import com.stackroute.tldm.service.MessageService;


@RunWith(SpringRunner.class)
@WebMvcTest

public class MessageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private Message message;
	@MockBean
	private MessageResponse messageResponse;
	@MockBean
	private Receiver receiver;
	@MockBean
	private Sender sender;
	@MockBean
	private MessageService messageService;
	@InjectMocks
	private MessageController messageController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();

		// Message
		message = new Message();
		message.setId("123");
		message.setMessageContent("this message is to test the working of our application");
		message.setCreatedAt(new Date());
		message.setReceiver(receiver);
		message.setSender(sender);

		// MessageResponse
		messageResponse.setContent("this is to test our application");
		messageResponse.setReceiver(receiver);
		messageResponse.setSender(sender);
		messageResponse.setTime("02:19:22");

		// Sender

		sender.setName("Richard Christoper");
		sender.setPhoneNum("9933676790");
		sender.setUserId("John123");
		sender.setUserMail("john12@gmail.com");
		sender.setUserName("Richard Christoper");

		// Receiver

		receiver.setName("Gayathri");
		receiver.setPhoneNum("9988775566");
		receiver.setUserId("gaya22");
		receiver.setUserMail("gayathri97@gmail.com");
		receiver.setUserName("Gayathri");

	}

	@Test
	public void deleteMessageSuccess() throws Exception {
		when(messageService.deleteMessage("123")).thenReturn(true);
		mockMvc.perform(delete("/api/v1/message/123")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
	}
	@Test
	public void deleteMessageFailure() throws Exception {
		when(messageService.deleteMessage("123")).thenReturn(false);
		mockMvc.perform(delete("/api/v1/message/123")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

	}
	
	
	@Test
	public void getMessagesByUserAndReceiverSuccess() throws Exception {
		List<Message> messageList=messageService.getMessagesByUserIdAndReceiverId("John123", "gaya22");
		when(messageService.getMessagesByUserIdAndReceiverId("John123", "gaya22")).thenReturn(messageList);
		mockMvc.perform(get("/api/v1/message/John123/gaya22")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
		
	}
	

	@Test
	public void getMessagesByUserAndReceiverFailure() throws Exception {
		
		when(messageService.getMessagesByUserIdAndReceiverId("John123", "gaya22")).thenReturn(null);
		mockMvc.perform(get("/api/v1/message/John123/gaya22")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());

}
}
