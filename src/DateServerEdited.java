
/* Christian Bazoian
 * Lab2 Part 3
 * 
 * To use:
 * Start the server
 * Start the client
 * type in your first name on the command line
 * the server will reply and tell you how long your name is
 * 
 * Got help from a tutorial on sockets posted here:
 * http://www.journaldev.com/741/java-socket-server-client-read-write-example
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class DateServerEdited 
{
	
	public static void main(String[] args) throws ClassNotFoundException 
	{
		//create a new server
		ServerSocket sock;
		
		try 
		{
			sock = new ServerSocket(6000);
			
			//now listen for connections
			while(true)
			{
				//accept client connection
				Socket client = sock.accept();
				
				//read from socket ObjectInputStream object
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				
				//convert ObjectInputStream object to String
				String message = (String) ois.readObject();
				
				//get the length of the input, which is supposed to be a first name
				int length = message.length();
				
				//create ObjectOutputStream object
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				
				//write object to the Socket
				//oos.writeObject("Message recieved: " + message);

				//send a message back to the client telling them how long their input was.
				oos.writeObject("Your name is " + length + " characters long.");
				
				
				
				//write the Date to the socket
				//pout.println(new java.util.Date().toString());
				
				//cant get this to work
				//terminate the server if client sends exit request
				//if(message.equalsIgnoreCase("exit"))
				//{
					//close the socket and resume listening for connections
					//client.close();
				//}
				
				
			}
		} 
		catch (IOException ioe) 
		{
			
			System.err.println(ioe);
		}
		
		
	}

}
