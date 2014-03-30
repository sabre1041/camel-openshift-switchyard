package com.andyserver.switchyard.camel.openshift.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpenShiftDomain {
	
	private String id;
	private String suffix;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	

}
