import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;

public class MyKeyListenerNetworkClient implements KeyListener {
	
	boolean[] keys = new boolean[256];
	private GameClient gameClient;
	
//	public MyKeyListenerNetworkClient(GameClient gameClient) {
//		this.gameClient = gameClient;
//	}
	
	public void setGameClient(GameClient gameClient) {
		this.gameClient = gameClient;
	}

	public boolean isKeyPressed(int keyCode) {
		if (this.keys[keyCode]) 
			return true;
		else
			return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KeyCode: "+e.getKeyCode());
		if (gameClient != null)
//		gameClient.clientOut.println("KC|+|"+e.getKeyCode());
		this.keys[e.getKeyCode()] = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyCode Released: "+e.getKeyCode());
//		gameClient.clientOut.println("KC|-|"+e.getKeyCode());
		this.keys[e.getKeyCode()] = false; 
	}

	@Override
	public void keyTyped(KeyEvent e) {
	  // Tue nichts
	}
  
}