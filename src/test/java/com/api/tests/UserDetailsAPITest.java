package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

public class UserDetailsAPITest {

	@Test(description = "Verify the User details is showing correctly in the API response", groups = {"api", "regression", "smoke"})
	public void userDetailsAPITest() {
		
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
