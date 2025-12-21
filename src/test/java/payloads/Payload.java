package payloads;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Cart;
import pojo.CartProduct;
import pojo.Geolocation;
import pojo.Login;
import pojo.Name;
import pojo.Product;
import pojo.Products;
import pojo.User;

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
	
	private static final String categories[] = {"women's clothing","electronics", "furniture", "jewelery", "men's clothing"};
	
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
	
	//User payload
	
	//String email, String username, String password, Name name, Address address, String phone
	
	public static User userPayLoad() {
		
		//Name
		String firstname = faker.name().firstName();
		String lastname = faker.name().lastName();
		
		Name name = new Name(firstname, lastname);
		
		
		//Geolocation
		String lat = faker.address().latitude();
		String lng = faker.address().longitude();
		
		Geolocation geolocation = new Geolocation(lat, lng);
		
		//Address
		String city = faker.address().city();
		String street = faker.address().streetName();
		int number = random.nextInt(100);
		String zipcode = faker.address().zipCode();
		
		Address address = new Address(city, street, number, zipcode, geolocation);
		
		//User
		String email = faker.internet().emailAddress();
		String username = faker.name().username();
		String password = faker.internet().password();
		String phonenumber = faker.phoneNumber().cellPhone();
		
		return new User(email, username, password, name, address, phonenumber);
	}
	
	//Cart
		public static Cart cartPayload(int userId) {
	        List<CartProduct> products = new ArrayList<>();
	        
	        
	        // Adding one random product to the cart
	        int productId = random.nextInt(100);
	        int quantity = random.nextInt(10) + 1;
	               
	        CartProduct cartProduct= new CartProduct(productId, quantity);
	        products.add(cartProduct);

	        
	        //new Date()  ----> Returns date like  Wed Feb 19 13:17:45 IST 202
	        // We need to convert this to "yyyy-MM-dd" format in String 
	        
	         SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);// Define output date format
	         String date = outputFormat.format(new Date());//Converting to String
		    
	        return new Cart(userId, date, products);
	    }
	
		//Login
		
		public static Login loginPayLoad() {
			String username=faker.name().username();
			String password=faker.internet().password();
			
			return new Login(username, password);
		}
	

}
