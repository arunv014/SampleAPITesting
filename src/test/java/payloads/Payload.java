package payloads;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Cart;
import pojo.Product;
import pojo.Products;

public class Payload {
	
	//3 
	
	/*
	 * The information from here is passed to the POJO classes to set as the value
	 * for the JSON which is used as the payoad for API call For some standard
	 * values we will use the java faker library to generate, like phone number,
	 * email ID and all.
	 * 
	 * We will create a user defined method for each payload. Here it is
	 * productPayload()
	 * 
	 * productPayload(){
	 * 
	 * }
	 * 
	 * But what will this method return, it will be in the format of Product pojo
	 * class thus,
	 * 
	 * Product productPayload() {
	 * 
	 * }
	 */
	
	
	//importing faker library to generate the test data
	private static final Faker faker = new Faker();
	
	//creating a list of categories from which randomly we can assign the categor.
	
	private static final String categories[] = {"books","cakes", "furniture", "Banks", "AI bots"};
	
	//creating Random class object in Java to randomly generate 
	private static final Random random = new Random();
	
	
	//Product payload
	public static Product productPayload(){
		
		/*
		 * String title, double price, String description, String image, String category
		 */
		
		String title = faker.commerce().productName();
		double price = Double.parseDouble(faker.commerce().price());
		String description = faker.lorem().sentence();
		String imageUrl = "https://i.pravatar.cc/100";
		String category = categories[random.nextInt(categories.length)];
		
		return new Product(title, price, description, imageUrl, category);
		
	}
	
	
	

}
