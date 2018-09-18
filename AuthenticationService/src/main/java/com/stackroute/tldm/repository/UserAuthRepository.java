package com.stackroute.tldm.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tldm.model.User;

@Repository
@Transactional
public interface UserAuthRepository extends JpaRepository<User, String> {

	public User findUserByEmail(String email);

	public User findUserByuserId(String userId);

	public User findUserByUserIdAndPassword(String userId, String password);

}
