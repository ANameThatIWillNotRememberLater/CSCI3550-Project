//import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) //throws IOException
	{
		final int actionMIN = 1; //TODO: needed?
		final int actionMAX = 3; //TODO: adjust
		
		boolean sentinel = true;
		String buffer;
		
		// TODO: Add data entry datatype
		// Desired data: 1. Deck info (Class, tags); 2. Games record (wins/losses, etc)
		
		// TODO: Initialize array of data entries
		
		// Will tentatively use TCP sockets
		// May add means of maintaining multiple connections
		
		ServerSocket welcomeSocket = null;
		try
		{
			// Arbitrary port number of 9999 selected
			welcomeSocket = new ServerSocket(9999);
		}
		catch (IOException ioe)
		{
			System.out.println("Error establishing socket. Terminating server.");
			sentinel = false;
		}
		
		// TODO: Read in data from serialized file
		
		// while server is active
		while (sentinel)
		{
			Socket connectionSocket;
			BufferedReader inFromClient;
			DataOutputStream outToClient;
			try
			{
				connectionSocket = welcomeSocket.accept();
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			
			// While connection is active // Until kill signal?
			while (true)
			{
				// Receive message: Action identifier: Which action to take
				/*buffer = inFromClient.readLine();
				int action = Integer.parseInt(buffer); // or*/
				int action = inFromClient.read();
				System.out.printf("Request received: Action %d\n", action);
				
				// End current connection if kill signal received // Switch to a kill method?
				if (action == actionMAX)
				{
					connectionSocket.close();
					// TODO: Update 
					break;
				}
				
				// Otherwise, perform appropriate action to request received
				// 1. Simple transfer of internal data
				if (action == 1)
				{
					fullTransfer(connectionSocket);
				}
				
				// 2. Add new entry
				if (action == 2)
				{
					newEntry(connectionSocket);
				}
				
				// Additional Functions here
			}
			
			}
			catch (IOException ioe)
			{
				System.out.println("Error connecting with client.");
				continue;
			}
		}
		
		//welcomeSocket.close();
	}

	private static boolean fullTransfer(Socket s)
	{
		//
		
		//TODO: Placeholder
		return false;
	}
	
	private static boolean newEntry(Socket s)
	{
		// TODO: Placeholder
		return false;
	}
}
