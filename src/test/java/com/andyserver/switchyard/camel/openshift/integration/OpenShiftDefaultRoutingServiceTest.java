package com.andyserver.switchyard.camel.openshift.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class })
public class OpenShiftDefaultRoutingServiceTest {

	@ServiceOperation("OpenShiftRoutingService.run")
	private Invoker service;
	
	@Test
	public void testService() throws Exception {
	
		service.sendInOnly("Test");
		Thread.sleep(5000);

	}

}
