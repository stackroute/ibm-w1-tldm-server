package com.stackroute.tldm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Channel {

    private String channelId;
    private String channelName;
    private String channelCreatedBy;
    private String channelDescription;
    private Date channelCreatedDate;
    private Community communityName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> channelUsers;

    public Channel() {
        super();
    }

    public Channel(String channelId, String channelName, String createdBy, String channelDescription,
                   Date channelCreatedDate, Community communityName, List<User> channelUsers) {
        super();
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelCreatedBy = createdBy;
        this.channelDescription = channelDescription;
        this.channelCreatedDate = channelCreatedDate;
        this.communityName = communityName;
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

    public String getchannelCreatedBy() {
        return channelCreatedBy;
    }

    public void setchannelCreatedBy(String createdBy) {
        this.channelCreatedBy = createdBy;
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

    public Community getCommunityName() {
        return communityName;
    }

    public void setCommunityName(Community communityName) {
        this.communityName = communityName;
    }

    public List<User> getChannelUsers() {
        return channelUsers;
    }

    public void setChannelUsers(List<User> channelUsers) {
        this.channelUsers = channelUsers;
    }

    @Override
    public String toString() {
        return "Channel [channelId=" + channelId + ", channelName=" + channelName + ", createdBy=" + channelCreatedBy
                + ", channelDescription=" + channelDescription + ", channelCreatedDate=" + channelCreatedDate
                + ", communityName=" + communityName + ", channelUsers=" + channelUsers + "]";
    }

}
