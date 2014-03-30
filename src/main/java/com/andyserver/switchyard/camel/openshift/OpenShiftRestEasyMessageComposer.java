package com.andyserver.switchyard.camel.openshift;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.apache.camel.component.openshift.OpenShiftConstants;
import org.switchyard.Exchange;
import org.switchyard.ExchangeState;
import org.switchyard.HandlerException;
import org.switchyard.Message;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;

import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplication;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplications;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftDomain;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftDomains;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftUser;
import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.client.IUser;
import com.openshift.client.InvalidCredentialsOpenShiftException;


/**
 * Custom implementation for composing and decomposing RestEasy messages
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftRestEasyMessageComposer extends RESTEasyMessageComposer {
	
    @Override
    public Message compose(RESTEasyBindingData source, Exchange exchange) throws Exception {

    	Message message = super.compose(source, exchange);

    	if(source.getHeaders().get(OpenShiftConstants.USERNAME) != null && source.getHeaders().get(OpenShiftConstants.PASSWORD) != null) {
    		message.getContext().setProperty(OpenShiftConstants.USERNAME, source.getHeaders().getFirst(OpenShiftConstants.USERNAME));
    		message.getContext().setProperty(OpenShiftConstants.PASSWORD, source.getHeaders().getFirst(OpenShiftConstants.PASSWORD));    		
    		
    	}
    	
    	if(source.getOperationName().equals("getUser")) {
    		message.getContext().setProperty("operation", "user");
    	}
    	else if(source.getOperationName().equals("getDomains")) {
    		message.getContext().setProperty("operation", "domains");
    	}
    	else if(source.getOperationName().equals("getDomain")) {
    		message.getContext().setProperty("operation", "domain");
    	} 
    	else if(source.getOperationName().equals("getApplications")) {
    		message.getContext().setProperty("operation", "applications");
    	} 
    	else if(source.getOperationName().equals("getApplication")) {
    		message.getContext().setProperty("operation", "application");
    	}
    	    	    	
    	return message;
    	
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public RESTEasyBindingData decompose(Exchange exchange, RESTEasyBindingData target) throws Exception {

    	Message sourceMessage = exchange.getMessage();
        Object content = sourceMessage.getContent();
        target.setOperationName(exchange.getContract().getProviderOperation().getName());
        
        if (exchange.getState().equals(ExchangeState.FAULT)) {
        	if(content instanceof HandlerException) {        		
        		HandlerException e = (HandlerException) content;
        		
        		if(e.getCause() != null) {
        			if(e.getCause() instanceof InvalidCredentialsOpenShiftException) {
                		throw new WebApplicationException(401);
        			}
        		}
        	}
        }
    		
           target = super.decompose(exchange, target);
           
           if(target.getOperationName().equals("getUser") && (content != null) && (content instanceof IUser)) {
        	   IUser iUser = (IUser) content;
        	   
        	   OpenShiftUser user = OpenShiftSwitchYardFactory.mapUser(iUser);
        	   
        	   target.setParameters(new Object[]{user});
           }
           
           else if(target.getOperationName().equals("getDomains") && (content != null)) {
        	   
        	   OpenShiftDomains domains = new OpenShiftDomains();
        	   
        	   if(content instanceof List) {
        		   List<Object> list = (List<Object>) content;
        		   
        		   for(Object o : list) {
        			   if(o instanceof IDomain) {
        				   IDomain domain = (IDomain) o;
        				   
        				   OpenShiftDomain openShiftDomain = OpenShiftSwitchYardFactory.mapDomain(domain);
        				   domains.getDomains().add(openShiftDomain);
        			   }
        		   }
        	   }
        	   target.setParameters(new Object[]{domains});

        	   
           }
           else if(target.getOperationName().equals("getApplications") && (content != null)) {

        	   OpenShiftApplications applications = new OpenShiftApplications();
        	   
        	   if(content instanceof List) {
        		   List<Object> list = (List<Object>) content;
        		   
        		   for(Object o : list) {
        			   if(o instanceof IApplication) {
        				   IApplication application = (IApplication) o;
        				   
        				   OpenShiftApplication openshiftApplication = OpenShiftSwitchYardFactory.mapApplication(application);
        				   applications.getApplications().add(openshiftApplication);
        			   }
        		   }
        	   }
        	   target.setParameters(new Object[]{applications});

        	   
           }
           else if(target.getOperationName().equals("getDomain") && (content != null) && (content instanceof IDomain)) {
        	   IDomain iDomain = (IDomain) content;
        	   
        	   OpenShiftDomain domain = OpenShiftSwitchYardFactory.mapDomain(iDomain);
        	   
        	   target.setParameters(new Object[]{domain});
           }
           else if(target.getOperationName().equals("getApplication") && (content != null) && (content instanceof IApplication)) {
        	   IApplication iApplication = (IApplication) content;
        	   
        	   OpenShiftApplication application = OpenShiftSwitchYardFactory.mapApplication(iApplication);
        	   
        	   target.setParameters(new Object[]{application});
           }
           else {
        	   throw new WebApplicationException();
           }
           
           
      
           return target;
    }

}
