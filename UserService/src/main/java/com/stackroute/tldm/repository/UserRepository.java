package com.stackroute.tldm.repository;

import com.stackroute.tldm.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User getUserByUserId(String userName);

    User getUserByUserName(String name);
}
