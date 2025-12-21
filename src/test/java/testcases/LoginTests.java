package testcases;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


import payloads.Payload;
import pojo.Login;
import routes.Routes;

public class LoginTests extends BaseClass{
	
	@Test
	public void testInvalidLogin() {
		
		Login loginPayLoad = Payload.loginPayLoad();
		
		given()
			.contentType(ContentType.JSON)
			.body(loginPayLoad)
		.when()
			.post(Routes.AUTH_LOGIN)
		.then()
			.statusCode(401)
			.log().body()
			;
	}
	
	@Test
	public void testValidLogin() {
		String username = configReader.getProperty("username");
		String password = configReader.getProperty("password");
		
		Login loginPayLoad = new Login(username, password);
		
		String token = given()
			.contentType(ContentType.JSON)
			.body(loginPayLoad)
		.when()
			.post(Routes.AUTH_LOGIN)
		.then()
			.statusCode(201)
			.extract().jsonPath().get("token");
		
		System.out.println(token);
	}

}
