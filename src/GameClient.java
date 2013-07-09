import java.io.*;
import java.net.*;

public class GameClient {

	private Socket echoSocket;
	private PrintWriter serverOut;
	private BufferedReader serverIn;
	private PrintWriter clientOut;
	private BufferedReader clientIn;
	private ServerSocket serverSocket;
	private Socket clientSocket;

	public GameClient () {
				
		try {
			echoSocket = new Socket("localhost", 1337);
			clientOut = new PrintWriter(echoSocket.getOutputStream(), true);
			clientIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		    System.out.println( "KeepAlive: " + echoSocket.getKeepAlive() );           
			clientOut.println( "Teeeeest ");          
		    System.out.println( "clientIn.readLine()" + clientIn.readLine() );           

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		} 
	}

	public void finish () {
		clientOut.close();
		try {
			clientIn.close();
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
