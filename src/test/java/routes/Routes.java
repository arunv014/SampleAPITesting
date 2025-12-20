package routes;

public class Routes {
	
	// 1
	//This class is purely used for storing the base URL ,end points for all APIs
	
	
	/*
	 * All wil be defined as public static final, since it needs to be accessed from
	 * all parts of the project without creating an object and also will not be
	 * changed
	 */
	 
	
	public static final String BASE_URl="https://fakestoreapi.com";
	
	//Products - here we will add all the endpoints replated to products
	public static final String GET_ALL_PRODUCTS = "/products";
	public static final String GET_PRODUCT_BY_ID = "/products/{id}";
	public static final String GET_PRODUCTS_WITH_LIMIT = "/products?limits={limit}";
	public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
	public static final String GET_ALL_CATEGORIES = "/products/categories";
	public static final String GET_PRODUCTS_BY_CATEGORY = "/products?category={category}";
	public static final String CREATE_PRODUCT = "/products";
	public static final String UPDATE_PRODUCT = "/products/{id}";
	public static final String DELETE_PRODUCT = "/products/{id}";
	
	
	

}
