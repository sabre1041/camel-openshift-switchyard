package com.andyserver.switchyard.camel.openshift;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplicationStartupResponse;
import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplicationsStartupResponse;
import com.openshift.client.IApplication;

/**
 * Aggregates messages representing an OpenShift application after they have been split to attempt to restart the application
 * Creates a {@link OpenShiftApplicationsStartupResponse} object as a response
 * 
 * @author Andrew Block
 *
 */
@Named("applicationsStartupAggregator")
public class OpenShiftApplicationsStartupServiceAggerator implements AggregationStrategy {

	@Inject
	ApplicationMetricsBean appStatusBean;
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if(oldExchange == null) {
			
			
			OpenShiftApplicationsStartupResponse response = new OpenShiftApplicationsStartupResponse();
			
			OpenShiftApplicationStartupResponse appResponse = process(newExchange);

			response.getApplications().add(appResponse);
			
			newExchange.getIn().setBody(response);			
			
			return newExchange;
		}
		
		OpenShiftApplicationsStartupResponse response = (OpenShiftApplicationsStartupResponse) oldExchange.getIn().getBody();
		
		OpenShiftApplicationStartupResponse appResponse = process(newExchange);

		response.getApplications().add(appResponse);
		
		oldExchange.getIn().setBody(response);
		
		return oldExchange;
		
	}
	
	/**
	 * Creates a {@link OpenShiftApplicationsStartupResponse} object based on the state of an OpenShift application
	 * 
	 * @param exchange the Camel exchange
	 * @return the {@link OpenShiftApplicationsStartupResponse} object
	 */
	private OpenShiftApplicationStartupResponse process(Exchange exchange) {
		IApplication app = exchange.getIn().getBody(IApplication.class);
		OpenShiftApplicationStartupResponse appResponse = new OpenShiftApplicationStartupResponse();
		appResponse.setName(app.getName());
		appResponse.setInitialState((String) exchange.getIn().getHeader("gearState"));

		appStatusBean.checkStatus(app, exchange.getIn());
		
		appResponse.setCurrentState((String) exchange.getIn().getHeader("gearState"));
		
		return appResponse;
	}

}
