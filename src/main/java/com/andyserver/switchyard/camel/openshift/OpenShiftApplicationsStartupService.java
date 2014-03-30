package com.andyserver.switchyard.camel.openshift;

import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplicationsStartupResponse;


/**
 * SwitchYard interface for the Camel XML Service
 * 
 * @author Andrew Block
 *
 */
public interface OpenShiftApplicationsStartupService {

	public OpenShiftApplicationsStartupResponse run();
	
}
