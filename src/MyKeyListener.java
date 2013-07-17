import java.awt.event.*;

/**
 * 
 * 
 * Keylistener fur das Spiel
 */

public class MyKeyListener implements KeyListener {
	
	boolean[] keys = new boolean[256];
	
	
	
	
	public boolean isKeyPressed(int keyCode) {
		if (this.keys[keyCode]) 
			return true;
		else
			return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true; 
	}
	public void keyPressed(int keyCode) {
		System.out.println("KeyCode pressed via LAN: "+keyCode);
		this.keys[keyCode] = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false; 
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
