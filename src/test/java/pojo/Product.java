package pojo;

public class Product {
	
	//2
	
	/*
	 * Pojo classes are used to create the payload. Payload is sent only for POST,
	 * PATCH etc..
	 * 
	 * Here we use the concept of encapsulation such that the variables are not
	 * accessible directly they are only accessible using the getters and setters methods.
	 * 
	 * Here create constructor along with getters and setters for the private variables.
	 * Constructor always has the same name as the class
	 */
	
	private String title;
	private double price;
	private String description;
	private String image;
	private String category;
	
	//Constructor
	public Product(String title, double price, String description, String image, String category){
		this.title=title;
		this.price=price;
		this.description=description;
		this.image=image;
		this.category=category;
	}
	
	//Getters and setters
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setPrice(String description){
		this.description=description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image){
		this.image=image;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category=category;
	}

}
