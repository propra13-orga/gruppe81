import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class GameServerThread extends Thread implements Runnable {

	private Socket clientSocket;
	public PrintWriter serverOut;
	public BufferedReader serverIn;
	private ServerSocket serverSocket;
    MyKeyListener kND;


	public GameServerThread (ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public MyKeyListener getKeyListener () {
		return kND;
	}

	public void run()
    {
		clientSocket = null;
      try
      {
        System.out.println( "Warte auf Verbindung vom Client" );
			clientSocket = serverSocket.accept();
	          System.out.println( "Verbindung vom Client wird hergestellt" );
			serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
			serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String clientInput;
			kND = new MyKeyListener();
			while ((clientInput = serverIn.readLine()) != null) {
				System.out.println( "ServerOut: " + clientInput);
				serverOut.println(clientInput);
				String[] splitClientInput = clientInput.split(" ");
				System.out.println( "splitClientInput[0] " + splitClientInput[0]);
				if (kND != null) {
					System.out.println( "splitClientInput[0] " + splitClientInput[0]);
					if (splitClientInput[0].equals("KC")) {
						System.out.println( "splitClientInput[1] " + splitClientInput[1]);
						System.out.println( "splitClientInput[2] " + splitClientInput[2]);
						if (splitClientInput[1].equals("+")) {
							kND.keyPressed(Integer.parseInt(splitClientInput[2]));
						} else {
							kND.keyReleased(Integer.parseInt(splitClientInput[2]));
						}
					}
				}
			}
			System.out.println( "Hier hängt er!" );
      }
      catch ( IOException e )
      {
			System.out.println("Accept failed: 1337");
			System.exit(-1);
      }
    }
  
	public void finish () {
		serverOut.close();
		try {
			serverIn.close();
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			serverSocket.close();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//  public void run()
//	  {
//		clientSocket = null;
//	    try
//	    {
//	      System.out.println( "Gleich hängt er!" );
//			clientSocket = serverSocket.accept();
//			serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
//			serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			String clientInput;
//			while ((clientInput = serverIn.readLine()) != null) {
//				System.out.println( "ServerOut: " + clientInput);
//				serverOut.println(clientInput);
//				String[] splitClientInput = clientInput.split("|");
//				if (splitClientInput[0]=="KC") {
//					if (splitClientInput[1]=="+") {
//						if (kND != null) {
//							
//						}
//					} else {
//						
//					}
//				}
//			}
//			System.out.println( "Hier hängt er!" );
//	    }
//	    catch ( IOException e )
//	    {
//			System.out.println("Accept failed: 1337");
//			System.exit(-1);
//	    }
//	  }

}