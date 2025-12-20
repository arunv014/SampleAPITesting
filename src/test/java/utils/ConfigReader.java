package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	//6
	
	/*
	 * We need a special class to read the properties file. Class name is
	 * "Properties". Properties properties;
	 * 
	 * To read the file we need to specify from where the file should be read i.e the
	 * part of file. Properties properties; private static final String CONFIG_FILE_PATH= ".\\src\\test\\resources\\config.properties"
	 * 
	 * 
	 * Now define a userdefined method to read and return the data from the config.properties file.
	 * 
	 */
	
	Properties properties;
	private static final String CONFIG_FILE_PATH = ".\\src\\test\\resources\\config.properties";
	
	//Constructor for load the config.properties file
	
	public ConfigReader(){
		properties=new Properties();
		try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)){
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file");
		}
	}
	
	//User defined method for reading the data
	//based on the key passed (name defined in the properties file), we need o get the value.
	
	//for string values
	public String getProperty(String key) {
		
		return properties.getProperty(key);
		
	}
	
	//for integer values
	
	public int getIntProperty(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}
	
	

}
