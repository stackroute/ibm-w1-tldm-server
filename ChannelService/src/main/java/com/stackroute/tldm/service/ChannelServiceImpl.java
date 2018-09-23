package com.stackroute.tldm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tldm.exception.ChannelAlreadyExistsException;
import com.stackroute.tldm.exception.ChannelNotFoundException;
import com.stackroute.tldm.model.Channel;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.ChannelRepository;

@Service
public class ChannelServiceImpl implements ChannelService {

	private ChannelRepository channelRepository;

	@Autowired
	public ChannelServiceImpl(ChannelRepository channelRepository) {

		this.channelRepository = channelRepository;

	}

	@Override
	public Channel createChannel(Channel channel) throws ChannelAlreadyExistsException {
		Channel createChannel = null;
		try {
			if (channelRepository.getChannelByChannelName(channel.getChannelName()) == null) {

				channel.setChannelCreatedDate(new Date());
				
				createChannel = channelRepository.insert(channel);
			} else {
				throw new ChannelAlreadyExistsException("Channel Already Exists");
			}
		} catch (ChannelNotFoundException e) {

			e.printStackTrace();
		}

		return createChannel;
	}

	@Override
	public Channel updateChannel(String channelId, Channel channel) throws ChannelNotFoundException {
		Channel fetch = channelRepository.findById(channelId).get();
		if (fetch != null) {
			channelRepository.save(channel);
		} else {
			throw new ChannelNotFoundException("Channel not found");
		}
		return channel;
	}

	@Override
	public boolean deleteChannel(String channelId) throws ChannelNotFoundException {
		if (channelRepository.findById(channelId) != null) {
			channelRepository.deleteById(channelId);
		} else {
			throw new ChannelNotFoundException("channel not found");
		}
		return true;
	}

	@Override
	public List<Channel> getAllChannels() {
		List<Channel> channelList = channelRepository.findAll();
		return channelList;
	}

	@Override
	public Channel getChannelByChannelName(String channelName) throws ChannelNotFoundException {
		Channel fetchChannel;
		try {
			fetchChannel = channelRepository.getChannelByChannelName(channelName);
		} catch (NoSuchElementException exception) {
			throw new ChannelNotFoundException("channel not found");
		}

		return fetchChannel;
	}

	@Override
	public List<User> findAllChannelUsersByChannelName(String channelName) {
		List<User> allUsers = null;
		try {
			allUsers = channelRepository.getChannelByChannelName(channelName).getChannelUsers();
		} catch (ChannelNotFoundException e) {

		}
		return allUsers;
	}

	@Override
	public Channel updateChannelUser(String channelId, User user) throws ChannelNotFoundException {

		Channel channel = channelRepository.findById(channelId).get();
		List<User> userList = new ArrayList<>();
		userList = channel.getChannelUsers();
		userList.add(user);
		channel.setChannelUsers(userList);
		channelRepository.save(channel);
		return channel;

	}

	@Override
	public List<Channel> getListOfChannelsByUsers(String userName) {
		List<Channel> channels = new ArrayList<>();
		List<Channel> channelList = channelRepository.findAll();
		System.out.println(channelList);
		Iterator iterator = channelList.iterator();
		while (iterator.hasNext()) {
			Channel eachChannel = (Channel) iterator.next();
			if (eachChannel != null) {
				List<User> channelUser = eachChannel.getChannelUsers();
				 System.out.println(channelUser);
				Iterator userIterator = channelUser.iterator();
				while (userIterator.hasNext()) {
					User eachUser = (User) userIterator.next();
					System.out.println("kkkkkkkk" + eachUser.getUserName());
					if (userName.equals(eachUser.getUserName())) {
						channels.add(eachChannel);
						System.out.println("kkkkkkkk" + channels);
						// channelRepository.save(channels);
					}
				}
			}			
			// if (channelUser.size() > 0) {
			// }
		}

		return channels;
	}

	@Override
	public boolean removeChannelUser(String channelId, String userId) {
		boolean flag = false;
		Channel channels = new Channel();
		channels = channelRepository.findById(channelId).get();
		List<User> channelUser = this.findAllChannelUsersByChannelName(channels.getChannelName());

		Iterator<User> iterator = channelUser.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getUserId().equals(userId)) {
				iterator.remove();
				flag = true;
				break;
			}
		}
		channels.setChannelUsers(channelUser);
		channelRepository.save(channels);
		System.out.println(flag);
		return flag;
	}

	@Override
	public List<Channel> getListOfChannelsByUser(String userId) {
		List<Channel> channels = new ArrayList<>();
		List<Channel> channelList = channelRepository.findAll();
		System.out.println(channelList);
		Iterator iterator = channelList.iterator();
		while (iterator.hasNext()) {
			Channel eachChannel = (Channel) iterator.next();
			if (eachChannel != null) {
				List<User> channelUser = eachChannel.getChannelUsers();
				 System.out.println(channelUser);
				Iterator userIterator = channelUser.iterator();
				while (userIterator.hasNext()) {
					User eachUser = (User) userIterator.next();
					System.out.println("kkkkkkkk" + eachUser.getUserId());
					if (userId.equals(eachUser.getUserId())) {
						channels.add(eachChannel);
						System.out.println("kkkkkkkk" + channels);
						// channelRepository.save(channels);
					}
				}
			}			
			// if (channelUser.size() > 0) {
			// }
		}

		return channels;
	}

}
