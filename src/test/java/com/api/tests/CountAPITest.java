package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class CountAPITest {
	@Test
	public void countAPITest() {

		Header authHeader = new Header("Authorization", getToken(Role.FD));
		given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON).header(authHeader).when()
				.get("/dashboard/count").then().log().all().statusCode(200).time(lessThan(1500L)).and()
				.body("message", equalTo("Success")).body("data.size()", equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.key",
						Matchers.containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response_schema/countApiResponse.json"));

	}

	@Test
	public void countAPITest_MissingAuthToken() {

		given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON).log().headers().when()
				.get("/dashboard/count").then().log().all().statusCode(401);

	}
}
