package com.stackroute.tldm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.model.Receiver;
import com.stackroute.tldm.model.Sender;
import com.stackroute.tldm.repository.ChatRepository;
import static org.mockito.Mockito.when;

public class MessageServiceImplTest {

	@Mock
	ChatRepository chatRepository;

	Optional<Message> options;
	List<Message> messageList = null;

	Message message;
	Sender sender;
	Receiver receiver;
	@InjectMocks
	MessageServiceImpl messageService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		message = new Message();
		message.setId("Manik123");
		message.setMessageContent("Hello");
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setCreatedAt(new Date());
		options = Optional.of(message);

		messageList = new ArrayList<>();
		messageList = chatRepository.findAll();

		sender = new Sender();
		sender.setUserId("1");
		sender.setUserName("mggmanik");
		sender.setName("Manik");
		sender.setUserMail("mggmanik@gmail.com");
		sender.setPhoneNum("7060186830");

		receiver = new Receiver();
		receiver.setUserId("2");
		receiver.setUserName("adiwakar");
		receiver.setName("diwakar");
		receiver.setUserMail("diwakar@gmail.com");
		receiver.setPhoneNum("934567897");

	}

	@Test(expected = MessageNotFoundException.class)
	public void deleteMessageFailureTest() throws MessageNotFoundException {
		when(chatRepository.findById(message.getId())).thenReturn(options);
		boolean flag = messageService.deleteMessage(message.getId());
		assertEquals(false, flag);

	}

	@Test
	public void deleteMessageSuccessTest() throws MessageNotFoundException {
		when(chatRepository.findById(message.getId())).thenReturn(options);
		boolean flag = messageService.deleteMessage(message.getId());
		assertEquals(true, flag);
	}

	@Test
	public void getMessagesSuccessTest() throws MessageNotFoundException {
		when(chatRepository.findById("Manik123")).thenReturn(options);
		List<Message> messages = messageService.getMessagesByUserIdAndReceiverId("1", "2");
		Assert.assertEquals(messageList, messages);
	}

	@Test(expected = MessageNotFoundException.class)
	public void getMessagesFailureTest() throws MessageNotFoundException {
		when(chatRepository.findById("Manik123")).thenReturn(null);
		List<Message> messages = messageService.getMessagesByUserIdAndReceiverId("4", "2");
		Assert.assertEquals(messageList, messages);
	}

}
