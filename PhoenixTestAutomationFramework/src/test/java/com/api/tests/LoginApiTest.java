package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.models.UserLoginCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginApiTest {
	
	@Test
	public void loginAPITest() throws IOException {
		
		UserLoginCredentials userlogin = new UserLoginCredentials("iamfd", "password");
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI")) //We have remove the hard coded BASE URI with config properties file.
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
