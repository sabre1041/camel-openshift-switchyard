package com.andyserver.switchyard.camel.openshift;

import com.andyserver.switchyard.camel.openshift.model.ScheduledApplicationsResponse;


/**
 * SwitchYard interface for the Camel XML Service
 * 
 * @author Andrew Block
 *
 */
public interface OpenShiftXMLRoutingService {

	public ScheduledApplicationsResponse run();
	
}
