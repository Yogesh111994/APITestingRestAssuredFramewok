package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	// Service URL
	public static String GOREST_ENDPOINT="/public/v2/users";
	public static String REQRES_ENDPOINT="/api/users";
	public static String CIRCIUT_ENDPOINT="/api/f1/2017/circuits.json";
	public static String AMADEUS_TOKEN_ENDPOINT="/v1/security/oauth2/token";
	public static String AMADEUS_FLIGHT_INFO_ENDPOINT="/v1/shopping/flight-destinations";
	
	
	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	protected String BaseURI;

	@Parameters({ "BaseURI" })
	@BeforeTest
	public void setUp(String BaseURI) {
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigurationManager();
		prop = config.initProp();
		//String BaseURI=prop.getProperty("BaseURI");
		this.BaseURI=BaseURI;
		// restClient= new RestClient(prop, BaseURI);
	}	
	
	// To run the test using properties file some changes you have make
	//  comment the @Parameters
	// public void setUp(String BaseURI) remove the parameter from method
	//
}
