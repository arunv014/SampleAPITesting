package testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import routes.Routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Product;

	//7 To write all the product test cases (Using mock data)

public class ProductTests extends BaseClass{
	
	//1) Test to retrieve all products
	@Test
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
	@Test
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
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
	public void testToRetrieveProductByCategory() {
		
		given()
			.pathParam("category", "electronics")
		.when()
			.get(Routes.GET_PRODUCTS_BY_CATEGORY)
		.then()
			.statusCode(200)
			.log().body()
			.body("size()", greaterThan(0))
			.body("category", everyItem(notNullValue()))
			.body("category", everyItem(equalTo("electronics")));
	}
	
	//8 Test to add a new product
	
	//Here the newProduct that we create is in the POJO format, so we need to do serialization (POJO to JSON conversion)
	
	@Test
	public void testCreateProduct() {
		
		Product newProduct = Payload.productPayload();
		
		int productId = given()
			.contentType(ContentType.JSON)
			.body(newProduct)
		.when()
			.post(Routes.CREATE_PRODUCT)
		.then()
			.statusCode(201)
			.log().body()
			.body("id", notNullValue())
			.body("title", equalTo(newProduct.getTitle()))
			.extract().jsonPath().getInt("id"); //Extracting Id from respnse body
			
		System.out.println(productId);
		
	}
	
	//9 Test to update existing product
	
	@Test
	public void testUpdateProduct() {
		
		int productId=configReader.getIntProperty("productId");
		
		Product updatedPayload = Payload.productPayload();
		
		given()
			.contentType(ContentType.JSON)
			.body(updatedPayload)
			.pathParam("id", productId)
		.when()
			.put(Routes.UPDATE_PRODUCT)
		.then()
			.log().body()
			.statusCode(200)
			.body("title", equalTo(updatedPayload.getTitle()));
	}
	
	//10 Test to delete existing product
	
	@Test
	public void testDeleteProduct() {
		
		int productId = configReader.getIntProperty("productId");
		
		given()
			.pathParam("id", productId)
		.when()
			.delete(Routes.DELETE_PRODUCT)
		.then()
		.statusCode(200);
		
	}

}
