package com.andyserver.switchyard.camel.openshift.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="openshiftstatus")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduledApplicationsResponse {
	
	@XmlElementWrapper(name="applications")
	@XmlElement(name="application")
	private List<ScheduledApplicationResponse> applications = new ArrayList<ScheduledApplicationResponse>();;

	public List<ScheduledApplicationResponse> getApplications() {
		return applications;
	}

	public void setApplications(List<ScheduledApplicationResponse> applications) {
		this.applications = applications;
	}

}
