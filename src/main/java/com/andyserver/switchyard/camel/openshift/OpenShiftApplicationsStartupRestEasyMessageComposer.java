package com.andyserver.switchyard.camel.openshift;

import javax.ws.rs.WebApplicationException;

import org.apache.camel.component.openshift.OpenShiftConstants;
import org.switchyard.Exchange;
import org.switchyard.ExchangeState;
import org.switchyard.HandlerException;
import org.switchyard.Message;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;

import com.openshift.client.InvalidCredentialsOpenShiftException;


public class OpenShiftApplicationsStartupRestEasyMessageComposer extends RESTEasyMessageComposer {
	
    @Override
    public Message compose(RESTEasyBindingData source, Exchange exchange) throws Exception {

    	Message message = super.compose(source, exchange);

    	if(source.getHeaders().get(OpenShiftConstants.USERNAME) != null && source.getHeaders().get(OpenShiftConstants.PASSWORD) != null) {
    		
    		message.getContext().setProperty(OpenShiftConstants.USERNAME, source.getHeaders().getFirst(OpenShiftConstants.USERNAME));
    		message.getContext().setProperty(OpenShiftConstants.PASSWORD, source.getHeaders().getFirst(OpenShiftConstants.PASSWORD));    		
    		
    	}
    	
    	return message;
    	
    }
    
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
        
        return super.decompose(exchange, target);
    }

}
