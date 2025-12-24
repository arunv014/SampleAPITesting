package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import routes.Routes;

public class SchemaTests extends BaseClass{
	
	@Test
	public void testProductSchema() {
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", configReader.getIntProperty("productId"))
		.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		.then()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("productSchema.json"));
	}
	@Test
	public void testCartSchema() {
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", configReader.getIntProperty("cartId"))
		.when()
			.get(Routes.GET_CART_BY_ID)
		.then()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("cartSchema.json"));
	}
	
	@Test
	public void testUserSchema() {
		
		given()
			.contentType(ContentType.JSON)
			.pathParam("id", configReader.getIntProperty("userId"))
		.when()
			.get(Routes.GET_USER_BY_ID)
		.then()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userSchema.json"));
	}

}
