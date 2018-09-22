package com.stackroute.tldm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    private String userId;
    private String password;
    private String userName;
    private String phoneNum;
    private String userMail;
    private Date createdAt;

    public User() {
        super();
    }

    public User(String userId, String password, String userName, String phoneNum, String userMail, Date createdAt) {
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
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", userMail='" + userMail + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
