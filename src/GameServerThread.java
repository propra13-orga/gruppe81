import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Klasse fuer die Kommunikation im Netzwerkmodus von Serverseite
 */
public class GameServerThread extends Thread implements Runnable {

	private MainWindow mainWindow;
	private Socket clientSocket;
	public PrintWriter serverOut;
//	public OutputStream serverOutStream;
//	public ObjectOutputStream serverObjectOut;
	public BufferedReader serverIn;
	private ServerSocket serverSocket;
    MyKeyListener kND;


	public GameServerThread (ServerSocket serverSocket) {
		kND = new MyKeyListener();
		this.serverSocket = serverSocket;
//		this.serverOut = serverOut;
//		this.serverOutStream = serverOutStream;
//		this.serverObjectOut = serverObjectOut;
	}
	
	public void setMainWindow (MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		if (this.mainWindow!=null) {
	        System.out.println("MainWindow im Thread gesetzt");
		}
	}
	
	public MyKeyListener getKeyListener () {
		return kND;
	}

	/**
	 * akzeptiert die Verbindung vom Client und verarbeitet die Daten des Clients
	 */
	public void run()
    {
		clientSocket = null;
      try
      {
        System.out.println( "Warte auf Verbindung vom Client" );
		while (mainWindow==null) {
			
		}
		if (mainWindow!=null) {
//	        System.out.println("Checke labelServer");
//			if (!mainWindow.labelServer.isVisible()) {
//		        System.out.println("Aktiviere labelServer");
//				mainWindow.labelServer.setVisible(true);
//				mainWindow.add(mainWindow.labelServer);
//				mainWindow.repaint();
//			}
		}
			clientSocket = serverSocket.accept();
			mainWindow.lobby.setText(mainWindow.lobby.getText()+"Spieler 2 betritt die Lobby..."+(char)KeyEvent.VK_ENTER);
			mainWindow.repaint();
	        System.out.println( "Verbindung vom Client wird hergestellt" );
			serverOut = new PrintWriter(clientSocket.getOutputStream(), true);
			serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			serverOutStream = clientSocket.getOutputStream();
//			serverObjectOut = new ObjectOutputStream(serverOutStream);  
			String clientInput;
			while ((clientInput = serverIn.readLine()) != null) {
				String[] splitClientInput = clientInput.split(" ");
				if (mainWindow.gw!=null) {
//					System.out.println( "ServerOut: " + clientInput);
//					serverOut.println(clientInput);
//					System.out.println( "splitClientInput[0] " + splitClientInput[0]);
					if (kND != null) {
//						System.out.println( "splitClientInput[0] " + splitClientInput[0]);
						if (splitClientInput[0].equals("KC")) {
//							System.out.println( "splitClientInput[1] " + splitClientInput[1]);
//							System.out.println( "splitClientInput[2] " + splitClientInput[2]);
							if (splitClientInput[1].equals("+")) {
								kND.keyPressed(Integer.parseInt(splitClientInput[2]));
							} else {
								kND.keyReleased(Integer.parseInt(splitClientInput[2]));
							}
						}
					}
				} else {
					if (splitClientInput[0].equals("<")) {
						mainWindow.chat.setText(mainWindow.chat.getText()+clientInput+(char)KeyEvent.VK_ENTER);
					}
					if (splitClientInput[0].equals("PLAYER")) {
						if (splitClientInput[1].equals("READY")) {
							mainWindow.startButton.setVisible(true);
						}
					}
				}
			}
//			System.out.println( "Hier hängt er!" );
      }
      catch ( IOException e )
      {
			System.out.println("Accept failed: 1337");
			System.exit(-1);
      }
    }
  
	public void finish () {
		if (serverOut!=null) {
			serverOut.close();
		}
		try {
			if (serverIn!=null) {
				serverIn.close();
			}
			if (clientSocket!=null) {
				clientSocket.close();
			}
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