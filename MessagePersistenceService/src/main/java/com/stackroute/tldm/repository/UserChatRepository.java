package com.stackroute.tldm.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tldm.model.Message;

@Repository
public interface UserChatRepository extends CassandraRepository<Message, UUID> {
}
