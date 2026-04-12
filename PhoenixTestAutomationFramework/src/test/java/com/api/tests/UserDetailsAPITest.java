package com.api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import com.api.constant.Role;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() {
		
		Header authHeader = new Header("Authorization", getToken(Role.QC));
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.accept(ContentType.JSON)
		.header(authHeader)
		.when()
		.get("userdetails")
		.then()
		.log().all()
		.and()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("response_schema/userdetailsresponseschema.json"));
	}
}
