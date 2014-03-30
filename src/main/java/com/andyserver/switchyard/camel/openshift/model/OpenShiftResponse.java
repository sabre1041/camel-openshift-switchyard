package com.andyserver.switchyard.camel.openshift.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OpenShiftResponse {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
