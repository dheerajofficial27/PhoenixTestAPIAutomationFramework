package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.models.UserLoginCredentials;
import com.api.utils.SpecUtil;

public class LoginApiTest {
	
	@Test
	public void loginAPITest() throws IOException {
		
		UserLoginCredentials userlogin = new UserLoginCredentials("iamfd", "password");
		
		given()
		.spec(SpecUtil.requestSpec(userlogin)) //calling request method from specUtil package
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.responseSpec())
		.body("message", equalTo("Success"))
		.and()
		.body(matchesJsonSchemaInClasspath("response_schema/loginResponseSchema.json"));
	}

}
