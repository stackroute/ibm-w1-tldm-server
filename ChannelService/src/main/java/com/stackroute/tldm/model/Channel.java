package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Channel {

    @Id
    private String channelId;
    private String channelName;
    private String channelCreatedBy;
    private String channelDescription;
    private Date channelCreatedDate;
  
    private List<User> channelUsers;

    public Channel() {
        super();

    }

    public Channel(String channelId, String channelName, String channelCreatedBy, String channelDescription,
                   Date channelCreatedDate,  List<User> channelUsers) {
        super();
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelCreatedBy = channelCreatedBy;
        this.channelDescription = channelDescription;
        this.channelCreatedDate = channelCreatedDate;
       
        this.channelUsers = channelUsers;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCreatedBy() {
        return channelCreatedBy;
    }

    public void setChannelCreatedBy(String channelCreatedBy) {
        this.channelCreatedBy = channelCreatedBy;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public Date getChannelCreatedDate() {
        return channelCreatedDate;
    }

    public void setChannelCreatedDate(Date channelCreatedDate) {
        this.channelCreatedDate = channelCreatedDate;
    }

    
    public List<User> getChannelUsers() {
        return channelUsers;
    }

    public void setChannelUsers(List<User> channelUsers) {
        this.channelUsers = channelUsers;
    }

    @Override
    public String toString() {
        return "Channel [channelId=" + channelId + ", channelName=" + channelName + ", channelCreatedBy="
                + channelCreatedBy + ", channelDescription=" + channelDescription + ", channelCreatedDate="
                + channelCreatedDate + ", channelUsers=" + channelUsers + "]";
    }
}
