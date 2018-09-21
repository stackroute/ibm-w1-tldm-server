package com.stackroute.tldm.repository;

import com.stackroute.tldm.model.ChannelMessage;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChannelChatRepository extends CassandraRepository<ChannelMessage, UUID> {
}
