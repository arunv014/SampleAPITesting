package pojo;

public class Cart {
	
	
	private int id;
	private String userId;
	private String date;
	private Products products;
	
	public Cart(int id, String userId, String date, Products products){
		this.id=id;
		this.userId=userId;
		this.date=date;
		this.products=products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

}
