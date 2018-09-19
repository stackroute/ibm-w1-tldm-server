package com.stackroute.tldm.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tldm.model.ChannelMessage;

@Repository
public interface GroupChatPersistRepository extends CassandraRepository<ChannelMessage, UUID> {

}
