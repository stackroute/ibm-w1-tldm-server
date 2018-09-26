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

    // this method is used to create a channel
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

    // this method is used to update a channel
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

    // this method is used to delete a channel
    @Override
    public boolean deleteChannel(String channelId) throws ChannelNotFoundException {
        if (channelRepository.findById(channelId) != null) {
            channelRepository.deleteById(channelId);
        } else {
            throw new ChannelNotFoundException("channel not found");
        }

        return true;
    }

    // this method is used to get all the channels
    @Override
    public List<Channel> getAllChannels() {
        List<Channel> channelList = channelRepository.findAll();

        return channelList;
    }

    // this method is used to get the channel details by channel name
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

    // this method is used to list all the users of a particular channel
    @Override
    public List<User> findAllChannelUsersByChannelName(String channelName) {
        List<User> allUsers = null;
        try {
            allUsers = channelRepository.getChannelByChannelName(channelName).getChannelUsers();
        } catch (ChannelNotFoundException e) {

        }

        return allUsers;
    }

    // this method is used to list all the channels where a particular userName is present
    @Override
    public Channel updateChannelUser(String channelId, List<User> users) throws ChannelNotFoundException {
        Channel channel = channelRepository.findById(channelId).get();
        List<User> userList = channel.getChannelUsers();
        for (User user : users) {
            userList.add(user);
        }
        channel.setChannelUsers(userList);
        System.out.println(channelRepository.save(channel));

        return channel;
    }

   

    // this method is used to remove a user from a channel
    @Override
    public boolean removeChannelUser(String channelId, String userId) {
        boolean flag = false;
        Channel channels = channelRepository.findById(channelId).get();
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

        return flag;
    }

    // this method is used to list all the channels where a particular userId is present
    @Override
    public List<Channel> getListOfChannelsByUser(String userId) {
        List<Channel> channels = new ArrayList<>();
        List<Channel> channelList = channelRepository.findAll();
        Iterator iterator = channelList.iterator();
        while (iterator.hasNext()) {
            Channel eachChannel = (Channel) iterator.next();
            if (eachChannel != null) {
                List<User> channelUser = eachChannel.getChannelUsers();
                Iterator userIterator = channelUser.iterator();
                while (userIterator.hasNext()) {
                    User eachUser = (User) userIterator.next();
                    if (userId.equals(eachUser.getUserId())) {
                        channels.add(eachChannel);
                    }
                }
            }
        }

        return channels;
    }

    @Override
    public Channel getChannelByChannelId(String channelId) throws ChannelNotFoundException {
        Channel fetchChannel;
        try {
            fetchChannel = channelRepository.findById(channelId).get();
        } catch (NoSuchElementException exception) {
            throw new ChannelNotFoundException("channel not found");
        }

        return fetchChannel;
    }

}
