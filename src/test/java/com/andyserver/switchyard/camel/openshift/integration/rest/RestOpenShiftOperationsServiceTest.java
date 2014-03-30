package com.andyserver.switchyard.camel.openshift.integration.rest;

import static org.junit.Assert.assertEquals;

import org.apache.camel.component.openshift.OpenShiftConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.PropertyMixIn;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
		CDIMixIn.class, HTTPMixIn.class, PropertyMixIn.class }, scanners = { BeanSwitchYardScanner.class, TransformSwitchYardScanner.class })
public class RestOpenShiftOperationsServiceTest {

	private HTTPMixIn httpMixIn;
	@ServiceOperation("OpenShiftJavaRoutingService")
	private Invoker service;
	
    private static String BASE_URL = "http://localhost:8081/";
    
    //TODO: Configure your OpenShift username and password
    private static final String OPENSHIFT_USERNAME = "username";
    private static final String OPENSHIFT_PASSWORD = "password";
    
    //TODO: Configure your OpenShift domain and an application uuid
    private static final String DOMAIN_NAME = "";
    private static final String APPLICATION_UUID = "";

	private PropertyMixIn propMixIn;


    @BeforeDeploy
    public void setProperties() {
        System.setProperty("org.switchyard.component.resteasy.standalone.port", "8081");
        System.setProperty("org.switchyard.component.resteasy.standalone.path", "");
		propMixIn.set("openshift.user", OPENSHIFT_USERNAME);
		propMixIn.set("openshift.password", OPENSHIFT_PASSWORD);
    }
    
    @Before
    public void setup() {
		httpMixIn.setRequestHeader(OpenShiftConstants.USERNAME, OPENSHIFT_USERNAME);
		httpMixIn.setRequestHeader(OpenShiftConstants.PASSWORD, OPENSHIFT_PASSWORD);

    }
    
	@Test
	public void testGetUser() throws Exception {
		assertEquals(200,httpMixIn.sendStringAndGetStatus(BASE_URL +"/operation/user", "", HTTPMixIn.HTTP_GET));
		
	}
	
	@Test
	public void testGetDomains() throws Exception {
		assertEquals(200,httpMixIn.sendString(BASE_URL +"/operation/domains", "", HTTPMixIn.HTTP_GET));
		
	}
	
	@Test
	public void testGetDomain() throws Exception {
		assertEquals(200,httpMixIn.sendString(BASE_URL +"/operation/domain/"+DOMAIN_NAME, "", HTTPMixIn.HTTP_GET));
		
	}
	
	@Test
	public void testGetInvalidDomain() throws Exception {
		assertEquals(404,httpMixIn.sendStringAndGetStatus(BASE_URL +"/operation/domain/"+DOMAIN_NAME, "", HTTPMixIn.HTTP_GET));
		
	}
	
	@Test
	public void testGetApplications() throws Exception {
		assertEquals(200,httpMixIn.sendString(BASE_URL +"/operation/applications/"+DOMAIN_NAME, "", HTTPMixIn.HTTP_GET));
		
	}
	
	@Test
	public void testGetApplication() throws Exception {
		assertEquals(200,httpMixIn.sendString(BASE_URL +"/rest/application/"+APPLICATION_UUID, "", HTTPMixIn.HTTP_GET));
		
	}

}
