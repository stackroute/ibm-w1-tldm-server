package com.stackroute.tldm.repository;

import com.stackroute.tldm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserAuthRepository extends JpaRepository<User, String> {

	public User findUserByUserMail(String userEmail);

	public User findUserByuserId(String userId);

	public User findUserByUserIdAndPassword(String userId, String password);

}
