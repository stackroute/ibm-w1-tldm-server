package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
   	
	void saveMessage(Message message);

	boolean deleteMessage(UUID messageId) throws MessageNotFoundException;

	List<Message> getMessagesBySenderIdAndReceiverId(String senderId, String receiverId) throws MessageNotFoundException;

}
