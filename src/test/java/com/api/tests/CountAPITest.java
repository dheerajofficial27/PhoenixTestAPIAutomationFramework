package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.SpecUtil;

public class CountAPITest {
	//updated code
	@Test(description = "Verify the Count details is showing correctly in the API response", groups = {"api", "regression", "smoke"})
	public void countAPITest() {

		given()
		.spec(SpecUtil.requestSpecAuth(Role.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec())
		.and()
		.body("message", equalTo("Success")).body("data.size()", equalTo(3))
		.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body("data.key",
						Matchers.containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
		.body(matchesJsonSchemaInClasspath("response_schema/countApiResponse.json"));

	}

	@Test(description = "Verify the Count API is showing correct status code", groups = {"negative","api", "regression", "smoke"})
	public void countAPITest_MissingAuthToken() {

		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count").then().log().all().statusCode(401);

	}
}
