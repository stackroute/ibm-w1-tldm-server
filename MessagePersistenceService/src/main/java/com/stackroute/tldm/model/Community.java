package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.datastax.driver.core.DataType;

@UserDefinedType("Comminity")
public class Community {

	@CassandraType(type = DataType.Name.TEXT)
	private String communityId;
	
	@CassandraType(type = DataType.Name.TEXT)
	private String communityName;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "user")
	private List<User> communityUsers;
	
	@CassandraType(type = DataType.Name.DATE)
	private Date communityCreatedDate;
	
	@CassandraType(type = DataType.Name.TEXT)
	private User communityCreatedBy;
	
	@CassandraType(type = DataType.Name.UDT, userTypeName = "Channel")
	private List<Channel> channelList;

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