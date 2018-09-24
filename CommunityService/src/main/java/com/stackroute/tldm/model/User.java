
package com.stackroute.tldm.model;

import java.util.Date;

/*
 * The class "Channel" will be acting as the data model for the Channel Table in the database. 
 * Java object to recreate it as a table in your database.
 */

public class User {

    private String userId;
    private String userName;
    private String userMail;
    private String userPhoneNum;
    private Date   userCreatedAt;
    
    /*
     * This class should have five fields
     * (userId, userName, userMail, userPhoneNum, userCreatedAt)
     * This class should
	 * also contain the getters and setters for the fields,
	 * parameterized constructor and toString method.
     */

    public User() {
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

	public String getUserPhoneNum() {
		return userPhoneNum;
	}

	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public Date getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(Date userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public User(String userId, String userName, String userMail, String userPhoneNum, Date userCreatedAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userMail = userMail;
		this.userPhoneNum = userPhoneNum;
		this.userCreatedAt = userCreatedAt;
	}

}
