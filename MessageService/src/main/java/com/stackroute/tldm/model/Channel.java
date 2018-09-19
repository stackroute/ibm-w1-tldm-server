package com.stackroute.tldm.model;

import java.util.Date;
import java.util.List;

public class Channel {

	private String channelId;
	private String channelName;
	private String createdBy;
	private String channelDescription;
	private Date channelCreatedDate;
	private Community community;
	private List<User> channelUsers;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Channel(String channelId, String channelName, String createdBy, String channelDescription,
			Date channelCreatedDate, Community community, List<User> channelUsers) {
		super();
		this.channelId = channelId;
		this.channelName = channelName;
		this.createdBy = createdBy;
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
		return "Channel [channelId=" + channelId + ", channelName=" + channelName + ", createdBy=" + createdBy
				+ ", channelDescription=" + channelDescription + ", channelCreatedDate=" + channelCreatedDate
				+ ", channelUsers=" + channelUsers + "]";
	}

}