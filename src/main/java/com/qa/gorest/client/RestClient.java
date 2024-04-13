package com.qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.frameworkexception.AppFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private Properties prop;
	private String BaseURI;
	private static RequestSpecBuilder specBuilder;
	//private static final String BRARER_TOKEN = "fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674";
	//private static final String BASE_URI = "https://gorest.co.in";
     private boolean  isAuthorizationHeaderAdded= false;

	
	public RestClient(Properties prop,String BaseURI) {
		specBuilder = new RequestSpecBuilder();
		this.prop=prop;
		this.BaseURI=BaseURI; 
	}

	public void addAuthorization() {
		if(!isAuthorizationHeaderAdded) {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("Token"));
		isAuthorizationHeaderAdded=true;
		}
	}
	
	
	
	private void setRequestContent(String contentType) {
	     switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;

		default:
			System.out.println("Please pass the right content type"+contentType );
			throw new AppFrameworkException("INVALIDCONTENTTYPE");
		}
	}

	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth) {
		addAuthorization();
		}
		return specBuilder.build();
	}
 
	private RequestSpecification createRequestSpec(Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth) {
			addAuthorization();
			}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParms,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth) {
			addAuthorization();
			}
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if (queryParms != null) {
			specBuilder.addQueryParams(queryParms);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody,String contentType,Map<String, String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth) {
			addAuthorization();
			}
		setRequestContent(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody,String contentType,boolean includeAuth) {
		specBuilder.setBaseUri(BaseURI);
		if(includeAuth) {
			addAuthorization();
			}
		setRequestContent(contentType);
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	// HTTP method utils
	
	//=====================GET===========================
	
	public Response get(String serviceUrl,boolean includeAuth,boolean log ) {
		if(log) {
		return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> headersMap,boolean includeAuth,boolean log ) {
		if(log) {
		return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap,includeAuth)).when().get(serviceUrl);
	}
	
	public Response get(String serviceUrl,Map<String, String> headersMap,Map<String, Object> queryParms,boolean includeAuth,
			boolean log ) {
		if(log) {
		return RestAssured.given(createRequestSpec(headersMap, queryParms,includeAuth)).log().all().when().get(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(headersMap, queryParms,includeAuth)).when().get(serviceUrl);
	}
	
	//=====================POST===========================
	
	public Response post(String serviceUrl,String contentType,Object requestBody,
			Map<String, String> headersMap,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
			.when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
		.when().post(serviceUrl);
	}
	
	public Response post(String serviceUrl,String contentType,Object requestBody,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
			.when().post(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		.when().post(serviceUrl);
	}
	
	//=====================PUT===========================
	
	public Response put(String serviceUrl,String contentType,Object requestBody,
			Map<String, String> headersMap,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
			.when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
		.when().put(serviceUrl);
	}
	
	public Response put(String serviceUrl,String contentType,Object requestBody,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
			.when().put(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		.when().put(serviceUrl);
	}
	
	//=====================PATCH===========================
	
	public Response patch(String serviceUrl,String contentType,Object requestBody,
			Map<String, String> headersMap,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
			.when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
		.when().patch(serviceUrl);
	}
	
	public Response patch(String serviceUrl,String contentType,Object requestBody,boolean includeAuth,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
			.when().patch(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		.when().patch(serviceUrl);
	}
	
	//=====================DELETE===========================
	public Response delete(String serviceUrl,boolean includeAuth,boolean log ) {	
		if(log) {
			return RestAssured.given(createRequestSpec(includeAuth)).log().all().when().delete(serviceUrl);
		}
		return RestAssured.given(createRequestSpec(includeAuth)).when().delete(serviceUrl);
	}
	
	
public  String getAccessToken(String serviceUrl, String grantTpye,String clientId,String clientSecret) {
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		String accessTokenId = given().log().all()
		           .headers("Content-Type","application/x-www-form-urlencoded")
		           .formParam("grant_type", grantTpye)
		           .formParam("client_id", clientId)
		           .formParam("client_secret", clientSecret)
	  .when()
		           .post(serviceUrl)
	  .then().log().all()
		           .assertThat()
		           .statusCode(APIHttpStatus.Ok_200.getCode())
		           .extract().path("access_token");
		System.out.println("Token Id : "+accessTokenId);
		return accessTokenId;
	}
	
}
