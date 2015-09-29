/*
 * Author: Christian Bazoian
 * 
 * This GUI implements the DateClientEdited.java code and expands on it.
 * To use, start the DateServerEdited.java on the desired port
 * Make sure the port matches in the gui and the ip address is your own. Both are defaults
 * Enter your name in the gui input box
 * Server replies how many characters are in your name.
 */




import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;





class BasicSocketGUI extends JFrame
{
	// Declare variables here
	private JPanel main;
	private JMenuItem aboutMenuItem, exitMenuItem;
	private JButton connectButton;
	private JButton sendButton;
	private JTextArea inputTextArea;
	private JTextArea resultsTextArea;
	private JTextField iPField;
	private JTextField portField;
	private JLabel iPLabel;
	private JLabel portLabel;
	
	ObjectOutputStream oos = null; //create new 
	ObjectInputStream ois = null; //create new
	Socket sock = null; //create new



	public BasicSocketGUI() // constructor
	{
		createUserInterface();  // call createUserInterface()
	}




	public void createUserInterface()
	{

		// Returns the contentPane object for this frame.  All programs need this
		Container contentPane = getContentPane();

		// Enable explicit positioning of GUI components   All programs need this
		contentPane.setLayout(new BorderLayout());

		// Add an Icon to the JFrame
		//Image icon = Toolkit.getDefaultToolkit().getImage(null);	
		//setIconImage(icon);

		//make a new border
		Border blackline = BorderFactory.createLineBorder(Color.BLUE);

		//Borders
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();

		//Make Menu bar
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);
		menuBar.setBorder(blackline);






		//============== Main Panel Setup ==============================================

		main = new JPanel(null);  // make the main panel


		contentPane.add(main);




		//============Connect button set up ==================================================
		connectButton = new JButton("Connect");
		connectButton.setBounds(20,20,100,40);
		connectButton.setToolTipText("Connect to server");
		main.add(connectButton);

		//adds action listener to the submitButton
		ActionListener connectListener = new connectButtonSelector();
		connectButton.addActionListener((connectListener));



		//============ Send Button set up ==================================================
		sendButton = new JButton("Send");
		sendButton.setBounds(20,230,100,40);
		sendButton.setToolTipText("Send to server");
		main.add(sendButton);

		//adds action listener to the submitButton
		ActionListener sendListener = new sendButtonSelector();
		sendButton.addActionListener((sendListener));



		//=============== iPField set up ===================================================

		iPField = new JTextField("ip address");
		iPField.setBounds(250,20, 80, 20);
		//set default ip address to self, but it can be edited
		iPField.setText("127.0.0.1");
		iPField.setToolTipText("Type ip address here");
		main.add(iPField);



		//=============== portField set up ===================================================

		portField = new JTextField("port number");


		portField.setBounds(250,60, 80, 20);
		//set default port, but can be changed
		portField.setText("6000");
		portField.setToolTipText("Type port number here");
		main.add(portField);
		
		
		
		//=============== ipLabel set up ======================================================
		iPLabel = new JLabel("IP Address");
		iPLabel.setBounds(335,20,75,20);
		iPLabel.setBorder(loweredbevel);
		iPLabel.setToolTipText("Enter IP Address");

		main.add(iPLabel);
		
		//=============== portLabel set up ====================================================
		portLabel = new JLabel("Port Number");
		portLabel.setBounds(335,60,85,20);
		portLabel.setBorder(loweredbevel);
		portLabel.setToolTipText("Enter Port Number");

		main.add(portLabel);
		//=============== inputTextArea set up ===================================================

		inputTextArea = new JTextArea("Input to server");

		inputTextArea.setBorder(loweredbevel);
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setAutoscrolls(true);
		inputTextArea.setToolTipText("Input to send to the server");
		inputTextArea.setBorder(raisedbevel);

		//make the text area scroll down when no more words fit
		JScrollPane scroll = new JScrollPane(inputTextArea);
		scroll.setBounds(20,130,200,90);
		main.add(scroll);







		//=============== resultsTextArea set up ===================================================

		resultsTextArea = new JTextArea("Output from server");

		resultsTextArea.setBorder(loweredbevel);
		resultsTextArea.setLineWrap(true);
		resultsTextArea.setWrapStyleWord(true);
		resultsTextArea.setAutoscrolls(true);
		resultsTextArea.setToolTipText("Output from the server");
		resultsTextArea.setBorder(raisedbevel);
		resultsTextArea.setEditable(false);

		//make the text area scroll down when no more words fit
		JScrollPane scroll2 = new JScrollPane(resultsTextArea);
		scroll2.setBounds(250,130,200,90);
		main.add(scroll2);









		/*
		 * The following code sets up both the size of the window
		 * and centers the window on the screen. The Toolkit class
		 * is the abstract superclass of all actual implementations 
		 * of the Abstract Window Toolkit.  The getScreenSize( ) method
		 * gets the size of the screen. On systems with multiple 
		 * displays, the primary display is used.
		 */

		int width = 500;
		int height = 350;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);
		setResizable(false);

		setTitle("  Basic Socket Functionality ");
		setSize(width, height);
		setVisible(true);
	}//end class createUserInterface





	public class connectButtonSelector implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				//get ip address from iPField
				String iPAddress = iPField.getText();
				
				//get port number from portField
				String portFieldInput = portField.getText();
				
				//turn portFieldInput into an int
				int portNumber = Integer.parseInt(portFieldInput);
				
				
				// put ipAddress and portNumber together and connect to server
				
				//declare a new socket
				sock = new Socket(iPAddress,portNumber);

				// says submit button was pressed
				System.out.println("Connect button pressed" );
			}// end of try statement



			//Catch exception if any from file writing
			catch (Exception e)
			{
				System.err.println("Error: " + e.getMessage());
			}


		}// end of class		
	}//end action listener





	public class sendButtonSelector implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				// send input to the server

				// says submit button was pressed
				System.out.println("send button pressed" );
				
				//write to socket using ObjectOutputStream
				oos = new ObjectOutputStream(sock.getOutputStream());

				//take in user input
				String userInput = inputTextArea.getText();
				
				//write the user input to the socket
				oos.writeObject(userInput);
				
				
				//display results in resultsTextArea
				
				//read the server response message
				ois = new ObjectInputStream(sock.getInputStream());
				
				//put the server response into a string
				String message = (String) ois.readObject();
				
				//print out the message to the terminal
				resultsTextArea.setText(message);

				//close everything
				ois.close();
				oos.close();
				
				//sleep
				Thread.sleep(100);
				

				
			}// end of try statement



			//Catch exception if any from file writing
			catch (Exception e)
			{
				System.err.println("Error: " + e.getMessage());
			}


		}// end of class		
	}//end action listener





	/**
	 * Sets up the menu bar with File and Edit menus.
	 */

	public JMenuBar createMenuBar ()
	{
		MenuListener menuListener = new MenuListener ();

		JMenu fileMenu = new JMenu ("File");

		aboutMenuItem = new JMenuItem ("About...");
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
				ActionEvent.CTRL_MASK));
		aboutMenuItem.addActionListener (menuListener);

		exitMenuItem = new JMenuItem ("Exit");
		exitMenuItem.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
				ActionEvent.CTRL_MASK));
		exitMenuItem.addActionListener (menuListener);


		fileMenu.addSeparator ();
		fileMenu.add (aboutMenuItem);
		fileMenu.addSeparator ();
		fileMenu.add (exitMenuItem);

		JMenuBar bar = new JMenuBar ();
		bar.add (fileMenu);
		return bar;
	}







	/**
	 * An inner class to handle window events.
	 */
	public class WindowCloser extends WindowAdapter
	{
		//--------------------------------------------------------------
		//  Terminates the program when the window is closed.
		//--------------------------------------------------------------
		public void windowClosing (WindowEvent event)
		{
			exitMenuItem.doClick ();
		}
	}









	public class MenuListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Handles action events caused by menu selections.
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event)
		{
			if (event.getActionCommand ().equals ("Exit"))
				System.exit (0);

			else if (event.getActionCommand ().equals ("About..."))
			{
				JOptionPane.showMessageDialog (null,
						"Basic Socket GUI\n" +
								"Author: Christian Bazoian\n" + 
								"Use with DateServerEdited.java\n" +
						"Version 1.1.0.0");
			}


		}
	}		















	public static void main (String args[])
	{
		BasicSocketGUI application = new BasicSocketGUI();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end main
}//end class NewGUI