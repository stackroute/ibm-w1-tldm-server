package com.stackroute.tldm.model;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Date;
import java.util.List;

/*
 * The class "Community" will be acting as the data model for the Community Table in the database. 
 * Please note that this class is annotated with @UserdefinedType annotation. 
 * Java object to recreate it as a table in your database.
 */

@UserDefinedType("Community")
public class Community {

    @CassandraType(type = DataType.Name.TEXT)
    private String communityId;
    @CassandraType(type = DataType.Name.TEXT)
    private String communityName;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "user")
    private List<User> communityUsers;
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date communityCreatedDate;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "user")
    private User communityCreatedBy;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "user")
    private List<Channel> channelList;
    
    /*
     * This class should have six fields
     * (communityId, communityName, communityUsers, communityCreatedDate, communityCreatedBy, channelList)
     * This class should
	 * also contain the getters and setters for the fields,
	 * parameterized constructor and toString method.
     */

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

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public Community(String communityId, String communityName, List<User> communityUsers, Date communityCreatedDate,
                     User communityCreatedBy, List<Channel> channelList) {
        super();
        this.communityId = communityId;
        this.communityName = communityName;
        this.communityUsers = communityUsers;
        this.communityCreatedDate = communityCreatedDate;
        this.communityCreatedBy = communityCreatedBy;
        this.channelList = channelList;
    }

    public Community() {
        super();

    }

    @Override
    public String toString() {
        return "Community [communityId=" + communityId + ", communityName=" + communityName + ", communityUsers="
                + communityUsers + ", communityCreatedDate=" + communityCreatedDate + ", communityCreatedBy="
                + communityCreatedBy + ", channelList=" + channelList + "]";
    }

}
