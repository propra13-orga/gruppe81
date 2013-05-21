import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class DungeonCrawlerGame extends JPanel implements Runnable {

	/**
	 * @author Dmytro Shlyakhov
	 */
	private static final long serialVersionUID = 1L;
	//Double buffering
	private Image dbImage;
	private Graphics dbg;
	//Jpanel variables
	public String windowTitle;
	static final int GWIDTH =1000, GHEIGHT =600;
	static final Dimension gameDim = new Dimension(GWIDTH, GHEIGHT);
	
	// Game Variales
	private Thread game;
	private volatile boolean running = false;
	private int currentLevel = 1;
	
	//Game Objects
	World world;
	Player p1;
	MyKeyListener k1;
	private MainWindow mainWindow;
	private static boolean hitExit;
	//Constructor
	public DungeonCrawlerGame(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		world = new World(currentLevel);
		p1 = new Player(world);
		this.k1 = new MyKeyListener(); 
		addKeyListener(k1);
		setPreferredSize(gameDim);
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus(true);
		//Handles users key inputs
	/*	addKeyListener(new KeyAdapter() {
			public void keyPressed (KeyEvent e){
				
				int c = e.getKeyCode();
			//	System.out.println(c);  //debugging string to see which number gets var c 
				if(c==KeyEvent.VK_LEFT){
					if (!p1.checkForCollision())
					{
					p1.setXDirection(-1);
					}
				}
				
				if(c== KeyEvent.VK_RIGHT){
					if (!p1.checkForCollision())
					{
					p1.setXDirection(1);
					}
				}
				if(c==KeyEvent.VK_UP){
					if (!p1.checkForCollision())
					{
					p1.setYDirection(-1);
					}
				}
				if(c==KeyEvent.VK_DOWN){
					if (!p1.checkForCollision())
					{
					p1.setYDirection(1);
					}
				}
				
			}
			public void keyReleased (KeyEvent e){
				p1.setYDirection(0);
				p1.setXDirection(0);
			}
			public void keyTyped (KeyEvent e){
		
			}
		});
		*/
	} 
	
	public void changelevel(){
		hitExit =true;
		
		
	}
	
	public void newWorld(int levelNumber){
		world = null;
		world = new World(levelNumber);
		p1 = null;
		p1 = new Player(world);
	}
	
	public void run(){
		
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 /200.0; //Make the divisor smaller to increase the SPEED
		long timer =System.currentTimeMillis(); //Current time for FPS
		double delta = 0;
		int frames =0;
		int updates =0;
		
		while(running){ //We can do everything here : Main LOOP
			long now = System.nanoTime();
			
			delta+= (now-lastTime)/ns;
			lastTime = now;
			while (delta >=1){
				gameUpdate();
				updates ++;
				delta--;
			}
	/*		if (hitExit) {
              newWorld(2);
              hitExit=false;
			}
	*/
			if (p1.isHitFinish()) {
				mainWindow.showFinish();
			}
			if (p1.isHitTrap()) {
				mainWindow.showDCMenue();
			}
			gameRender();
			paintScreen();
			frames++;
		//	System.out.println(System.currentTimeMillis() - timer );
			if(System.currentTimeMillis() - timer > 1000){
				timer +=1000;
			
		//		System.out.println(updates + " ups, "+ frames + " fps");
				
				updates=0;
				frames=0;
			}
				
		}
	}
	
	private void changestate(){
		
		
		if (p1.isHitExit()) {
			currentLevel++;
			if(currentLevel>3)
				currentLevel =1;
            newWorld(currentLevel);
            p1.changestate();
			}
		
	}
	;
	private void gameUpdate(){
		if(running && game !=null){
			//Update state
			if(k1.isKeyPressed(KeyEvent.VK_UP)){
				if (!p1.checkForCollision())
				{
				p1.setYDirection(-1);
				
				}
			}
			else if(k1.isKeyPressed(KeyEvent.VK_DOWN)){
				if (!p1.checkForCollision())
				{
				p1.setYDirection(+1);
				}
			}else{			
				p1.setYDirection(0);
				if(k1.isKeyPressed(KeyEvent.VK_LEFT)){
					if (!p1.checkForCollision())
					{
						p1.setXDirection(-1);
					}
				}else if(k1.isKeyPressed(KeyEvent.VK_RIGHT)){
					if (!p1.checkForCollision())
					{
						p1.setXDirection(+1);
					}
				}else{			
					p1.setXDirection(0);
					}
			}
			p1.update(); //Updating Player
		//	requestFocus(true); //to be able to move the player
			
			changestate();
		}
	}
	
	private void gameRender(){
		if(dbImage == null){ //Create the Buffer -Image
			dbImage = createImage(GWIDTH, GHEIGHT);
			if(dbImage == null){
				System.err.println("dbImage is still null");
				return;
			}else{
				dbg = dbImage.getGraphics();
			}
				
		}
		//Clear the screen
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0, 0, getWidth(), getHeight());
		//Draw game elements
		draw(dbg);
	}
	
	
	/*Draw all Game content in this method */
	
	public void draw (Graphics g){
		world.draw(g);
		p1.draw(g); //Drawing Player
		
	}
	
	private void paintScreen(){
		Graphics g;
		try{
			g =this.getGraphics();
			if(dbImage != null && g != null){
				g.drawImage(dbImage, 0, 0, null);
			}
		Toolkit.getDefaultToolkit().sync(); // For Linux
		}catch (Exception e){
			System.err.println(e);
		}
		
		
	}
	@Override
	public void addNotify(){
		super.addNotify();
		startGame();
	}
	
	//Start game method
	
	public synchronized void startGame(){  //Need to check performance if with synchronized is better than without?
		if(game==null  || ! running )
		{
			game = new Thread(this);
			game.start();
			running = true;
		}
	}
	
	public synchronized void stopGame(){   //Private or should I make it public, to call it from other class?
		if (running){
			running = false;
		}
	}
	private void log(String s){
		System.out.println(s);
	}
	
}
