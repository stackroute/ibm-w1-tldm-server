package com.stackroute.tldm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.tldm.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	UserModel getUserByUserName(String userName);

}
