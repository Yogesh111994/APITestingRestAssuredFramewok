package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;

public class UpdateUserTest extends BaseTest {

	@BeforeMethod
	public void getUserSetUp() {
		restClient=new RestClient(prop, BaseURI);
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"yogesh","male","active"},
		};
	}
	
	@Test(dataProvider="getUserData")
	public void updateUserTest(String name,String gender,String status) {	
		User user= new User(name,gender,StringUtils.getRandomEmailId(),status);
		int userId=restClient.post(GOREST_ENDPOINT, "json", user,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.and()
		.extract().path("id");
		System.out.println(userId);
	   
		RestClient upadteClient= new RestClient(prop, BaseURI);
		user.setName("Somesh");
		user.setStatus("inactive");
		upadteClient.put(GOREST_ENDPOINT+"/"+userId,"json", user, true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.Ok_200.getCode())
		.and()
		.body("name", equalTo("Somesh"))
		.and()
		.body("status", equalTo("inactive"));
		
		
	}
	
	
	
	
}
