package com.andyserver.switchyard.camel.openshift.integration.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.PropertyMixIn;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
		CDIMixIn.class, HTTPMixIn.class, PropertyMixIn.class })
public class OpenShiftRestRoutingServiceTest {

	private HTTPMixIn httpMixIn;
	@ServiceOperation("OpenShiftRoutingService")
	private Invoker service;
	
	private PropertyMixIn propMixIn;

	
    private static String BASE_URL = "http://localhost:8081/";
    
    //TODO: Configure your OpenShift username and password
    private static final String OPENSHIFT_USER = "";
    private static final String OPENSHIFT_PASSWORD = "";
    
    
    @BeforeDeploy
    public void setProperties() {
        System.setProperty("org.switchyard.component.resteasy.standalone.port", "8081");
        System.setProperty("org.switchyard.component.resteasy.standalone.path", "");
        
		propMixIn.set("openshift.user", OPENSHIFT_USER);
		propMixIn.set("openshift.password", OPENSHIFT_PASSWORD);

    }

	@Test
	public void testStartApplications() throws Exception {
		httpMixIn.setRequestHeader("OpenShiftUserName", OPENSHIFT_USER);
		httpMixIn.setRequestHeader("OpenShiftPassword", OPENSHIFT_PASSWORD);

		assertEquals(200,httpMixIn.sendString(BASE_URL +"/scheduled/appstatus", "", HTTPMixIn.HTTP_GET));
	}

}
