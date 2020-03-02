package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Record extends TestBase{

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("*****Started TC003_POST_EMPLOYEE_RECORD*****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);

		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(3000);

	}

	@Test
	void checkResponseBody() {
		logger.info("*****Check Response Body*****");

		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
	}

	@Test
	void checkStatusCode() {
		logger.info("****** Checking Status Code ******");

		int statusCode = response.getStatusCode();
		logger.info("Status Code is => " + statusCode);

		Assert.assertEquals(200, statusCode);
	}

	@Test
	void checkResponseTime() {
		logger.info("******Check Response Time*****");
		long responseTime = response.getTime();
		logger.info("response time is => " + responseTime);

		if (responseTime > 2000)
			logger.warn("Response Time is greater than 2 second");
		Assert.assertTrue(responseTime < 10000);
	}

	@Test
	void checkStatusLine() {
		logger.info("****Check Status Line****");

		String statusLine = response.getStatusLine();
		logger.info("Status Line ==> " + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
	}

	@Test
	void checkContentType() {
		logger.info("*******Checking Content Type*******");
		String contentType1 = response.getContentType();
		String contentType = response.getHeader("Content-Type");
		System.out.println("ContentType1 is => "+contentType1);
		logger.info("Content Type is => " + contentType);
		Assert.assertEquals(contentType1, "application/json;charset=utf-8");
	}

	@Test
	void checkServerType() {
		logger.info("*****Check Server Type*****");
		String serverType = response.header("Server");
		logger.info("Server Type is ==> " + serverType);
		Assert.assertEquals("nginx/1.16.0", serverType);
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("*****Check Server Type*****");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Server Type is ==> " + contentEncoding);
		Assert.assertEquals(contentEncoding,null);
	}
	
	@Test
	void checkContentLength() {
		logger.info("*****Check Server Type*****");
		String contentLength = response.header("Content-Length");
		logger.info("Server Type is ==> " + contentLength);
		
		if(Integer.parseInt(contentLength)<100)
			logger.info("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)<100);
	}
	
	@Test
	void checkCookies() {
		logger.info("*******Checking Cookies*******");
		String cookie = response.getCookie("PHPSESSID");
	}
	
	@AfterClass
	void tearDown() {
		logger.info("**********Finished TC003_PUT_EMPLOYEE_RECORD********");
	}

}
