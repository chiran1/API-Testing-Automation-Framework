package com.employee.api.base;


import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static String empID="1";//Hard coded - Input for get details of single Employee & update employee
	public static Response response;
	
	
	
	@BeforeClass
	public void setup() {
		//logger = LogManager.getLogger(TestBase.class.getName());
		
		
		//logger= Logger.getLogger("EmployeesRestAPI");//added logger
		//PropertyConfigurator.configure("Log4j.properties");//added logger
		//logger.atLevel(Level.DEBUG);
	}
	
}
