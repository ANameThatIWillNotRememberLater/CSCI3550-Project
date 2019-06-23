import java.io.*;
import java.util.*;
import java.net.*;

public class Client {

	public static void main(String[] args)
	{

		final int actionMIN = 1;//TODO: Adjust
		final int actionMAX = 3;//TODO: Adjust
		
		// Will tentatively use TCP sockets
		// May add means of maintaining multiple connections
		
		// Arbitrary port number of 9999 selected for server
		// For testing and development, "localhost" will be used as location
		// of server.
		
		boolean connection = true;
		int action = -1;
		String buffer; // TODO: remove?
		
		Socket clientSocket = null;
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		
		try
		{
			clientSocket = new Socket("localhost", 9999);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch (IOException ioe)// If socket error occurs
		{
			connection = false;
		}

		Scanner input = new Scanner(System.in);
		
		System.out.println("User greeting");
		// While connection is maintained // Until kill action?
		while (connection)
		{
			//Put menu in client instead
			// Loop until valid option is selected
			do
			{
				try
				{
					System.out.println("What action would you like? (Please enter only the number of your selection followed by ENTER)");
					System.out.println("Place list of functions here.");//Array of functions?
					System.out.printf("To end communication, enter %d.\n", actionMAX);
					System.out.print("-> ");
					action = input.nextInt();
					// if action is out of bounds
					if (action < actionMIN || actionMAX < action)
					{
						System.out.println("ERROR: Invalid option. Please select again.");
						continue;
					}
					// Send selected action to server
					//buffer = Integer.toString(action); //Use writeInt instead
					outToServer.writeInt(action);
					
					// 1. Receive and display contents of file from server
					if (action == 1)
					{
						fullTransfer(inFromServer);
					}
					
					// 2. Submit new entry
					else if (action == 2)
					{
						newEntry(outToServer, input);
					}
					
					if (action == actionMAX)
					{
						connection = false;
						break;
					}
				}
				catch (InputMismatchException ime)
				{
					System.out.println("ERROR: Please use the proper formatting for selecting an action.");
					continue;
				}
				catch (IOException ioe)
				{
					System.out.println("Communication error with server.\nTerminating connection.");
					connection = false;
					
				}
			}
			while(false);
			
			
		}
		
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error closing socket.");
			e.printStackTrace();
		}
	}

	private static boolean fullTransfer(BufferedReader in)
	{
		//TODO: Placeholder
		return false;
	}
	
	private static boolean newEntry(DataOutputStream out, Scanner in)
	{
		// TODO: placeholder
		return false;
	}
}
