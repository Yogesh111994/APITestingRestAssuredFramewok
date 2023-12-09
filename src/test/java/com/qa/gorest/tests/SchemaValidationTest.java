package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidationTest  extends BaseTest {
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient= new RestClient(prop, BaseURI);
	}
	
	@Test
	public void gorestAPISchemaValidation() {
		restClient.get(GOREST_ENDPOINT,true, true)
		                      .then().log().all()
		                                .assertThat()
		                                      .and()
		                                        . statusCode(APIHttpStatus.Ok_200.getCode())
		                                              . and()
		                                              .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getuserschema.json"));
		                                             
		                                              
		                                              
		                                          
	
	}

}
