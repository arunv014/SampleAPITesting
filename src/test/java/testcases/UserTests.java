package testcases;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.User;
import routes.Routes;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

public class UserTests extends BaseClass
{
	
	//1 Test to retrieve all users
	@Test
	public void testGetAllUsers() 
	{
		given()
		.when()
			.get(Routes.GET_ALL_USERS)
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.log().body()
		.body("size()", greaterThan(0));
	}
	
	//2 Test to retrieve user by Id
	
	@Test
	public void testRetrieveUserById() 
	{
		given()
			.pathParam("id", configReader.getIntProperty("userId"))
		.when()
			.get(Routes.GET_USER_BY_ID)
		.then()
			.statusCode(200)
			.log().body()
			.contentType(ContentType.JSON)
			.body("id", notNullValue());
	}
	
	//3 Test to retrieve users within a limit
	
	@Test
	public void testRetrieveUserWithinLimit() {
		
		given()
			.pathParam("limit", 5)
		.when()
			.get(Routes.GET_USERS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.log().body()
			.body("size()", equalTo(5));
	}
	
	//4 Test to retrieve users in descending order
	
	@Test
	public void testToRetrieveUserDescending() {
		
		Response response=given()
			.pathParam("order", "desc")
		.when()
			.get(Routes.GET_USERS_SORTED)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.extract().response();
		
		List<Integer> userId = response.jsonPath().getList("id", Integer.class);
		
		assertEquals(isDescending(userId), true);
	}
	
	//5 Test to retrieve users are retrieved in ascending order
	
	@Test
	public void testRetrieveUsersAscending() {
		
		Response response=given()
			.pathParam("order", "asc")
		.when()
			.get(Routes.GET_USERS_SORTED)
		.then()
		.statusCode(200)
		.body("size()", greaterThan(0))
		.extract().response();
		
		List<Integer> userId = response.jsonPath().getList("id", Integer.class);
		
		assertEquals(isAscending(userId), true);
	}
	
	//6 Test to check creation of User
	
	@Test
	public void testCreateuser() {
		
		User newuser = Payload.userPayLoad();
		
		int userId=given()
			.contentType(ContentType.JSON)
			.body(newuser)
			
		.when()
			.post(Routes.CREATE_USER)
		.then()
		.statusCode(201)
		.log().body()
		.body("id", notNullValue())
		.extract().jsonPath().getInt("id");
	}
	
	//7 Test to check updation of User
	@Test
	public void testUpdateUser() {
		
		User updatedUser = Payload.userPayLoad();
		
		given()
			.contentType(ContentType.JSON)
			.body(updatedUser)
			.pathParam("id", configReader.getIntProperty("userId"))
		.when()
			.put(Routes.UPDATE_USER)
		.then()
		.statusCode(200)
		.log().body()
		.body("username", equalTo(updatedUser.getUsername()));
	}
	
	
	//8 Test to check deletion of user
	@Test
	public void testDeleteUser() {
		given()
			.pathParam("id", configReader.getIntProperty("userId"))
		.when()
			.delete(Routes.DELETE_USER)
		.then()
			.statusCode(200);
	}

}
