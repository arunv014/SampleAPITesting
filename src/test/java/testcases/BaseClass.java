package testcases;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import routes.Routes;
import utils.ConfigReader;

public class BaseClass {
	
	//4 
	
	/*
	 * Base class contains common and helper methods for all test cases. Here we
	 * will do the loggin feature also. Using RequestLoggingFilter class.
	 * All testcase classes will extend this BaseClass to get access the common methods.
	 */
	
	//Object for accessing the ConfigReader class
	ConfigReader configReader;
	
	
	//TestNG method , this is the first to be executed and after this only the rest of the test cases will get executed.
	@BeforeClass
	public void setup() 
	{
		//here we need to defin the BASE_URL, we need to get it from the routes and also integrate a restAssured concept to assign it as the base URL for all.
		//The advantage is that the next time you call the endpoints, you need to only call the endpoints no need to attach it with the base url.
		RestAssured.baseURI= Routes.BASE_URl;
		
		//initialize the configReader object
		configReader = new ConfigReader();
	}
	

}
