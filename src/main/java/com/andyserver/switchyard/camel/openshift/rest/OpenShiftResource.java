package com.andyserver.switchyard.camel.openshift.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplication;

/**
 * Rest interface to expose OpenShift operations invoking a Java based Camel route
 * 
 * @author Andrew Block
 *
 */
@Path("operation")
public interface OpenShiftResource {
	
	@GET
	@Path("user")
	@Produces(MediaType.APPLICATION_XML)
	public Response getUser();
	
	@GET
	@Path("domains")
	@Produces(MediaType.APPLICATION_XML)
	public Response getDomains();
	
	@GET
	@Path("domain/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getDomain(@PathParam("id") String id);
	
	@GET
	@Path("applications/{domainId}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getApplications(@PathParam("domainId") String domainId);
	
	@GET
	@Path("application/{uuid}")
	@Produces(MediaType.APPLICATION_XML)
	public OpenShiftApplication getApplication(@PathParam("uuid") String uuid);


}
