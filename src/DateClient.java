import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class DateClient 
{

	public static void main(String [] args)
	{
		try
		{
			//create a new socket
			Socket sock = new Socket("127.0.0.1",6013);
			
			
			InputStream in = sock.getInputStream();
			
			BufferedReader bin = new BufferedReader(new InputStreamReader(in));
			
			//read the date from the socket
			String line;
			
			while((line = bin.readLine()) != null)
			{
				System.out.println(line);
			}
			
			//close the socket connection
			sock.close();
		}
		catch(IOException ioe)
		{
			System.err.println(ioe);
		}
		
	}
}
