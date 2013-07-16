import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class GameClientThread extends Thread implements Runnable {

	public PrintWriter clientOut;
	public BufferedReader clientIn;
	public MainWindow mainWindow;

	public GameClientThread (MainWindow mainWindow, BufferedReader clientIn, PrintWriter clientOut) {
		this.mainWindow = mainWindow;
		this.clientIn = clientIn;
		this.clientOut = clientOut;
	}
	
	public void run()
    {
		try
		{
			String serverInput;
			while (1==1) {
				if ((serverInput = clientIn.readLine()) != null) {
					String[] splitServerInput = serverInput.split(" ");
					System.out.println("Client: Input from Server: " + serverInput);
					if (splitServerInput[0].equals("WORLD")) {
						if (mainWindow.gw!=null) {
							mainWindow.gw.world.getLevel(serverInput);
						} else {
							mainWindow.showDCGame();
						}
					}    		  
					if (splitServerInput[0].equals("PLAYER")) {
						if (splitServerInput[1].equals("1")) {
							mainWindow.gw.p1.setX(Integer.valueOf(splitServerInput[2]));
							mainWindow.gw.p1.setY(Integer.valueOf(splitServerInput[3]));
							mainWindow.gw.p1.setXDirection(Integer.valueOf(splitServerInput[4]));
							mainWindow.gw.p1.setYDirection(Integer.valueOf(splitServerInput[5]));
						}    		  
						if (splitServerInput[1].equals("2")) {
							mainWindow.gw.p2.setX(Integer.valueOf(splitServerInput[2]));
							mainWindow.gw.p2.setY(Integer.valueOf(splitServerInput[3]));
							mainWindow.gw.p2.setXDirection(Integer.valueOf(splitServerInput[4]));
							mainWindow.gw.p2.setYDirection(Integer.valueOf(splitServerInput[5]));
						}    		  
					}    		  
				}    		  
			}    		  
		}
		catch ( IOException e )
		{
			System.out.println("Accept failed: 1337");
			System.exit(-1);
		}
	}

}