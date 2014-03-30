package com.andyserver.switchyard.camel.openshift.integration;

import static org.junit.Assert.assertNotNull;

import org.apache.camel.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.PropertyMixIn;

import com.andyserver.switchyard.camel.openshift.model.OpenShiftApplicationsStartupResponse;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class, PropertyMixIn.class })
public class OpenShiftApplicationsStartupServiceTest {

	@ServiceOperation("OpenShiftApplicationsStartupService.run")
	private Invoker service;
	
	private PropertyMixIn propMixIn;
	
    //TODO: Configure your OpenShift username and password
    private static final String OPENSHIFT_USER = "username";
    private static final String OPENSHIFT_PASSWORD = "password";

    
    @BeforeDeploy
    public void setProperties() {        
		propMixIn.set("openshift.user", OPENSHIFT_USER);
		propMixIn.set("openshift.password", OPENSHIFT_PASSWORD);

    }
	
	@Test
	public void testServiceOneWay() throws Exception {
	
		service.sendInOnly("Test");
		Thread.sleep(5000);

	}
	
	
	@Test
	public void testServiceResponse() throws Exception {
	
		Message response = service.sendInOut("Test").getContent(Message.class);
		
		OpenShiftApplicationsStartupResponse rsp = response.getBody(OpenShiftApplicationsStartupResponse.class);
		assertNotNull(rsp);
		System.out.println(rsp);
	}


}
