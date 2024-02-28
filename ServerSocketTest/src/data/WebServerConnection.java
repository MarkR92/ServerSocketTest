package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class WebServerConnection {

	public static void connect()
	 { 
	     try { 
	         // Step 1: Define the URL 
	         // Replace with your desired URL 
	         String url = " http://localhost:8077"; 

	         // Step 2: Create a URL object ead
	         URL serverUrl = new URL(url); 

	         // Step 3: Open a connection 
	         HttpURLConnection connection = (HttpURLConnection)serverUrl.openConnection(); 

	         // Step 4: Set the request method to GET 
	         connection.setRequestMethod("GET"); 

	         // Step 5: Get the HTTP response code 
	         int responseCode = connection.getResponseCode(); 
	         System.out.println("Response Code: "
	                            + responseCode); 

	         // Step 6: Read and display response content 
	         BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream())); 

	         String line; 
	         StringBuilder responseContent 
	             = new StringBuilder(); 

	         while ((line = reader.readLine()) != null) { 
	             responseContent.append(line); 
	         } 

	         reader.close(); 

	         // Defining the file name of the file 
	         Path fileName = Path.of("../temp.html"); 

	         // Writing into the file 
	         Files.writeString(fileName, responseContent.toString()); 

	         // Print the response content 
	         System.out.println( 
	             "Response Content:\n"
	             + responseContent.toString()); 
	     } 
	     catch (IOException e) { 
	         e.printStackTrace(); 
	     } 
	 } 
}
