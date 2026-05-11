package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.models.CreateJobPayload;
import com.api.models.Customer;
import com.api.models.CustomerAddress;
import com.api.models.CustomerProduct;
import com.api.models.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {

		Customer customer = new Customer("Dheeraj", "Sharma", "7007183451", "", "dks@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("16 K", "Sunshine Aprtment", "New Kndli", "RedFox Hotel",
				"Mayur Vihar", "110096", "India", "Delhi");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getDatetime(10), "54098785894645",
				"554098785894645", "54098785894645", DateTimeUtil.getDatetime(10), Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getcode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getcode(), "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemList);

		given().spec(SpecUtil.requestSpecAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtil.responseSpec())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}
