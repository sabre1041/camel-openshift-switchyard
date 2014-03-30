package com.andyserver.switchyard.camel.openshift;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.andyserver.switchyard.camel.openshift.model.ScheduledApplicationResponse;
import com.andyserver.switchyard.camel.openshift.model.ScheduledApplicationsResponse;
import com.openshift.client.IApplication;

/**
 * Aggregates messages representing an OpenShift application after they have been split to attempt to restart the application
 * Creates a {@link ScheduledApplicationsResponse} object as a response
 * 
 * @author Andrew Block
 *
 */
@Named("scheduledAggregator")
public class ScheduledApplicationServiceAggerator implements AggregationStrategy {

	@Inject
	ApplicationMetricsBean appStatusBean;
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if(oldExchange == null) {
			
			
			ScheduledApplicationsResponse response = new ScheduledApplicationsResponse();
			
			ScheduledApplicationResponse appResponse = process(newExchange);

			response.getApplications().add(appResponse);
			
			newExchange.getIn().setBody(response);			
			
			return newExchange;
		}
		
		ScheduledApplicationsResponse response = (ScheduledApplicationsResponse) oldExchange.getIn().getBody();
		
		ScheduledApplicationResponse appResponse = process(newExchange);

		response.getApplications().add(appResponse);
		
		oldExchange.getIn().setBody(response);
		
		return oldExchange;
		
	}
	
	/**
	 * Creates a {@link ScheduledApplicationsResponse} object based on the state of an OpenShift application
	 * 
	 * @param exchange the Camel exchange
	 * @return the {@link ScheduledApplicationsResponse} object
	 */
	private ScheduledApplicationResponse process(Exchange exchange) {
		IApplication app = exchange.getIn().getBody(IApplication.class);
		ScheduledApplicationResponse appResponse = new ScheduledApplicationResponse();
		appResponse.setName(app.getName());
		appResponse.setInitialState((String) exchange.getIn().getHeader("gearState"));

		appStatusBean.checkStatus(app, exchange.getIn());
		
		appResponse.setCurrentState((String) exchange.getIn().getHeader("gearState"));
		
		return appResponse;
	}

}
