import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;

/**
 * Klasse fuer die Tastatureingaben beim Client
 */
public class MyKeyListenerNetworkClient implements KeyListener {
	
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
		System.out.println("KeyCode: "+e.getKeyCode());
		if (gameClient != null) {
			gameClient.clientOut.println("KC + "+e.getKeyCode());
		}
		this.keys[e.getKeyCode()] = true; 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyCode Released: "+e.getKeyCode());
		if (gameClient != null) {
			gameClient.clientOut.println("KC - "+e.getKeyCode());
		}
		this.keys[e.getKeyCode()] = false; 
		if (this.mainWindow!=null) {
//			System.out.println("test"+e.getKeyChar()+e.getKeyChar());
			if (mainWindow.gw==null) {
//				System.out.println("test"+"test"+(char)e.getKeyChar()+(char)e.getKeyChar());
				if ((e.getKeyCode()!=KeyEvent.VK_SHIFT) && (e.getKeyCode()!=KeyEvent.VK_CONTROL) && (e.getKeyCode()!=KeyEvent.VK_CAPS_LOCK) && (e.getKeyCode()!=KeyEvent.VK_BACK_SPACE) && (e.getKeyCode()!=KeyEvent.VK_ESCAPE)) {
					mainWindow.chatInput.setText(mainWindow.chatInput.getText()+e.getKeyChar());
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {
						mainWindow.chat.setText(mainWindow.chat.getText()+"> "+mainWindow.chatInput.getText());
						if (mainWindow.gameClient!=null){
							if (mainWindow.gameClient.gameClientThread!=null){
								if (mainWindow.gameClient.gameClientThread.clientOut!=null){
									System.out.println("Msg To Server "+"< "+mainWindow.chatInput.getText());
									mainWindow.gameClient.gameClientThread.clientOut.println("< "+mainWindow.chatInput.getText());
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

	@Override
	public void keyTyped(KeyEvent e) {
	  // Tue nichts
	}
  
}