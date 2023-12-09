package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmadeusAuth2Test extends BaseTest {

	private String accessToken;

	@Parameters({ "BaseURI", "grantTpye", "clientId", "clientSecret" })
	@BeforeMethod
	public void getFlightAPISetUp(String BaseURI, String garntType, String ClientId, String clientSecret) {
		restClient = new RestClient(prop, BaseURI);
		accessToken = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT, garntType, ClientId, clientSecret);
	}

	@Test
	public void getInfoTest() {
		RestClient flightRestClient = new RestClient(prop, BaseURI);

		Map<String, String> headerMaps = new HashMap<String, String>();
		headerMaps.put("Authorization", "Bearer " + accessToken);

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", 200);

		Response response = flightRestClient.get(AMADEUS_FLIGHT_INFO_ENDPOINT, headerMaps, queryParams, false, true)
				.then().log().all().extract().response();
		JsonPath js = response.jsonPath();
		String total = js.get("data[0].price.total");
		System.out.println("Total " + total);
	}
}
