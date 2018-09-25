package com.stackroute.tldm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

/*
 * The class "User" will be acting as the data model for the User Table in the database.
 * Java object to recreate it as a table in your database.
 */

@RedisHash("user")
public class User {

    @Id
    private String userId;
    private String password;
    private String userName;
    private String phoneNum;
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
        return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + ", phoneNum=" + phoneNum
                + ", userMail=" + userMail + ", createdAt=" + createdAt + "]";
    }

}