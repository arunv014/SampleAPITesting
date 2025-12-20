package testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import routes.Routes;
import io.restassured.response.Response;

public class ProductTests extends BaseClass{
	
	//1) Test to retrieve all products
	//@Test
	public void testGetAllProducts() {
		
		given()
		.when()
			.get(Routes.GET_ALL_PRODUCTS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0));
			//.log().body();
	}
	
	//2 Test to retrieve a single product by Id
	//@Test
	public void testGetProductById() {
		
		given()
			.pathParam("id", configReader.getIntProperty("productId"))
		.when()
			.get(Routes.GET_PRODUCT_BY_ID)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();
	}
	
	
	//3 Test to retrieve products within a limit
	//@Test
	public void testGetProductWithinLimit() {
		
		int limit = 5;
		given()
			.pathParam("limit", limit)
		.when()
			.get(Routes.GET_PRODUCTS_WITH_LIMIT)
		.then()
			.statusCode(200)
			.body("size()", equalTo(limit))
			.log().body();
	}
	
	//4 Test to retrieve products sorted in descending order
	
	//@Test
	public void testToRetrieveSortedProductsDescending() {
		
		Response response=given()
			.pathParam("order", "desc")
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then()
			.statusCode(200)
			.extract().response();
		
		//Whenever you want to get the data from the response, we need to convert it into JSON path
		
		List<Integer> productId= response.jsonPath().getList("id", Integer.class);
		
		//Now we need to check whether these Ids are in descending order
		//isDescending() method is implemented in BaseClass
		assertEquals(isDescending(productId), true);
	}
	
	//5 Test to retrieve products sorted in ascending order
	
	@Test
	public void testToRetrieveAscendingOrderProducts() {
		
		Response response=given()
			.pathParam("order", "asc")
		.when()
			.get(Routes.GET_PRODUCTS_SORTED)
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		List<Integer> productId = response.jsonPath().getList("id", Integer.class);
		//System.out.println(productId);
		assertTrue(isAscending(productId));
		//assertEquals(isAscending(productId), true);
	}
	
	
	
	//6 Test to retrieve all categories
	
	//@Test
	public void testToRetrieveAllCategories() {
		
		given()
		.when()
			.get(Routes.GET_ALL_CATEGORIES)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();
	}
	
	//7 Test to retrieve product by category
	
	//@Test
	public void testToRetrieveProductByCategory() {
		
		given()
			.pathParam("category", "")
		.when()
			.get(Routes.GET_PRODUCTS_BY_CATEGORY)
		.then();
	}

}
