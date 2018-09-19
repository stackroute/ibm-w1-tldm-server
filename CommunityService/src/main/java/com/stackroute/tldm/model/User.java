
package com.stackroute.tldm.model;

public class User {

    private String userId;
    private String userName;
    private String userMail;
    private String userPhoneNum;

    public User() {
    }

    public User(String userId, String userName, String userMail, String userPhoneNum) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPhoneNum = userPhoneNum;
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

	@Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPhoneNum='" + userPhoneNum + '\'' +
                '}';
    }
}
