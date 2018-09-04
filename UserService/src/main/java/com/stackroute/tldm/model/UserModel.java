package com.stackroute.tldm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserModel {

	@Id
	private String userId;
	private String userName;
	private String name;
	private String userPhoneNumber;
	private String userMail;

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", userName=" + userName + ", name=" + name + ", userPhoneNumber="
				+ userPhoneNumber + ", userMail=" + userMail + "]";
	}

	public UserModel(String userId, String userName, String name, String userPhoneNumber, String userMail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.userPhoneNumber = userPhoneNumber;
		this.userMail = userMail;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public UserModel() {

	}
}
