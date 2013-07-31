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
 * Klasse fuer die Kommunikation im Netzwerkmodus von Clientseite
 */
public class GameClientThread extends Thread implements Runnable {

	public PrintWriter clientOut;
	public BufferedReader clientIn;
	public MainWindow mainWindow;

	public GameClientThread (MainWindow mainWindow, BufferedReader clientIn, PrintWriter clientOut) {
		this.mainWindow = mainWindow;
		this.clientIn = clientIn;
		this.clientOut = clientOut;
	}
	
	/**
	 * verarbeite die Daten vom Server
	 */
	public void run()
    {
		try
		{
			String serverInput;
			while (1==1) {
				if ((serverInput = clientIn.readLine()) != null) {
					String[] splitServerInput = serverInput.split(" ");
					if (splitServerInput[0].equals("WORLD")) {
						if (mainWindow.gw!=null) {
							mainWindow.gw.noDrawing = true;
							if (splitServerInput[1].equals("NEW")) {
////								mainWindow.gw.p1.setHitExit(true);
////								mainWindow.gw.currentLevel = Integer.valueOf(splitServerInput[2]);
////								mainWindow.gw.currentRoom = Integer.valueOf(splitServerInput[3]);
//								mainWindow.gw.newWorld(Integer.valueOf(splitServerInput[2]));
//								mainWindow.gw.world.wallslist.clear();
//								mainWindow.gw.c.ed.clear();
//								mainWindow.gw.c.em.clear();
//								mainWindow.gw.c.eWO.clear(); //loescht die Objekte aus den früheren Levels
//								mainWindow.gw.world = null;
//								mainWindow.gw.world = new World(0, mainWindow.gw);
//								mainWindow.gw.p1.setWorld(mainWindow.gw.world);
//								mainWindow.gw.p2.setWorld(mainWindow.gw.world);								
							} else {
								if (mainWindow.gw.world!=null) {
									mainWindow.gw.world.getLevel(serverInput);
								}
							}
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
							if (Integer.valueOf(splitServerInput[5])==-1) {
								mainWindow.gw.p1.lastDirection =3;								
							}
							if (Integer.valueOf(splitServerInput[5])==1) {
								mainWindow.gw.p1.lastDirection =1;								
							}
							if (Integer.valueOf(splitServerInput[4])==-1) {
								mainWindow.gw.p1.lastDirection =2;								
							}
							if (Integer.valueOf(splitServerInput[4])==1) {
								mainWindow.gw.p1.lastDirection =0;								
							}
						}    		  
						if (splitServerInput[1].equals("2")) {
							mainWindow.gw.p2.setX(Integer.valueOf(splitServerInput[2]));
							mainWindow.gw.p2.setY(Integer.valueOf(splitServerInput[3]));
							mainWindow.gw.p2.setXDirection(Integer.valueOf(splitServerInput[4]));
							mainWindow.gw.p2.setYDirection(Integer.valueOf(splitServerInput[5]));
							if (Integer.valueOf(splitServerInput[5])==-1) {
								mainWindow.gw.p2.lastDirection =3;								
							}
							if (Integer.valueOf(splitServerInput[5])==1) {
								mainWindow.gw.p2.lastDirection =1;								
							}
							if (Integer.valueOf(splitServerInput[4])==-1) {
								mainWindow.gw.p2.lastDirection =2;								
							}
							if (Integer.valueOf(splitServerInput[4])==1) {
								mainWindow.gw.p2.lastDirection =0;								
							}
						}    		  
					}   
					if (splitServerInput[0].equals("SHOOT")) {
						if (Integer.valueOf(splitServerInput[1])==1) {
							mainWindow.gw.c.addEntity(new Bullet(Integer.valueOf(splitServerInput[2]), Integer.valueOf(splitServerInput[3]), mainWindow.gw.p1, mainWindow.gw));
						} else {
							mainWindow.gw.c.addEntity(new Bullet(Integer.valueOf(splitServerInput[2]), Integer.valueOf(splitServerInput[3]), mainWindow.gw.p2, mainWindow.gw));
						}
					}   
					if (splitServerInput[0].equals("CAST")) {
						if (Integer.valueOf(splitServerInput[1])==1) {
							mainWindow.gw.c.addEntity(new Spell(Integer.valueOf(splitServerInput[2]), Integer.valueOf(splitServerInput[3]), mainWindow.gw.p1, mainWindow.gw));
						} else {
							mainWindow.gw.c.addEntity(new Spell(Integer.valueOf(splitServerInput[2]), Integer.valueOf(splitServerInput[3]), mainWindow.gw.p2, mainWindow.gw));
						}
					}   
					if (splitServerInput[0].equals("CAST2")) {
						if (Integer.valueOf(splitServerInput[1])==1) {
							mainWindow.gw.c.addEntity(new Spell2(Integer.valueOf(splitServerInput[2]), Integer.valueOf(splitServerInput[3]), mainWindow.gw.p1, mainWindow.gw));
						} else {
							mainWindow.gw.c.addEntity(new Spell2(Integer.valueOf(splitServerInput[2]), Integer.valueOf(splitServerInput[3]), mainWindow.gw.p2, mainWindow.gw));
						}
					}   
					if (splitServerInput[0].equals("DESTROYABLE")) {
						if (splitServerInput[1].equals("REMOVE")) {							
							mainWindow.gw.c.removeEntity(mainWindow.gw.c.ed.get(Integer.valueOf(splitServerInput[2])));
						}
						if (splitServerInput[1].equals("LIFE")) {							
							System.out.println("Client: Input from Server: " + serverInput);
							mainWindow.gw.c.ed.get(Integer.valueOf(splitServerInput[2])).setLifepoints(Integer.valueOf(splitServerInput[3]));
							mainWindow.gw.c.ed.get(Integer.valueOf(splitServerInput[2])).setX(Integer.valueOf(splitServerInput[4]));
							mainWindow.gw.c.ed.get(Integer.valueOf(splitServerInput[2])).setY(Integer.valueOf(splitServerInput[5]));
						}
					}   
					if (splitServerInput[0].equals("MOVABLE")) {
						if (splitServerInput[1].equals("REMOVE")) {							
							mainWindow.gw.c.removeEntity(mainWindow.gw.c.em.get(Integer.valueOf(splitServerInput[2])));
						}
					}   
					if (splitServerInput[0].equals("WALLQUEST")) {
						if (splitServerInput[1].equals("REMOVE")) {							
							mainWindow.gw.world.wallquest.clear();
						}
					}   
					if (splitServerInput[0].equals("<")) {
						mainWindow.chat.setText(mainWindow.chat.getText()+serverInput+(char)KeyEvent.VK_ENTER);
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