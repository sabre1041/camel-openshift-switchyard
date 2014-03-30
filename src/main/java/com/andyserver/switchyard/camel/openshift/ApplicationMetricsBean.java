package com.andyserver.switchyard.camel.openshift;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.camel.Message;

import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.client.IGear;
import com.openshift.client.IGearGroup;
import com.openshift.client.IUser;

/**
 * Bean to perform metrics on an Application 
 * 
 * @author Andrew Block
 *
 */
@Named("ApplicationMetrics")
public class ApplicationMetricsBean {
	
	/**
	 * Checks the current status of the gear running the Application and adds a header
	 * to the Message
	 * 
	 * @param application the Application
	 * @param message the Camel Message
	 */
	public void checkStatus(IApplication application, Message message) {

		for(IGearGroup gearGroup : application.getGearGroups()) {
			for(IGear gear : gearGroup.getGears()) {
				message.setHeader("gearState", gear.getState().name());
			}
		}
	}
	
	/**
	 * Collects all Applications related to a User
	 * 
	 * @param user the authenticated User
	 * @param message the Camel message
	 */
	public void collectApplications(IUser user, Message message) {
		final List<IApplication> applications = new ArrayList<IApplication>();
		
		for(IDomain domain : user.getDomains()) {
			for(IApplication application : domain.getApplications()) {
				applications.add(application);
			}
		}
		
		message.setBody(applications);
		
	}

	
}
