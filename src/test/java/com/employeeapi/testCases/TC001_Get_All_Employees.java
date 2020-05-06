package com.employeeapi.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import com.employee.api.base.TestBase;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	private static Logger logger = LogManager.getLogger(TC001_Get_All_Employees.class);

	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("*************************started TC001_Get_All_Employees **********************************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		// Thread.sleep(3);

	}

	@Test
	void checkResponseBody() {
		logger.info("********************* checking response body ********************");

		String responseBody = response.getBody().asString();
		logger.info("Response Body ==" + responseBody);// getting responseBody
		Assert.assertTrue(response != null);

	}

	@Test
	void checkStatusCode() {
		logger.info("********************* checking status code ********************");
		int statusCode = response.getStatusCode();// getting status code
		logger.info("statusCode == " + statusCode);// 200
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	void checkResponseTime() {
		logger.info("********************* checking Response Time ********************");

		long responseTime = response.getTime();
		logger.info("responseTime == " + responseTime);// response time

		if (responseTime > 2000) {
			logger.warn("response time is greater than 2000");

			Assert.assertTrue(responseTime < 2000);
		}

	}

	@Test
	void checkStatusLine() {

		logger.info("********************* checking Response Time ********************");

		String statusLine = response.getStatusLine();
		logger.info("statusLine == " + statusLine);// status line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("********************* checking Content Type ********************");

		String contentType = response.getContentType();
		logger.info("contentType == " + contentType);// content Type
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test
	void checkServerType() {
		logger.info("********************* checking server Type ********************");

		String serverType = response.header("Server");
		logger.info("serverType == " + serverType);// content Type
		Assert.assertEquals(serverType, "nginx/1.16.0");

	}

	@Test
	void checkContentEncoding() {
		logger.info("********************* checking contentEncoding  ********************");

		String contentEncoding = response.header("Content-Encoding");
		logger.info("contentEncoding == " + contentEncoding);// content Encoding
		Assert.assertEquals(contentEncoding, "gzip");

	}

	@Test
	void checkContentLength() {
		logger.info("********************* checking content Length  ********************");

		String contentLength = response.header("Content-Length");
		logger.info("contentLength is == " + contentLength);// content Encoding
		if (Integer.parseInt(contentLength) < 100) {
			logger.warn("content length is less than 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength) > 100);

	}

	@Test
	void checkCookies() {
		logger.info("********************* checking cookies  ********************");

		String cookie = response.getCookie("PHPSESSID");
		/*
		 * Assert.assertEquals(cookie, "5da106ccc1a3cd08b3cd9f2f5d611843\r\n" + "");
		 */

	}

	@AfterClass
	void tearDown() {
		logger.info("********************* Finish TC001_Get_All_Employees  ********************");
	}

}
