package testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import routes.Routes;
import io.restassured.response.Response;

public class ProductTests extends BaseClass{
	
	//1) Test to retrieve all products
	@Test
	public void testGetAllProducts() {
		
		given()
		.when()
			.get(Routes.GET_ALL_PRODUCTS)
		.then()
			.statusCode(200)
			.body("size()", greaterThan(0))
			.log().body();
	}

}
