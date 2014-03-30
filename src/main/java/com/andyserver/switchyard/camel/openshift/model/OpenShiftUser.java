package com.andyserver.switchyard.camel.openshift.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class OpenShiftUser {

	private String userName;
	private String server;
	private Integer maxGears;
	private Integer consumedGears;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public Integer getMaxGears() {
		return maxGears;
	}
	public void setMaxGears(Integer maxGears) {
		this.maxGears = maxGears;
	}
	public Integer getConsumedGears() {
		return consumedGears;
	}
	public void setConsumedGears(Integer consumedGears) {
		this.consumedGears = consumedGears;
	}

	
}
