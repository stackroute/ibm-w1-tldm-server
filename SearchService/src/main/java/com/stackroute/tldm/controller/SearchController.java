package com.stackroute.tldm.controller;

import com.stackroute.tldm.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/v1/search-users")
    public ResponseEntity<?> searchUserByNames(String name) {
        searchService.getUserByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
