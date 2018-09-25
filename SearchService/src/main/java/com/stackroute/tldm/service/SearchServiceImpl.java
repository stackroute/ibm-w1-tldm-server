package com.stackroute.tldm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tldm.model.User;
import com.stackroute.tldm.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class SearchServiceImpl implements SearchService {

    private SearchRepository searchRepository;
    private SimpMessagingTemplate template;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository, SimpMessagingTemplate template) {
        this.searchRepository = searchRepository;
        this.template = template;
    }

    @KafkaListener(topics = "${search-topic.boot}", groupId = "${groupId.boot}")
    public void receiveUser(@Payload User user) {
        searchRepository.insert(user);
    }

    @Override
    public void getUserByName(String userName) {
        searchRepository.findByUserNameRegex(userName);
    }
}
