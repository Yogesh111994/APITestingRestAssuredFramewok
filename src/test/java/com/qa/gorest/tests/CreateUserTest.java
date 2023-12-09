package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.constants.AppConstant;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtils;
import com.qa.gorest.utils.StringUtils;
import static org.hamcrest.Matchers.*;


public class CreateUserTest extends BaseTest{

	@BeforeMethod
	public void getUserSetUp() {
		restClient=new RestClient(prop, BaseURI);
	}
	
	@DataProvider
	public Object[][] UserData() {
		return new Object[][] {
			{"yogesh","male","active"},
			{"shiwanee","female","inactive"},
			{"somesh","male","active"}
		};
	}
	
	@DataProvider
	public Object[][] getUserDataFromExcelSheet() {
		return ExcelUtils.excelData(AppConstant.USER_DATA_SHEET_NAME);
	}
	
	@Test(dataProvider="UserData")
	public void createUserTest(String name,String gender,String status) {	
		User user= new User(name,gender,StringUtils.getRandomEmailId(),status);
		int userId=restClient.post(GOREST_ENDPOINT, "json", user,true, true)
		.then().log().all()
		.assertThat()
		.statusCode(APIHttpStatus.CREATED_201.getCode())
		.and()
		.extract().path("id");
		System.out.println(userId);
		
		
		RestClient restClient=new RestClient(prop, BaseURI);
		restClient.get(GOREST_ENDPOINT+"/"+userId,true, true)
        .then().log().all()
                  .assertThat()
                          . statusCode(APIHttpStatus.Ok_200.getCode())
                                 .and()
                                       .body("id", equalTo(userId));
	}
}
