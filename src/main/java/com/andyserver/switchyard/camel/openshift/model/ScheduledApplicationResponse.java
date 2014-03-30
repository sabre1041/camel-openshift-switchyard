package com.andyserver.switchyard.camel.openshift.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="appstatus")
public class ScheduledApplicationResponse {
	
	private String name;
	private String initialState;
	private String currentState;

	public String getInitialState() {
		return initialState;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
