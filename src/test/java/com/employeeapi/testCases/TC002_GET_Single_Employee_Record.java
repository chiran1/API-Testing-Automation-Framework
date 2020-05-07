package com.employeeapi.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.api.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GET_Single_Employee_Record extends TestBase{
	private static Logger logger = LogManager.getLogger(TC001_Get_All_Employees.class);
	
	 @BeforeClass
	 void getEmployeeData() throws InterruptedException {
		 logger.info("*************************started TC002_GET_Single_Employee_Record **********************************");
		 
		 RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
			httpRequest = RestAssured.given();
			response = httpRequest.request(Method.GET, "/employees/"+empID);
			Thread.sleep(3);
	 }
	 
	 @Test
	 void checkResponseBody() {
		 String responseBody = response.getBody().asString();
		 Assert.assertEquals(responseBody.contains(empID), true);
	 }
	 
	 @Test
	 void checkStatusCode() {
		 int statusCode = response.getStatusCode();
		 Assert.assertEquals(statusCode, 404);
	 }
	 
	 @Test
	 void checkResponseTime() {
		 long responseTime = response.getTime();
		 Assert.assertTrue(responseTime>491);
	 }
	 
	 @Test
		void checkContentType() {
			String contentType = response.getContentType();
			Assert.assertEquals(contentType, "text/html;charset=UTF-8");
		}
	 
	 @Test
		void checkServerType() {
			String serverType = response.header("Server");
			Assert.assertEquals(serverType, "nginx/1.16.0");

		}
	 
		/*
		 * @Test void checkContentLength() { String contentLength =
		 * response.header("Content-Length");
		 * Assert.assertTrue(Integer.parseInt(contentLength) > 1500);
		 * 
		 * }
		 */
	 
	 @Test
		void checkStatusLine() {

			String statusLine = response.getStatusLine();
			Assert.assertEquals(statusLine, "HTTP/1.1 404 Not Found");
		}

	 @AfterClass
		void tearDown() {
			logger.info("********************* Finish TC002_GET_Single_Employee_Record  ********************");
		}

}