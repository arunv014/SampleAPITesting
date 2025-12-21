package testcases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
	
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;
	
	
	//TestNG method , this is the first to be executed and after this only the rest of the test cases will get executed.
	@BeforeClass
	public void setup() throws FileNotFoundException
	{
		//here we need to defin the BASE_URL, we need to get it from the routes and also integrate a restAssured concept to assign it as the base URL for all.
		//The advantage is that the next time you call the endpoints, you need to only call the endpoints no need to attach it with the base url.
		RestAssured.baseURI= Routes.BASE_URl;
		
		//initialize the configReader object
		configReader = new ConfigReader();
		
		//setup filters for logging
		FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.txt");
		PrintStream log = new PrintStream(fos, true);
		requestLoggingFilter = new RequestLoggingFilter(log);
		responseLoggingFilter = new ResponseLoggingFilter(log);
		
		RestAssured.filters(requestLoggingFilter, responseLoggingFilter);
	}
	
	
	//Helper method to check if list is sorted in descending order
	boolean isDescending(List<Integer> list) 
	{
		for(int i=0; i<list.size()-1; i++) {
			if(list.get(i) < list.get(i+1)) {
				return false;
			}
		}return true;
	}
	
	//Helper method to check if list is sorted in ascending order
	boolean isAscending(List<Integer> list) {

		for (int i = 0; i > list.size()-1; i++) {
			if (list.get(i) < list.get(i + 1)) {
				return false;
			}
		}
		return true;
	}
	
	//Helper method to check dates fall within the specified range

	 public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
	    public boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate) {
	       
	    	LocalDate start = LocalDate.parse(startDate, FORMATTER);
	    	
	        LocalDate end = LocalDate.parse(endDate, FORMATTER);

	        for (String dateTime : cartDates) 
	        	{
	            LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), FORMATTER);
	            if (cartDate.isBefore(start) || cartDate.isAfter(end)) {
	                return false; // Immediately return false if any cart date is out of range
	            }
	        }
	        return true; // All dates are within range
	    }
	

}
