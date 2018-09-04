package com.stackroute.tldm.model;

public class Sender {

    private int userId;
    private String userName;
    private String name;
    private String userMail;
    private String phoneNum;

    public Sender() {
    }

    public Sender(int userId, String userName, String name, String userMail, String phoneNum) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.userMail = userMail;
        this.phoneNum = phoneNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    @Override
    public String toString() {
        return "Sender{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", userMail='" + userMail + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
