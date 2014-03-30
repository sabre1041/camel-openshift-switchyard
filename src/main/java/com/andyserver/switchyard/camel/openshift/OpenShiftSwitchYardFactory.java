package com.andyserver.switchyard.camel.openshift;

import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplication;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftDomain;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftUser;
import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.client.IUser;

/**
 * Utility class to convert OpenShift domain model to a application specific model
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftSwitchYardFactory {
	
	/**
	 * Converts a OpenShift user object to a application specific user object
	 *  
	 * @param user the OpenShift user object
	 * @return the application user object
	 */
	public static OpenShiftUser mapUser(IUser user) {
		OpenShiftUser openShiftUser = new OpenShiftUser();
		openShiftUser.setConsumedGears(user.getConsumedGears());
		openShiftUser.setMaxGears(user.getMaxGears());
		openShiftUser.setServer(user.getServer());
		openShiftUser.setUserName(user.getRhlogin());
		
		return openShiftUser;
	}
	
	/**
	 * @param domain
	 * @return
	 */
	public static OpenShiftDomain mapDomain(IDomain domain) {
		OpenShiftDomain openShiftDomain = new OpenShiftDomain();
		openShiftDomain.setId(domain.getId());
		openShiftDomain.setSuffix(domain.getSuffix());
		
		return openShiftDomain;
	}
	
	/**
	 * @param application
	 * @return
	 */
	public static OpenShiftApplication mapApplication(IApplication application) {
		OpenShiftApplication openShiftApplication = new OpenShiftApplication();
		openShiftApplication.setName(application.getName());
		openShiftApplication.setUuid(application.getUUID());
		openShiftApplication.setApplicationUrl(application.getApplicationUrl());
		openShiftApplication.setSshUrl(application.getSshUrl());
		openShiftApplication.setGitUrl(application.getGitUrl());
		openShiftApplication.setCreationTime(application.getCreationTime());
		
		return openShiftApplication;
	}

}
