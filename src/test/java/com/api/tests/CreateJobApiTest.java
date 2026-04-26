package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.models.CreateJobPayload;
import com.api.models.Customer;
import com.api.models.CustomerAddress;
import com.api.models.CustomerProduct;
import com.api.models.Problems;
import com.api.utils.SpecUtil;

public class CreateJobApiTest {

	@Test
	public void CreateJobApiTest() {

		Customer customer = new Customer("Dheeraj", "Sharma", "7007183451", "", "dks@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("16 K", "Sunshine Aprtment", "New Kndli", "RedFox Hotel",
				"Mayur Vihar", "110096", "India", "Delhi");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "14097785894622",
				"14097785894622", "14097785894622", "2025-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemsArray);

		given().spec(SpecUtil.requestSpecAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtil.responseSpec());

	}

}
