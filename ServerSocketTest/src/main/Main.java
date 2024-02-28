package main;

import java.io.BufferedReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import data.WebServerConnection;

public class Main {

	public static void main(String[] args) {
		
		try (//pick a port to connect and listen to.
		ServerSocket socket = new ServerSocket(8097)) {
			System.out.println("Listening");
			
			while(true)//while connected
			{
				Socket client=socket.accept();
		
			
				//read all incoming messages
				InputStreamReader input=new InputStreamReader(client.getInputStream());
				//buffer these incoming messages
				BufferedReader buff= new BufferedReader(input);
				//store the incoming messages
				StringBuilder request=new StringBuilder();
				
				String line="";//temp holding one line of our message
				line = buff.readLine();//gives us the first line
			//	(line = buff.readLine()) != null
				//!line.isBlank()(
				while(!line.isBlank())//while the line is not blank continue to read
				{
					request.append(line+"\r\n");//add line to request
					line=buff.readLine();//check the next line
				}
				System.out.println("--REQUEST--");
				
				System.out.println(request);
				
				//Decide how to responed to request
				
				
				
				
				
				//get the first line of the request
				String firstLine=request.toString().split("\n")[0];
				//System.out.println(firstLine);
				//get the second element from first line
				String resource=firstLine.split(" ")[1];
				String method=firstLine.split(" ")[0];
				
				System.out.println("Method: "+method);
				
				System.out.println("Resource: "+resource);
				//compare the element to our list of things
				//send back apprpiate thing
				if(resource.equals("/people"))
				{
					OutputStream clientOutput=client.getOutputStream();//output all responses
					
					clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());//encode to bytes
					clientOutput.write(("\r\n").getBytes());//blank line
					clientOutput.write(("Mark \r\nDorian \r\nMehir \r\nDan \r\nKhate \r\nRobert \r\nCiaran \r\nDillon").getBytes());//encode to bytes
				
					clientOutput.flush();//empty the built up buffer
				}
				else
				{
					OutputStream clientOutput=client.getOutputStream();//output all responses
					
					clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());//encode to bytes
					clientOutput.write(("\r\n").getBytes());//blank line
					clientOutput.write(("Hello").getBytes());//encode to bytes
					
					clientOutput.flush();//empty the built up buffer
					
				}
				
				
				
				
				client.close();// get ready for next message
//				WebServerConnection con = new WebServerConnection();
//				con.connect();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
