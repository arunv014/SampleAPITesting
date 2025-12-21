package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviders {
	
	//8 Usedto feed the data to test cases from the input JSON or csv file.
	
	@DataProvider
	public Object[][] jsonDataProvider() throws IOException{
		//JSON file path
		String filePath = ".\\testData\\product.json";
		
		//Read JSON file and map it to a list of maps
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> dataList = objectMapper.readValue(new File(filePath), new TypeReference<List<Map<String, String>>>() {
		});
		
		//Convert List<Map<String, String>> to Object[][]
		Object[][] dataArray = new Object[dataList.size()][];
		for(int i=0; i < dataList.size(); i++) {
			dataArray[i] = new Object[] {dataList.get(i) };
		}
		
		return dataArray;
	}
	
	//For csv
	
	@DataProvider
	public Object[][] csvDataProvider() throws IOException{
		//Path to csv file
		String filePath = ".\\testData\\product.csv";
		
		//Read the csv file and store data in a List
		List<String[]> dataList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			// Skip the first line (header)
			br.readLine();
			
			String line;
			while((line = br.readLine())!= null) {
				String[] data = line.split(","); // Splitting by comma
				dataList.add(data);
			}
		}
		
		// Convert List<String[]> to Object[][]
		Object[][] dataArray = new Object[dataList.size()][];
		for(int i=0; i < dataList.size(); i++) {
			dataArray[i] = dataList.get(i);
		}
		
		return dataArray;
	}

}
