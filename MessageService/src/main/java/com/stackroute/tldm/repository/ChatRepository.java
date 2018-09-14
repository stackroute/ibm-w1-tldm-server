package com.stackroute.tldm.repository;

import com.stackroute.tldm.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Message, String> {
}
