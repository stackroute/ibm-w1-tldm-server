package com.stackroute.tldm.controller;

import com.stackroute.tldm.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @MessageMapping("/search")
    public void searchNames(String name) throws Exception {
        searchService.getUsersByName(name);
    }
}
