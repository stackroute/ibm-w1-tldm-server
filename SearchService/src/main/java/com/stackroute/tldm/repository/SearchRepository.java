package com.stackroute.tldm.repository;

import com.stackroute.tldm.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SearchRepository extends MongoRepository<User, String> {

    List<User> findByUserNameRegex(String userName);
}
