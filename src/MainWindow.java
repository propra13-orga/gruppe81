import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Classe MainWindow erstellt den Frame mit der Hauptmenue und MenueButtons  
 */
public class MainWindow extends JFrame{

	public JButton startButton;
	private JButton sButton, nButton, ncButton, ndButton, bereitButton, editorButton, backButton,startButtonTest;
	public JLabel label,labelServer,labelClient;
//	public JPanel panel;
	public DungeonCrawlerGame gw;
	public GameServer gameServer;
	public GameClient gameClient;
	public Editor editor;
	private FlowLayout myLayout = new FlowLayout(FlowLayout.CENTER);
	public static GFrame  editorFrame;
	public MyKeyListenerNetworkClient kNC;
	
	MyKeyListener kNt;
	public JEditorPane lobby,chat,chatInput,ip;

//	DungeonCrawlerGame gw =new DungeonCrawlerGame();
	
	
	/** 
	 * Constructor MainWindow erstellst das MenueFenster, mit dem Namen des Speils, 
	 * Groesse des Spielfelds, die nicht geaendert werden kann 
	 */
	
	public MainWindow(){        	 
	//	super("Test Window Titel"); // Rufe Vater Methode auf mit der String
		setLayout(myLayout);    	 
		setTitle("Dungeon Crawler pre Alpha "); 
		setSize(1000, 600);     	 
		setResizable(false);		
		setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE); 
		setLocationRelativeTo(null);  // 

//        panel = new JPanel();        
//        panel.setSize(800, 400);
//        add(panel);

//		add(gw);
		
//		gw.requestFocus(true);
//        gw = new DungeonCrawlerGame(this);
//        gw.setSize(1000, 600);
//        gw.setVisible(false);

        /*
         * Erstelle zwei Buttons: "Start" und "Beenden"
         */

//		kNt = new MyKeyListener();
//		kNt.setMainWindow(this);
//		addKeyListener(kNt);

		kNC = new MyKeyListenerNetworkClient();
		kNC.setMainWindow(this);
//		addKeyListener(kNC);

		startButton = new JButton("Start");// The JButton name.
        add(startButton);
        // getContentPane().add(startButton);
        startButton.setSize(400, 70);
        startButton.setLocation(300, 150);
        startButton.setVisible(true);
		startButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
            showDCGame();
		}});
		
		//===============Test level Button
		
		startButtonTest = new JButton("Start own lvl");// The JButton name.
        add(startButtonTest);
        // getContentPane().add(startButton);
        startButtonTest.setSize(400, 70);
        startButtonTest.setLocation(300, 290);
        startButtonTest.setVisible(true);
		startButtonTest.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
            showDCGameTest();
		}});
		
		// =========================End of Testlevel button

		backButton = new JButton("Zurück");// The JButton name.
        add(backButton);
        backButton.setSize(400, 70);
        backButton.setLocation(300, 150);
        backButton.setVisible(false);
        backButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			ip.setVisible(false);
			nButton.setVisible(true);
			startButton.setVisible(true);
			editorButton.setVisible(true);
			backButton.setVisible(false);
			ndButton.setVisible(false);
			ncButton.setVisible(false);
		}});

		ndButton = new JButton("Netzwerk Spiel starten");//The JButton name.
		//getContentPane().add(sButton);
		add(ndButton);
		ndButton.setSize(400,70);
		ndButton.setLocation(400,150);
		ndButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
	        startButton.setVisible(false);
			ip.setVisible(false);
	        ncButton.setVisible(false);
	        ndButton.setVisible(false);
			gameServer = new GameServer();
//	        System.out.println("setze MainWindow");
			if (kNC.mainWindow!=null) {
				gameServer.gameServerThread.setMainWindow(kNC.mainWindow);
				gameServer.gameServerThread.kND.setMainWindow(kNC.mainWindow);
//		        System.out.println("MainWindow gesetzt");
			}
			if (gameServer.gameServerThread.kND!=null) {
				kNC.mainWindow.addKeyListener(gameServer.gameServerThread.kND);
//		        System.out.println("kND gesetzt");
			}
//			kNC.mainWindow.labelServer.setVisible(true);
//			kNC.mainWindow.add(kNC.mainWindow.labelServer);
			kNC.mainWindow.lobby.setText(kNC.mainWindow.lobby.getText()+"Spieler 1 betritt die Lobby..."+(char)KeyEvent.VK_ENTER);
			kNC.mainWindow.lobby.setVisible(true);
			kNC.mainWindow.add(kNC.mainWindow.lobby);
			kNC.mainWindow.chat.setVisible(true);
			kNC.mainWindow.add(kNC.mainWindow.chat);
			kNC.mainWindow.chatInput.setVisible(true);
			kNC.mainWindow.add(kNC.mainWindow.chatInput);
			kNC.mainWindow.repaint();
			requestFocus();			
		}}
				);
		ndButton.setVisible(false);

        ip = new JEditorPane();
        ip.setFocusable(true);
        ip.setSize(70, 10);
        ip.setLocation(700, 100);
        ip.setText("127.0.0.1");        
        ip.setVisible(false);
		add(ip);
		
		ncButton = new JButton("Netzwerk Spiel beitreten");//The JButton name.
		//getContentPane().add(sButton);
		add(ncButton);
		ncButton.setSize(400,70);
		ncButton.setLocation(400,150);
		ncButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			ip.setVisible(false);
	        ncButton.setVisible(false);
	        ndButton.setVisible(false);
	        startButton.setVisible(false);
	        bereitButton.setVisible(true);
			gameClient = new GameClient(kNC.mainWindow,ip.getText());			
			kNC.setGameClient(gameClient);
			addKeyListener(kNC);
			kNC.mainWindow.lobby.setText(kNC.mainWindow.lobby.getText()+"Spieler 1 ist in der Lobby."+(char)KeyEvent.VK_ENTER);
			kNC.mainWindow.lobby.setText(kNC.mainWindow.lobby.getText()+"Spieler 2 betritt die Lobby..."+(char)KeyEvent.VK_ENTER);
			kNC.mainWindow.lobby.setVisible(true);
			kNC.mainWindow.add(kNC.mainWindow.lobby);
			kNC.mainWindow.chat.setVisible(true);
			kNC.mainWindow.add(kNC.mainWindow.chat);
			kNC.mainWindow.chatInput.setVisible(true);
			kNC.mainWindow.add(kNC.mainWindow.chatInput);
			kNC.mainWindow.repaint();
			requestFocus(true);
		}}
				);
		ncButton.setVisible(false);

		nButton = new JButton("Netzwerk");//The JButton name.
		//getContentPane().add(sButton);
		add(nButton);
		nButton.setSize(400,70);
		nButton.setLocation(400,150);
		nButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			nButton.setVisible(false);
			startButton.setVisible(false);
			editorButton.setVisible(false);
			ip.setVisible(true);
			backButton.setVisible(true);
			ndButton.setVisible(true);
			ncButton.setVisible(true);
		}}
				);
		nButton.setVisible(true);

        editorButton = new JButton("Editor");// The JButton name.
        add(editorButton);
        // getContentPane().add(startButton);
        editorButton.setSize(400, 70);
        editorButton.setLocation(300, 150);
        editorButton.setVisible(true);
        editorButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
		//	MyMouseListener myMouseListener = new MyMouseListener(); 
        //   editor = new Editor(kNC.mainWindow,myMouseListener);
			editorFrame = new GFrame();
		}});

		
		sButton = new JButton("Beenden");// The JButton name.
        // getContentPane().add(sButton);
        add(sButton);
        sButton.setSize(400, 70);
        sButton.setLocation(300, 250);
        sButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if (gameClient != null) {
            		gameClient.finish();            		
            	}
            	if (gameServer != null) {
            		gameServer.finish();            		
            	}
            	dispose();
            }
        });
        sButton.setVisible(true);

        bereitButton = new JButton("bereit");
        add(bereitButton);
        bereitButton.setSize(400, 70);
        bereitButton.setLocation(400, 250);
        bereitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if (kNC.mainWindow.gameClient!=null) {
            		kNC.mainWindow.gameClient.clientOut.println("PLAYER READY");
        	        bereitButton.setVisible(false);
            	}
            }
        });
        bereitButton.setVisible(false);
        label = new JLabel("Geschafft!!! Bist ein wahrer hIro :)");
        label.setSize(400, 70);
        label.setLocation(300, 50);
//        label.setVisible(false);
        labelServer = new JLabel("Server:)");
        labelServer.setSize(400, 70);
        labelServer.setLocation(100, 100);
        lobby = new JEditorPane();
        lobby.setFocusable(false);
        lobby.setSize(400, 70);
        lobby.setLocation(100, 100);
        chat = new JEditorPane();
        chat.setFocusable(false);
        chat.setSize(500, 300);
        chat.setLocation(700, 100);
        chatInput = new JEditorPane();
        chatInput.setFocusable(false);
        chatInput.setSize(400, 70);
        chatInput.setLocation(700, 400);
        labelClient = new JLabel("Client:)");
        labelClient.setSize(400, 70);
        labelClient.setLocation(700, 100);
	}

	
	/**
	 * Beende das Spiel und zeige das Menue an
	 */
    public void showDCMenue() {
        label.setVisible(false);
        gw.stopGame();
        remove(gw);
        add(startButton);
        add(sButton);
        add(nButton);
        add(ncButton);
        add(ndButton);
        add(label);
        add(backButton);
        add(startButtonTest);
        repaint();
    }	

  /**
   * Beende das Spiel und zeige wer gewonnen hat
   */
    public void showFinish() {
        gw.stopGame();
        remove(gw);
        add(startButton);
        add(sButton);
        add(nButton);
        add(ncButton);
        add(ndButton);
        add(editorButton);
        label.setVisible(true);
        add(label);
        add(backButton);
        add(startButtonTest);
        repaint();
    }	

   /** 
   * Starte das Spiel
   */
    public void showDCGame() {
        label.setVisible(false);
        remove(bereitButton);
        remove(sButton);
        remove(nButton);
        remove(ncButton);
        remove(ndButton);
        remove(startButton);
        remove(startButtonTest);
        remove(editorButton);
        remove(labelClient);
        remove(labelServer);
        remove(label);
        remove(lobby);
        remove(chat);
        remove(chatInput);
        remove(backButton);
        gw = new DungeonCrawlerGame(this);        
        gw.setSize(1000, 600);
//System.out.println("111111");
        add(gw);
//System.out.println("211111");
//        gw.newWorld(1);
        gw.startGame();    // Startet das Spiel
        gw.requestFocus(); // Tastatureingabe
        pack();
    }
    
    public void showDCGameTest() {
        label.setVisible(false);
        remove(bereitButton);
        remove(sButton);
        remove(nButton);
        remove(ncButton);
        remove(ndButton);
        remove(startButton);
        remove(startButtonTest);
        remove(editorButton);
        remove(labelClient);
        remove(labelServer);
        remove(label);
        remove(lobby);
        remove(chat);
        remove(chatInput);
        remove(backButton);
        gw = new DungeonCrawlerGame(this, true);        
        gw.setSize(1000, 600);
//System.out.println("111111");
        add(gw);
//System.out.println("211111");
//        gw.newWorld(1);
        gw.startGame();    // Startet das Spiel
        gw.requestFocus(); // Tastatureingabe
        pack();
    }
    
    /**
     * Startmethode
     * @params	
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new DCMenue(); 
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
		 
//		MyKeyListener keyListener = new MyKeyListener(); 
			
	
	}
	
//	public void paint(Graphics g) {
//		if (editor!=null) {
//			editor.paint();
//		}
//	}

}
