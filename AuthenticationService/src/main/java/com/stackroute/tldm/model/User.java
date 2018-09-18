package com.stackroute.tldm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	
	private String userId;
	@NotBlank
       @Email
	private String email;
	@NotBlank
	private String password;

	public User() {
		super();
	}

	public User(String userId, String email, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

}
