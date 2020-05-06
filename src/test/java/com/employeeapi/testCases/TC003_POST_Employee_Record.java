package com.employeeapi.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;


import com.employee.api.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_POST_Employee_Record extends TestBase {

	private static Logger logger = LogManager.getLogger(TC003_POST_Employee_Record.class);

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		
		logger.info("*************************started TC003_POST_Employee_Record **********************************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON, we can add key-value
		// pairs using the put method {"name":"sdfsdf", "salary", "123"}

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName );// cast
		requestParams.put("salary", empSalary );
		requestParams.put("age", empAge );

		// Add a header stating the request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody() {
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
		
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	 @Test
		void checkStatusLine() {
			String statusLine = response.getStatusLine();
			Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		}
	
	 
	 @Test
		void checkContentType() {
			String contentType = response.getContentType();
			Assert.assertEquals(contentType, "application/json;charset=utf-8");
		}
	 
	 @Test
		void checkServerType() {
			String serverType = response.header("Server");
			Assert.assertEquals(serverType, "nginx/1.16.0");

		}
		@Test
		void checkContentEncoding() {
			String contentEncoding = response.header("Content-Encoding");
			//Assert.assertEquals(contentEncoding, "gzip");
			Assert.assertNull(contentEncoding);
		}
	 

	 @AfterClass
		void tearDown() {
			logger.info("********************* Finish TC003_POST_Employee_Record  ********************");
		}
	

}
