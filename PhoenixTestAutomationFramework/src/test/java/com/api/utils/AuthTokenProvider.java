package com.api.utils;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.models.UserLoginCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	public static String getToken(Role role) {
		
		UserLoginCredentials usercredential = null;
		if(role == Role.FD) {
			usercredential = new UserLoginCredentials("iamfd", "password");
		}
		else if(role == Role.SUP) {
			usercredential = new UserLoginCredentials("iamsup", "password");
		}
		else if(role == Role.ENG) {
			usercredential = new UserLoginCredentials("iameng", "password");
		}
		else if(role == Role.QC) {
			usercredential = new UserLoginCredentials("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(usercredential).when().post("login").then().log()
				.ifValidationFails().statusCode(200).body("message", Matchers.equalTo("Success")).extract().body()
				.jsonPath().getString("data.token");

		return token;
	}

}
