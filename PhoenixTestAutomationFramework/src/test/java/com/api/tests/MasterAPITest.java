package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.utils.AuthTokenProvider.getToken;
import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void MasterApiTest() {

		Header authHeader = new Header("Authorization", getToken(Role.FD));
		given().baseUri(ConfigManager.getProperty("BASE_URI")).header(authHeader).contentType(ContentType.JSON).when()
				.post("master").then().log().all().statusCode(200).body("message", equalTo("Success"))
				.body("data.size()", equalTo(10))
				.body(matchesJsonSchemaInClasspath("response_schema/masterApiResponse.json"))
				.body("$", hasKey("message")) //$ means - find key in whole big JSON schema
				.body("data.mst_oem.id", everyItem(notNullValue()));

	}

}
