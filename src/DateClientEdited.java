/*Christian Bazoian
 * Lab 2 Part 3
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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class DateClientEdited 
{

	public static void main(String [] args) throws InterruptedException, ClassNotFoundException
	{
		ObjectOutputStream oos = null; //create new 
		ObjectInputStream ois = null; //create new
		Socket sock = null; //create new

		try
		{
			//declare a new socket
			sock = new Socket("127.0.0.1",6000);

			//write to socket using ObjectOutputStream
			oos = new ObjectOutputStream(sock.getOutputStream());

			//take in user input
			Scanner scan = new Scanner(System.in);
			String userInput = scan.nextLine();
			
			//write the user input to the socket
			oos.writeObject(userInput);

			//read the server response message
			ois = new ObjectInputStream(sock.getInputStream());
			
			//put the server response into a string
			String message = (String) ois.readObject();
			
			//print out the message to the terminal
			System.out.println(message);

			//close everything
			scan.close();
			ois.close();
			oos.close();
			
			//sleep
			Thread.sleep(100);



			//close the socket connection
			sock.close();
		}
		catch(IOException ioe)
		{
			System.err.println(ioe);
		}

	}
}
