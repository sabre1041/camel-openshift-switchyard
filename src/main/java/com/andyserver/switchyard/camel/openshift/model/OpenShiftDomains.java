package com.andyserver.switchyard.camel.openshift.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="domains")
public class OpenShiftDomains {

	private List<OpenShiftDomain> domains = new ArrayList<OpenShiftDomain>();

	@XmlElement(name="domain")
	public List<OpenShiftDomain> getDomains() {
		return domains;
	}

	public void setDomains(List<OpenShiftDomain> domains) {
		this.domains = domains;
	}
	
}
