package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;

// Model for Channel.

/*
 * The class "Channel" will be acting as the data model for the Channel Table in the database. 
 * Java object to recreate it as a table in your database.
 */


public class Channel {

	private String channelId;
	private String channelName;
	private String channelCreatedBy;
	private String channelDescription;
	private Date channelCreatedDate;
	private Community community;
	private List<User> channelUsers;
	
	 /*
     * This class should have seven fields
     * (channelId, channelname, channelCreatedBy, channelDescription, channelCreatedDate, community, channelUsers)
     * This class should
	 * also contain the getters and setters for the fields,
	 * parameterized constructor and toString method.
     */


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

	public Channel(String channelId, String channelName, String channelCreatedBy, String channelDescription,
			Date channelCreatedDate, Community community, List<User> channelUsers) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.channelCreatedBy = channelCreatedBy;
		this.channelDescription = channelDescription;
		this.channelCreatedDate = channelCreatedDate;
		this.community = community;
		this.channelUsers = channelUsers;
	}

	public Channel() {
		super();

	}

	@Override
	public String toString() {
		return "Channel [channelId=" + channelId + ", channelName=" + channelName + ", channelCreatedBy=" + channelCreatedBy
				+ ", channelDescription=" + channelDescription + ", channelCreatedDate=" + channelCreatedDate
				+ ", channelUsers=" + channelUsers + "]";
	}

}