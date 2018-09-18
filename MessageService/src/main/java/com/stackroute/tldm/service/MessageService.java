package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    
	void saveMessage(ChannelMessage channelMessage);
	
	void saveMessage(Message message);

	boolean deleteMessage(UUID messageId) throws MessageNotFoundException;

	List<Message> getMessagesByUserIdAndReceiverId(String senderId, String receiverId) throws MessageNotFoundException;

}
