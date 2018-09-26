package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("Channel")
public class Channel {

    @CassandraType(type = DataType.Name.TEXT)
    private String channelId;
    @CassandraType(type = DataType.Name.TEXT)
    private String channelName;
    @CassandraType(type = DataType.Name.TEXT)
    private String channelCreatedBy;
    @CassandraType(type = DataType.Name.TEXT)
    private String channelDescription;
    @CassandraType(type = DataType.Name.TIMESTAMP)
    private Date channelCreatedDate;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "Community")
    private Community community;
    @CassandraType(type = DataType.Name.UDT, userTypeName = "user")
    private List<User> channelUsers;

    public Channel() {
        super();

    }

    public Channel(String channelId, String channelName, String channelcreatedBy, String channelDescription,
                   Date channelCreatedDate, Community community, List<User> channelUsers) {
        super();
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelCreatedBy = channelcreatedBy;
        this.channelDescription = channelDescription;
        this.channelCreatedDate = channelCreatedDate;
        this.community = community;
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

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<User> getChannelUsers() {
        return channelUsers;
    }

    public void setChannelUsers(List<User> channelUsers) {
        this.channelUsers = channelUsers;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelCreatedBy='" + channelCreatedBy + '\'' +
                ", channelDescription='" + channelDescription + '\'' +
                ", channelCreatedDate=" + channelCreatedDate +
                ", community=" + community +
                ", channelUsers=" + channelUsers +
                '}';
    }
}
