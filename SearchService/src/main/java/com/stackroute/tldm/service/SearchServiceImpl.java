package com.stackroute.tldm.service;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

// receiveUser() method annotated with @KafkaListener becomes the consumer listening from User-Service.
@Service
public class SearchServiceImpl implements SearchService {

    private SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @KafkaListener(topics = "search_user", groupId = "search_user_group")
    public void receiveUser(@Payload User user) {
        searchRepository.insert(user);
    }

    @Override
    public List<User> getAllUsersByUserNameRegex(String userName) {
        List<User> userList = searchRepository.findAllByUserNameRegex(userName);

        return userList;
    }
}
