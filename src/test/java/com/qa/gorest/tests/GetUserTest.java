package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetUp() {
		restClient= new RestClient(prop, BaseURI);
	}
	
	
	@Test(priority=3)
	public void getAllUser() {
		restClient.get(GOREST_ENDPOINT,true, true)
		                      .then().log().all()
		                                .assertThat()
		                                        . statusCode(APIHttpStatus.Ok_200.getCode());	
	}
	@Test(priority=1, enabled=false, description="this test is in progress....")
	public void getspecificUser() {
		restClient.get(GOREST_ENDPOINT+"/"+"4885695",true, true)
		                      .then().log().all()
		                                .assertThat()
		                                        . statusCode(APIHttpStatus.Ok_200.getCode())
		                                                .and()
		                                                      .body("id", equalTo(4885695));
		
	}
	
	@Test(priority=1)
	public void getUserWithQueryParams() {
		Map<String,Object> queryParms= new HashMap<String,Object>();
		queryParms.put("name", "yogesh");
		queryParms.put("status", "active");
		System.out.println(queryParms.size());
		restClient.get(GOREST_ENDPOINT, null, queryParms,true, true)
		                      .then().log().all()
		                                .assertThat()
		                                        . statusCode(APIHttpStatus.Ok_200.getCode());
		System.out.println("Status Code is verified");
		                                        	
	}
	

}
