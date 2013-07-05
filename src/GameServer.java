import java.io.*;
import java.net.*;

public class GameServer {

	private Socket echoSocket;
	private PrintWriter out;
	private BufferedReader in;

	public GameServer () {
		try {
			echoSocket = new Socket("aztec.de", 80);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		} 
	}

	public void finish () {
		out.close();
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
