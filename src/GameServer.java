import java.io.*;
import java.net.*;

public class GameServer {

	private Socket echoSocket;
	public PrintWriter serverOut;
	public BufferedReader serverIn;
	private PrintWriter clientOut;
	private BufferedReader clientIn;
	private ServerSocket serverSocket;
	private Socket clientSocket;

	public GameServer () {
		try {
			serverSocket = new ServerSocket(1337);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 1337");
			System.exit(-1);
		}

	    new Thread( new Runnable()
	    {
	      public void run()
	      {
	  		clientSocket = null;
	        try
	        {
	          System.out.println( "Gleich hängt er!" );
				clientSocket = serverSocket.accept();
				serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
				serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String clientInput;
				while ((clientInput = serverIn.readLine()) != null) {
					System.out.println( "ServerOut: " + clientInput);
					serverOut.println(clientInput);
				}
				System.out.println( "Hier hängt er!" );
	        }
	        catch ( IOException e )
	        {
				System.out.println("Accept failed: 1337");
				System.exit(-1);
	        }
	      }
	    }).start();
	}

	public void finish () {
		serverOut.close();
		try {
			serverIn.close();
			clientSocket.close();
			serverSocket.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
