package testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.Map;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Product;
import routes.Routes;
import utils.DataProviders;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

//9 Test Cases using the test data in Product.json file. Example of data driven test

public class ProductDataDrivenTest extends BaseClass{
	
	//For adding the data provider here, we will add it to the @Test annotation as 
	//@Test(dataprovider = jsonDataProvider, utils.DataProviders.Class)
	
	/*
	 * As soon as we give
	 * this"(dataProvider = \"jsonDataProvider\", dataProviderClass = utils.DataProviders.class)"
	 * The test method will know that the data is being fed from the data provider
	 * and will feed data one by one as a Map. It is an array of maps.
	 */
	
	@Test(dataProvider = "jsonDataProvider", dataProviderClass = utils.DataProviders.class)
	void testAddNewProduct(Map<String, String> data) {
		
		/*
		 * First extract data individually from the Map and then store in variables.
		 * Then we will create a new POJO class object with those data and then pass
		 * that as the input.
		 */
		
		String title = data.get("title");
		double price = Double.parseDouble(data.get("price"));
		String description = data.get("description");
		String image = data.get("image");
		String category = data.get("category");
		
		Product newProduct = new Product(title, price, description, image, category);
		
		
		int productId=given()
			.contentType(ContentType.JSON)
			.body(newProduct)
		.when()
			.post(Routes.CREATE_PRODUCT)
		.then()
			.log().body()
			.body("id", notNullValue())
			.body("title", equalTo(newProduct.getTitle()))
			.extract().jsonPath().getInt("id");
			
		System.out.println("New productId is ---> "+productId);
		
		//Delete Product (If we dont do this in actual scenarioes the data will get un necessarly added here and accumulate over time.
		
		given()
			.pathParam("id", productId)
		.when()
			.delete(Routes.DELETE_PRODUCT)
		.then()
			.statusCode(200);
		
		System.out.println("Deleted productId is ---> "+ productId);
	}
	
	
	

}
