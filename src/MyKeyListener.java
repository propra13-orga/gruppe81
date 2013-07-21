import java.awt.event.*;

/**
 * 
 * 
 * Keylistener fur das Spiel
 */

public class MyKeyListener implements KeyListener {
	
	boolean[] keys = new boolean[256];
	
	private GameClient gameClient;
	public MainWindow mainWindow;
	
	public void setGameClient(GameClient gameClient) {
		this.gameClient = gameClient;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	
	
	public boolean isKeyPressed(int keyCode) {
		if (this.keys[keyCode]) 
			return true;
		else
			return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true; 
		System.out.println(e.getKeyChar());
	}
	public void keyPressed(int keyCode) {
		System.out.println("KeyCode pressed via LAN: "+keyCode);
		this.keys[keyCode] = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false; 
		System.out.println(e.getKeyChar());
		if (mainWindow!=null) {
			if (mainWindow.gw==null) {
				mainWindow.labelServer.setText(mainWindow.labelServer.getText()+e.getKeyChar());
				mainWindow.repaint();
			}
		}
	}
	public void keyReleased(int keyCode) {
		System.out.println("KeyCode released via LAN: "+keyCode);
		this.keys[keyCode] = false; 
	}

	@Override
	public void keyTyped(KeyEvent e) {
	  // Tue nichts
	}
  
}
