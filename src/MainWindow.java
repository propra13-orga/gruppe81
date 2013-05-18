
import javax.swing.JFrame;


public class MainWindow extends JFrame{

	/**
	 * @param args
	 * 
	 * 
	 * 
	 * 
	 */
	//Constructor
	DungeonCrawlerGame gw =new DungeonCrawlerGame();
	
	public MainWindow(){
	//	super("Test Window Titel"); // Rufe Vater Methode auf mit der String
		setTitle("Dungeon Crawler pre Alpha ");
		setSize(1000, 600);
		setResizable(false);
		setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//gw.requestFocus(true);
		add(gw);
		
		gw.requestFocus(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DCMenue(); 
		
		 
		//	MyKeyListener keyListener = new MyKeyListener(); 
			
	
	}

}
