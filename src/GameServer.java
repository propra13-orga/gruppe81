import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

/**
 * Klasse fuer den Verbindungsaufbau im Netzwerkmodus von Serverseite
 */
public class GameServer {

	private Socket echoSocket;
//	public PrintWriter serverOut;
//	public OutputStream serverOutStream;
	public BufferedReader serverIn;
//	public ObjectOutputStream serverObjectOut;
	private PrintWriter clientOut;
	private BufferedReader clientIn;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private MainWindow mainWindow;
	private MyKeyListener kND;
	private int clientCount;
	public GameServerThread gameServerThread;
	
	public GameServer () {
		try {
			serverSocket = new ServerSocket(1337);
		} catch (IOException e) {
			System.out.println("Could not listen on port: 1337");
			System.exit(-1);
		}

		gameServerThread = new GameServerThread(serverSocket);
		gameServerThread.start();
//	    new Thread( new Runnable()
//	    {
//	      public void run()
//	      {
//	  		clientSocket = null;
//	        try
//	        {
//	          System.out.println( "Warte auf Verbindung vom Client" );
//				clientSocket = serverSocket.accept();
//		          System.out.println( "Verbindung vom Client wird hergestellt" );
//				serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
//				serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				String clientInput;
//				kND = new MyKeyListener();
//				while ((clientInput = serverIn.readLine()) != null) {
//					System.out.println( "ServerOut: " + clientInput);
//					serverOut.println(clientInput);
//					String[] splitClientInput = clientInput.split(" ");
//					System.out.println( "splitClientInput[0] " + splitClientInput[0]);
//					if (kND != null) {
//						System.out.println( "splitClientInput[0] " + splitClientInput[0]);
//						if (splitClientInput[0].equals("KC")) {
//							System.out.println( "splitClientInput[1] " + splitClientInput[1]);
//							System.out.println( "splitClientInput[2] " + splitClientInput[2]);
//							if (splitClientInput[1].equals("+")) {
//								kND.keyPressed(Integer.parseInt(splitClientInput[2]));
//							} else {
//								kND.keyReleased(Integer.parseInt(splitClientInput[2]));
//							}
//						}
//					}
//				}
//				System.out.println( "Hier hängt er!" );
//	        }
//	        catch ( IOException e )
//	        {
//				System.out.println("Accept failed: 1337");
//				System.exit(-1);
//	        }
//	      }
//	    }).start();
	}

//	public void setMainWindow (MainWindow mainWindow) {
//		this.mainWindow = mainWindow;
//	}
	
	public MyKeyListener getKeyListener () {
		kND = gameServerThread.getKeyListener();
		return kND;
	}
	
	public void finish () {
		gameServerThread.finish();
		
//		serverOut.close();
//		try {
//			serverIn.close();
//			clientSocket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			serverSocket.close();		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
