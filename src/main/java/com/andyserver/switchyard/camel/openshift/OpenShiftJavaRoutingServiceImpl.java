package com.andyserver.switchyard.camel.openshift;

import javax.ws.rs.WebApplicationException;

import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.openshift.OpenShiftApplicationNotFoundException;
import org.apache.camel.component.openshift.OpenShiftConstants;
import org.apache.camel.component.openshift.OpenShiftDomainNotFoundException;

/**
 * 
 * Java Camel Route
 * @author Andrew Block
 *
 */
public class OpenShiftJavaRoutingServiceImpl extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	@SuppressWarnings("unchecked")
	public void configure() {
		Predicate operationNotNull = header("operation").isNotNull();
		Predicate user = PredicateBuilder.and(operationNotNull,header("operation").isEqualTo("user"));
		Predicate domains = PredicateBuilder.and(operationNotNull,header("operation").isEqualTo("domains"));
		Predicate domain = PredicateBuilder.and(operationNotNull,header("operation").isEqualTo("domain"));
		Predicate applications = PredicateBuilder.and(operationNotNull,header("operation").isEqualTo("applications"));
		Predicate application = PredicateBuilder.and(operationNotNull,header("operation").isEqualTo("application"));

		onException(OpenShiftDomainNotFoundException.class, OpenShiftApplicationNotFoundException.class)
			.throwException(new WebApplicationException(404));
		
		
		from("switchyard://OpenShiftJavaRoutingService")
		
		// Check to see if required parameters are set
		.choice()
			.when(PredicateBuilder.and(header(OpenShiftConstants.USERNAME).isNull(), header(OpenShiftConstants.PASSWORD).isNull()))
				.throwException(new WebApplicationException(401))
		.end()
		.choice()
			.when(user)
			.to("openshift://user")
			.when(domains)
			.to("openshift://domains")
			.when(domain)
			.recipientList().simple("openshift://domain/${body}").end()
			.when(applications)
			.recipientList().simple("openshift://applications/${body}").end()
			.when(application)
			.recipientList().simple("openshift://application/${body}").end()
			.otherwise()
			.throwException(new WebApplicationException(500))
		.end();
		
		}

}
