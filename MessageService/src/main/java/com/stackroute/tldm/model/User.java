package com.stackroute.tldm.model;

import java.util.Date;

// Model for User.

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Java object to recreate it as a table in your database.
 */

public class User {
	private String userId;
	private String userName;
	private String userMail;
	private String phoneNum;
	private Date createdAt;
	
	 /*
     * This class should have five fields
     * (userId, userName, userMail, phoneNum, createdAt)
     * This class should
	 * also contain the getters and setters for the fields,
	 * parameterized constructor and toString method.
     */

	public User() {
	}

	public User(String userId, String userName, String userMail, String phoneNum, Date createdAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.phoneNum = phoneNum;
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userMail=" + userMail + ", phoneNum=" + phoneNum
				+ ", createdAt=" + createdAt + "]";
	}

}