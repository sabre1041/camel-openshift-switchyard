package com.andyserver.switchyard.camel.openshift.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="applications")
public class OpenShiftApplications {
	
	private List<OpenShiftApplication> applications = new ArrayList<OpenShiftApplication>();

	@XmlElement(name="application")
	public List<OpenShiftApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<OpenShiftApplication> applications) {
		this.applications = applications;
	}

}
