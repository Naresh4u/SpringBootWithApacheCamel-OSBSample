package com.apache.camel.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author nareshd
 *
 */
@RestController
@RequestMapping("/rootCamel/")
@ActiveProfiles(value = "development")
public class OSBProxyController {
	

	 @Autowired
	 ProducerTemplate producerTemplate;

	@RequestMapping(value = "/helloCamel", method = RequestMethod.GET)
	@ApiOperation(value = "Gretings from Apache Camel", notes = "Returns a Hello Camel Message details. SLA:500", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Call is done Successfully", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Does Not Exist") })
	public String gretingsMethod() {
		return "Hello Boss !!! I'm APACHE CAMEL";
	}
	
	@RequestMapping(value = "/firstRoute", method = RequestMethod.GET)
	@ApiOperation(value = "Call REST Service By Apache Camel", notes = "Returns a Hello Camel Message details. SLA:500", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Call is done Successfully", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Does Not Exist") })
	public String callRESTServiceByCamel() {
		producerTemplate.sendBody("direct:firstRoute", "Calling via Spring Boot Rest Controller");
		return "Camel body: Calling via Spring Boot Rest Controller";
	}
	
	@RequestMapping(value = "/secondRoute", method = RequestMethod.GET)
	@ApiOperation(value = "Call REST Service By Apache Camel", notes = "Returns a Hello Camel Message details. SLA:500", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Call is done Successfully", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Input Provided"),
			@ApiResponse(code = 404, message = "Does Not Exist") })
	public String callRESTServiceByCamelTwo() {
		producerTemplate.sendBody("direct:secondRoute", "Calling via Spring Boot Rest Controller");
		return "Camel body: Calling via Spring Boot Rest Controller";
	}

}