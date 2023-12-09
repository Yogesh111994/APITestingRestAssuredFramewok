package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;

public class ReqResTest extends BaseTest{

	
	@BeforeMethod
	public void getUserSetUp() {
		restClient= new RestClient(prop, BaseURI);
	}
	
	@Test
	public void GetReqResTest() {
		restClient.get(REQRES_ENDPOINT+"?page=2",false, true)
        .then().log().all()
                  .assertThat()
                          . statusCode(APIHttpStatus.Ok_200.getCode());	
	}
}
