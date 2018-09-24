package com.stackroute.tldm.model;

import java.util.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Java object to recreate it as a table in your database.
 */

@Document
public class User {

	@Id
	@NotBlank
	private String userId;
	@NotBlank
	private String password;
	@NotBlank
	private String userName;
	@NotBlank
	private String phoneNum;
	@NotBlank
	@Email
	private String userMail;
	private Date createdAt;
	
	 /*
     * This class should have six fields
     * (userId, password, userName, phoneNum, userMail, createdAt)
     * This class should
	 * also contain the getters and setters for the fields,
	 * parameterized constructor and toString method.
     */

	public User() {

	}

	public User(@NotBlank String userId, @NotBlank String password, @NotBlank String userName,
			@NotBlank String phoneNum, @NotBlank @Email String userMail, Date createdAt) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.userMail = userMail;
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", phoneNum=" + phoneNum
				+ ", userMail=" + userMail + ", createdAt=" + createdAt + "]";
	}

}