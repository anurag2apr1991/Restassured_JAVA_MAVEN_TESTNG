package com.employeeapi.base;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

//import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "51838";

	public Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("EmployeesRestAPI");
		PropertyConfigurator.configure("Log4j.Properties");
		logger.setLevel(Level.DEBUG);;
	}
}
