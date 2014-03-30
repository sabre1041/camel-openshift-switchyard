package com.andyserver.switchyard.camel.openshift.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpenShiftRequest {
	
	private String openShiftUserName;
	private String openShiftPassword;
	
	public String getOpenShiftUserName() {
		return openShiftUserName;
	}
	public void setOpenShiftUserName(String openShiftUserName) {
		this.openShiftUserName = openShiftUserName;
	}
	public String getOpenShiftPassword() {
		return openShiftPassword;
	}
	public void setOpenShiftPassword(String openShiftPassword) {
		this.openShiftPassword = openShiftPassword;
	}

}
