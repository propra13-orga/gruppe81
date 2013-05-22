import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainWindow extends JFrame{

	private JButton startButton, sButton;
	private JLabel label;
	private DungeonCrawlerGame gw;
	private FlowLayout myLayout = new FlowLayout(FlowLayout.CENTER);
	/**
	 * @param args
	 * 
	 * 
	 * 
	 * 
	 */
	//Constructor
//	DungeonCrawlerGame gw =new DungeonCrawlerGame();
	
	public MainWindow(){
	//	super("Test Window Titel"); // Rufe Vater Methode auf mit der String
		setLayout(myLayout);
		setTitle("Dungeon Crawler pre Alpha ");
		setSize(1000, 600);
		setResizable(false);
		setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//gw.requestFocus(true);
//		add(gw);
		
//		gw.requestFocus(true);
//        gw = new DungeonCrawlerGame(this);
//        gw.setSize(1000, 600);
//        gw.setVisible(false);

        /*
         * JButton.
         */

        startButton = new JButton("Start");// The JButton name.
        add(startButton);
        // getContentPane().add(startButton);
        startButton.setLabel("Start");
        startButton.setSize(400, 70);
        startButton.setLocation(300, 150);
        startButton.setVisible(true);
		startButton.addActionListener(new java.awt.event.ActionListener()
		{public void actionPerformed(ActionEvent arg0) {
            showDCGame();
		}});

        sButton = new JButton("Beenden");// The JButton name.
        // getContentPane().add(sButton);
        add(sButton);
        sButton.setLabel("Beenden");
        sButton.setSize(400, 70);
        sButton.setLocation(300, 250);
        sButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        sButton.setVisible(true);
        label = new JLabel("Geschafft!!! Bist ein wahrer hIro :)");
        label.setSize(400, 70);
        label.setLocation(300, 50);
//        label.setVisible(false);
	}

    public void showDCMenue() {
        label.setVisible(false);
        gw.stopGame();
        remove(gw);
        add(startButton);
        add(sButton);
        add(label);
        repaint();
    }	

    public void showFinish() {
        gw.stopGame();
        remove(gw);
        add(startButton);
        add(sButton);
        label.setVisible(true);
        add(label);
        repaint();
    }	

    public void showDCGame() {
        label.setVisible(false);
        remove(sButton);
        remove(startButton);
        remove(label);
        gw = new DungeonCrawlerGame(this);
        gw.setSize(1000, 600);
//System.out.println("111111");
        add(gw);
//System.out.println("211111");
//        gw.newWorld(1);
        gw.startGame();
        gw.requestFocus();
        pack();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new DCMenue(); 
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
		 
//		MyKeyListener keyListener = new MyKeyListener(); 
			
	
	}

}
