package com.stackroute.tldm.service;

import com.stackroute.tldm.exception.MessageNotFoundException;
import com.stackroute.tldm.model.ChannelMessage;
import com.stackroute.tldm.model.Message;
import com.stackroute.tldm.repository.ChatRepository;
import com.stackroute.tldm.repository.GroupChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

	private ChatRepository chatRepository;
	private GroupChatRepository groupChatRepository;

	@Autowired
	public MessageServiceImpl(ChatRepository chatRepository, GroupChatRepository groupChatRepository) {
		this.chatRepository = chatRepository;
		this.groupChatRepository = groupChatRepository;
	}

	@Override
	public void saveMessage(Message message) {
		UUID newId = UUID.randomUUID();
		message.setMessageId(newId);
		message.setCreatedAt(new Date());
		chatRepository.insert(message);
	}

	@Override
	public void saveMessage(ChannelMessage channelMessage) {
		UUID newMessageId = UUID.randomUUID();
		channelMessage.setMessageId(newMessageId);
		channelMessage.setCreatedAt(new Date());
		groupChatRepository.insert(channelMessage);
	}

	@Override
	public boolean deleteMessage(UUID messageId) throws MessageNotFoundException {
		if (chatRepository.existsById(messageId)) {
			chatRepository.deleteById(messageId);
			return true;
		} else {
			throw new MessageNotFoundException("Message Not Found!");
		}
	}

	@Override
	public List<Message> getMessagesByUserIdAndReceiverId(String senderId, String receiverId)
			throws MessageNotFoundException {
		List<Message> messageList = chatRepository.findAll();
		List<Message> messages = new ArrayList<>();
		if (messageList != null) {
			ListIterator<Message> iterator = messageList.listIterator();
			while (iterator.hasNext()) {
				Message messageToGet = iterator.next();
				String sender = messageToGet.getSender().getUserId();
				String receiver = messageToGet.getReceiver().getUserId();
				if ((senderId.equals(sender) || senderId.equals(receiver))
						&& (receiverId.equals(receiver) || receiverId.equals(sender))) {
					messages.add(messageToGet);
				}
			}
			return messages;
		} else {
			throw new MessageNotFoundException("Message Not Found!");
		}
	}

}