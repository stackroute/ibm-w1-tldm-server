package com.stackroute.tldm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.tldm.model.Community;

@Repository
public interface CommunityRepository extends MongoRepository<Community, String> {

    Community getCommunityByCommunityName(String communityName);

   

}
