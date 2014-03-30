package com.andyserver.switchyard.camel.openshift;

import java.util.List;

import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.client.IUser;

/**
 * Interface for the Java based Camel route
 * 
 * @author Andrew Block
 *
 */
public interface OpenShiftOperationsService {
	
	public IUser getUser();
	
	public List<IDomain> getDomains();
	
	public IDomain getDomain(String id);
	
	public List<IApplication> getApplications();
	
	public IApplication getApplication(String uuid);

}
