package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.models.UserLoginCredentials;
import com.api.utils.SpecUtil;

public class LoginApiTest {
	
	private UserLoginCredentials userlogin;
	
	@BeforeMethod(description = "Creating Login API Payload")
	public void setup() {
		userlogin = new UserLoginCredentials("iamfd", "password");
	}
	
	@Test(description = "Verifying FD user will be able to login successfully", groups = {"api", "regression", "smoke"})
	public void loginAPITest() throws IOException {
		
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
