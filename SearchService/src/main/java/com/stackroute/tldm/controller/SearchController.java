package com.stackroute.tldm.controller;

import com.stackroute.tldm.model.User;
import com.stackroute.tldm.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-users")
@CrossOrigin("*")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> searchUserByNames(@PathVariable("name") String name) {
        ResponseEntity responseEntity = null;
        try {
            List<User> userList = searchService.getAllUsersByUserNameRegex(name);
            if (userList != null) {
                responseEntity = new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("No Match Found!", HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }
}
