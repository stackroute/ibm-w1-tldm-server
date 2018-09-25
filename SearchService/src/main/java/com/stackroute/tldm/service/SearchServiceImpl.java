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
    public void receiveUserList(@Payload List<User> userList) {

        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.convertValue(userList, List.class);
        ListIterator<User> iterator = users.listIterator();
        while (iterator.hasNext()) {
            User user = mapper.convertValue(iterator.next(), User.class);
            searchRepository.save(user);
        }

    }

    @Override
    public void getUsersByName(String userName) {

        List<User> users = new ArrayList<>();
        List<User> userList = (List<User>) searchRepository.findAll();
        ListIterator<User> iterator = userList.listIterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUserName().equals(userName)) {
                users.add(user);
            }
        }

        template.convertAndSend("/topic/search-user", users);
    }
}
