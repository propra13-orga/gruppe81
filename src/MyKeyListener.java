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
		if (this.mainWindow!=null) {
	        System.out.println("MainWindow im KeyListener gesetzt");
		}
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
//		System.out.println(e.getKeyChar());
	}
	public void keyPressed(int keyCode) {
//		System.out.println("KeyCode pressed via LAN: "+keyCode);
		this.keys[keyCode] = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false; 
//		System.out.println(e.getKeyChar());
		if (this.mainWindow!=null) {
//			System.out.println("test"+e.getKeyChar()+e.getKeyChar());
			if (mainWindow.gw==null) {
//				System.out.println("test"+"test"+(char)e.getKeyChar()+(char)e.getKeyChar());
				if ((e.getKeyCode()!=KeyEvent.VK_SHIFT) && (e.getKeyCode()!=KeyEvent.VK_CONTROL) && (e.getKeyCode()!=KeyEvent.VK_CAPS_LOCK) && (e.getKeyCode()!=KeyEvent.VK_BACK_SPACE) && (e.getKeyCode()!=KeyEvent.VK_ESCAPE)) {
					mainWindow.chatInput.setText(mainWindow.chatInput.getText()+e.getKeyChar());
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {
						mainWindow.chat.setText(mainWindow.chat.getText()+"> "+mainWindow.chatInput.getText());
						if (mainWindow.gameServer!=null){
							if (mainWindow.gameServer.gameServerThread!=null){
								if (mainWindow.gameServer.gameServerThread.serverOut!=null){
									mainWindow.gameServer.gameServerThread.serverOut.println("< "+mainWindow.chatInput.getText());
								}
							}
						}
						mainWindow.chatInput.setText("");
					}
					mainWindow.repaint();
				}
			}
		}
	}
	public void keyReleased(int keyCode) {
//		System.out.println("KeyCode released via LAN: "+keyCode);
		this.keys[keyCode] = false; 
	}

	@Override
	public void keyTyped(KeyEvent e) {
	  // Tue nichts
	}
  
}
