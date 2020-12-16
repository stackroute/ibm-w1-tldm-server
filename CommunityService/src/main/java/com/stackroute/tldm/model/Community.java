package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Community {

    @Id
    private String communityId;
    private String communityName;
    private List<User> communityUsers;
    private List<Channel> channelsList;
    private Date communityCreatedDate;
    private User communityCreatedBy;

    public Community() {
        super();
    }

    public Community(String communityId, String communityName, List<User> communityUsers, List<Channel> channelsList,
                     Date communityCreatedDate, User communityCreatedBy) {
        super();
        this.communityId = communityId;
        this.communityName = communityName;
        this.communityUsers = communityUsers;
        this.channelsList = channelsList;
        this.communityCreatedDate = communityCreatedDate;
        this.communityCreatedBy = communityCreatedBy;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public List<User> getCommunityUsers() {
        return communityUsers;
    }

    public void setCommunityUsers(List<User> communityUsers) {
        this.communityUsers = communityUsers;
    }

    public List<Channel> getChannelsList() {
        return channelsList;
    }

    public void setChannelsList(List<Channel> channelsList) {
        this.channelsList = channelsList;
    }

    public Date getCommunityCreatedDate() {
        return communityCreatedDate;
    }

    public void setCommunityCreatedDate(Date communityCreatedDate) {
        this.communityCreatedDate = communityCreatedDate;
    }

    public User getCommunityCreatedBy() {
        return communityCreatedBy;
    }

    public void setCommunityCreatedBy(User communityCreatedBy) {
        this.communityCreatedBy = communityCreatedBy;
    }

    @Override
    public String toString() {
        return "Community{" +
                "communityId='" + communityId + '\'' +
                ", communityName='" + communityName + '\'' +
                ", communityUsers=" + communityUsers +
                ", channelsList=" + channelsList +
                ", communityCreatedDate=" + communityCreatedDate +
                ", communityCreatedBy=" + communityCreatedBy +
                '}';
    }
}