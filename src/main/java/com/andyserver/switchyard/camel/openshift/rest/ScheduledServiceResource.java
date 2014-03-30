package com.andyserver.switchyard.camel.openshift.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.andyserver.switchyard.camel.openshift.model.ScheduledApplicationsResponse;


/**
 * Rest interface for the SwitchYard Camel XML scheduled service route
 * 
 * @author Andrew Block
 *
 */
@Path("scheduled")
public interface ScheduledServiceResource {

	@GET
	@Path("appstatus")
	@Produces(MediaType.APPLICATION_XML)
	public ScheduledApplicationsResponse run();
	
}
