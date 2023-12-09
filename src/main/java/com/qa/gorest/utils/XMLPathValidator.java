package com.qa.gorest.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkexception.AppFrameworkException;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XMLPathValidator {

	private XmlPath getXmlPAth(Response response) {
		String responseBody = response.getBody().asString();
		return new XmlPath(responseBody);
	}

	public <T> T xmlGet(Response response, String XmlPathExpression) {
		XmlPath XmlPath = getXmlPAth(response);
		return XmlPath.get(XmlPathExpression);
	}

	public <T> List<T> xmlGetList(Response response, String XmlPathExpression) {
		XmlPath XmlPath = getXmlPAth(response);
		return XmlPath.getList(XmlPathExpression);
	}

	public <T> List<Map<String, T>> readListOfMaps(Response response, String XmlPathExpression) {
		XmlPath XmlPath = getXmlPAth(response);
		return XmlPath.getList(XmlPathExpression);
	}
}
