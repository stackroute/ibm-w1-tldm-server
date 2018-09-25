package com.stackroute.tldm.repository;

import com.stackroute.tldm.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends CrudRepository<User, String> {
}
