package com.stackroute.tldm.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String userId;
    private String userName;
    private String phoneNum;
    private String userMail;
    private Date createdAt;
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", phoneNum=" + phoneNum + ", userMail=" + userMail
				+ ", createdAt=" + createdAt + "]";
	}
	
	public User() {
		
	}
	public User(String userId, String userName, String phoneNum, String userMail, Date createdAt) {
		super();
		this.userId = userId;
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

}