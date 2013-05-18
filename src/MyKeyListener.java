
import java.awt.event.*;

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
		//System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false; 
	}

	@Override
	public void keyTyped(KeyEvent e) {
	  // Tue nichts
	}
  
}
