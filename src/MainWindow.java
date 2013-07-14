import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *  Classe MainWindow erstellt den Frame mit der Hauptmenue und MenueButtons  
 */
public class MainWindow extends JFrame{

	private JButton startButton, sButton, nButton, ncButton, ndButton;
	private JLabel label;
	public DungeonCrawlerGame gw;
	public GameServer gameServer;
	public GameClient gameClient;
	private FlowLayout myLayout = new FlowLayout(FlowLayout.CENTER);
	
	public MyKeyListenerNetworkClient kNC;
	

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
//		add(gw);
		
//		gw.requestFocus(true);
//        gw = new DungeonCrawlerGame(this);
//        gw.setSize(1000, 600);
//        gw.setVisible(false);

        /*
         * Erstelle zwei Buttons: "Start" und "Beenden"
         */

		kNC = new MyKeyListenerNetworkClient();
		kNC.setMainWindow(this);
		addKeyListener(kNC);

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

		ndButton = new JButton("Netzwerk Spiel starten");//The JButton name.
		//getContentPane().add(sButton);
		add(ndButton);
		ndButton.setSize(400,70);
		ndButton.setLocation(400,150);
		ndButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			gameServer = new GameServer();
		}}
				);
		ndButton.setVisible(false);

		ncButton = new JButton("Netzwerk Spiel beitreten");//The JButton name.
		//getContentPane().add(sButton);
		add(ncButton);
		ncButton.setSize(400,70);
		ncButton.setLocation(400,150);
		ncButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
			gameClient = new GameClient(kNC.mainWindow);			
			kNC.setGameClient(gameClient);
//			kNC = new MyKeyListenerNetworkClient(gameClient); 
//			addKeyListener(kNC);
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
			ndButton.setVisible(true);
			ncButton.setVisible(true);
		}}
				);
		nButton.setVisible(true);

		
		
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
        label = new JLabel("Geschafft!!! Bist ein wahrer hIro :)");
        label.setSize(400, 70);
        label.setLocation(300, 50);
//        label.setVisible(false);
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
        label.setVisible(true);
        add(label);
        repaint();
    }	

   /** 
   * Starte das Spiel
   */
    public void showDCGame() {
        label.setVisible(false);
        remove(sButton);
        remove(nButton);
        remove(ncButton);
        remove(ndButton);
        remove(startButton);
        remove(label);
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

}
