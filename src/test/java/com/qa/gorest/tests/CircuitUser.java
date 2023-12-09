package com.qa.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JaywayPathValidator;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.List;
public class CircuitUser extends BaseTest {

	@BeforeMethod
	public void getUserSetUp() {
		restClient= new RestClient(prop, BaseURI);
	}
	
	@Test
	public void circuitUser() {
		Response circuitResponce=restClient.get(CIRCIUT_ENDPOINT, false,true);
		circuitResponce.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode());
//		int statusCode=circuitResponce.statusCode();
//		System.out.println(statusCode);
	//	Assert.assertEquals(circuitResponce.statusCode(), equalTo(APIHttpStatus.Ok_200.getCode()));
		
		JaywayPathValidator jsonPath= new JaywayPathValidator();
		List<String> countryList=jsonPath.readList(circuitResponce, "$..CircuitTable.Circuits[*].Location.country");
		System.out.println(countryList);
		Assert.assertTrue(countryList.contains("China"));
		
	}
}
