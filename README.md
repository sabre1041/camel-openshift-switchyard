camel-openshift-switchyard
======================

Demonstration of various use cases of the [OpenShift Camel Component](https://github.com/sabre1041/camel-openshift)  within a SwitchYard application. 

# Features

* OpenShift Camel component
* SwitchYard
	* Bindings
		* Scheduling
		* REST
	* Camel 
		* Routes
			* XML
			* Java
		* Various Enterprise Integration Patterns (EIP)
	* Custom Message Composers

# Project Overview

## SwitchYard Services

The following SwitchYard services are contained within this application

### Start Applications (Camel XML)

Attempts to start all applications within a users' account which are not currently started

### User Operations (Camel Java)

Performs various operations against a users' account

* Display User 
* Display Domains
* Display Applications within a given Domain

## SwitchYard Bindings

Services can be accessed via the following methods:

### REST

Each service is exposed via REST services. All services are available under the */rest* context.  The following table describes the method invocations available. 

| Context | Description |
| ---------- | --------------- |
| /scheduled/appstatus | Attempt to start stopped Applications returning statistics of the invocation |
| /operation/user | Retrieve details of the user |
| /operation/domains | Retrieve all domains for a user |
| /operation/domain/{id} | Retrieve the details for an individual domain |
| /operation/applications/{domainId} | Retrieve details for all applications in a given doman |
| /operation/application/{uuid} | Retrieve information for a given application |
 


### Timer

Invokes the service to start all applications within a given users' account. By default, the timer will fire hourly at the top of each hour. The schedule can be configured within the SwitchYard binding. The following properties must be configured within the SwitchYard context or via a Java property within the application container:

* *openshift.user* - The OpenShift login for a user account
* *openshift.password *- The OpenShift password for a user account


## Web Application

A web application is available to invoke the operations exposed by SwitchYard services. It can be accessed at the following location after being deployed to Fuse Service Works

[http://localhost:8080/camel-openshift-switchyard](http://localhost:8080/camel-openshift-switchyard/)

## Tests

Integration tests are available to demonstrate how to test SwitchYard components and the services within this project

# Requirements

1. Maven
2. Fuse Service Works/SwitchYard
3. Camel OpenShift Component

# Building and Deploying

1. Clone the repository
2. Configure project dependencies
	3. Run either the init.sh or init.bat within the support folder to retrieve and build the camel-openshift component and install in the local Maven repository
2. Configure required properties for timer service (optional)
2. Build the project using Maven `mvn clean install`
3. Deploy project to Fuse Service Works

# OpenShift

The project can be seemlessly deployed to the OpenShift platform

1. Create a new application
	2. Add a custom cartridge: [http://cartridge-switchyard.rhcloud.com/manifest/466c7020661420c4604e870802fe673244861a5a](http://cartridge-switchyard.rhcloud.com/manifest/466c7020661420c4604e870802fe673244861a5a)
	3. Select an application name
	4. Source code: [https://github.com/sabre1041/camel-openshift-switchyard.git](https://github.com/sabre1041/camel-openshift-switchyard.git)

