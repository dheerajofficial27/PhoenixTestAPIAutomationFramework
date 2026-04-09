package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.models.UserLoginCredentials;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginApiTest {
	
	@Test
	public void loginAPITest() {
		
		UserLoginCredentials userlogin = new UserLoginCredentials("iamfd", "password");
		
		given()
		.baseUri("http://64.227.160.186:9000/v1")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(userlogin)
		.log().headers()
		.log().uri()
		.log().body()
		.when()
		.post("login")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1500L))
		.body("message", equalTo("Success"))
		.and()
		.body(matchesJsonSchemaInClasspath("response_schema/loginResponseSchema.json"));
	}

}
