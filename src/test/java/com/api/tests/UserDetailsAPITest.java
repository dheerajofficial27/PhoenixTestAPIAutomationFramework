package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() {
		
		//Header authHeader = new Header("Authorization", getToken(Role.QC));
		
		given()
		.spec(SpecUtil.requestSpecAuth(Role.FD))
		.when()
		.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec())
		.and()
		.body(matchesJsonSchemaInClasspath("response_schema/userdetailsresponseschema.json"));
	}
}
