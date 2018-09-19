package com.stackroute.tldm.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tldm.model.ChannelMessage;

@Repository
public interface GroupChatRepository extends MongoRepository<ChannelMessage, UUID>{

}
