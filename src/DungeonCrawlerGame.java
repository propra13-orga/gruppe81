import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
//import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
//import javax.swing.JLabel;
import javax.swing.JPanel;
//import Object.EntityDestroyable;

import Object.EntityDestroyable;
import Object.EntityMapObject;
import Object.EntityMovable;



public class DungeonCrawlerGame extends JPanel implements Runnable {

	/**
	Spiel Klasse
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
	private boolean showStory = false;
	private int currentLevel = 0;
	private int currentRoom = 1;
	double delta = 0; //Time var
	private long bulletCoolOf =0;
	private long spellCoolOf =0;
	//Game Objects
	World world;
	Player p1;
	Player p2;
	MyKeyListener k1;
	MyKeyListener k2;
	NPC mob1;
	Bullet b;
	Shopping shop;
	private Controller c;
	public MainWindow mainWindow;
	public LinkedList<EntityDestroyable> ed;
	public LinkedList<EntityMovable> em;
	public LinkedList<EntityMapObject> eMO;
	private Image blaseImg;
	
	// private static boolean hitExit;
	/**Constructor
	 * Erzeugt eine Instanz des Spiels
	 * @param mainWindow ist das Fenster in dem das Spiel laeuft
	 */
	public DungeonCrawlerGame(MainWindow mainWindow){
    	
		this.mainWindow = mainWindow;
		c = new Controller(this);
		
		world = new World(currentLevel + currentRoom,  this);
		System.out.println(world);
		p1 = new Player(world);
		p2 = new Player(world);
		p2.setStart(p1.getX()+25, p1.getY()+25);
		ed = c.getEntDestrList();
		em = c.getEntMovList();
		eMO = c.getEntMO();
		this.k1 = new MyKeyListener(); 
		if (mainWindow.gameClient==null) {
			if (mainWindow.gameServer!=null) {
				if (mainWindow.gameServer.getKeyListener()!=null) {
					System.out.println("getKeyListener");
					this.k2 = mainWindow.gameServer.getKeyListener(); 
				} else {
					this.k2 = new MyKeyListener(); 
				}
			} else {
				this.k2 = new MyKeyListener(); 
			}
//			addKeyListener(k2);
		} else {
			addKeyListener(mainWindow.kNC);
			this.k2 = new MyKeyListener(); 
		}
		addKeyListener(k1);
		// mob1 = new NPC( 250, 26, this, p1);
		b = new Bullet(p1.getX(), p1.getY(), p1,this);
		
		blaseImg = new ImageIcon("Sprechblase_mit_Text1.png").getImage();
//		System.out.println("Test 1"); 
//		if (mainWindow.gameServer!=null) {
//			System.out.println("Test 2"); 
//			if (mainWindow.gameServer.gameServerThread.serverOut!=null) {
//				System.out.println("Test 3"); 
//				try {
//					System.out.println("World schreiben"); 
//					mainWindow.gameServer.gameServerThread.serverObjectOut.writeObject(world);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
////				mainWindow.gameServer.serverOut.
////
////xxxxxxxxxxxxx
//
//			} else {
//			}
//		} else {
//		}
		
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
	//	hitExit =true;
		
		
	}
	/**
	 *  die Klasse erzeugt einen neuen Raum
	 * @param levelNumber die Nummer der Leveldatei, die geladen werden soll
	 
	 */
	public void newWorld(int levelNumber){
		c.ed.clear();
		c.em.clear();
		c.eWO.clear(); //loescht die Objekte aus den früheren Levels
		world = null;
		world = new World(levelNumber, this);
		p1.setWorld(world);
		p1.useCheckpoint(currentRoom);
		p2.setWorld(world);
		p2.useCheckpoint(currentRoom);
//		p1 = null;
//		p1 = new Player(world);
	}
	/**
	 * die Spielerschleife
	 */
	public void run(){
		
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 /200.0; //Make the divisor smaller to increase the SPEED
		long timer =System.currentTimeMillis(); //Current time for FPS
		// double delta = 0;
		int frames =0;
		int updates =0;
		
		while(running){ //We can do everything here : Main LOOP
			long now = System.nanoTime();
			
			delta+= (now-lastTime)/ns;
			lastTime = now;			
			if (delta >=1){
				if (!world.isPaused()) {
					gameUpdate();
					updates ++;
					delta--;
	/*				if (hitExit) {
              			newWorld(2);
              			hitExit=false;
					}
	*/
					if (p1.isHitFinish()) {
						mainWindow.showFinish();
					}
					if (!p1.isAlive()) {
						mainWindow.showDCMenue();
					}
				}
				gameRender();
				paintScreen();
//				System.out.println("P1 x:"+p1.playerRect.x+" y:"+p1.playerRect.y+" Level:"+currentLevel+" Raum:"+currentRoom);
			}
			frames++;
		//		System.out.println(System.currentTimeMillis() - timer );
			if(System.currentTimeMillis() - timer > 1000){
				timer +=1000;
		
//				System.out.println(updates + " ups, "+ frames + " fps");
			
				updates=0;
				frames=0;
			}
		}
	}
	/**
	 * sorgt für den Levelwechsel wenn der Spieler in den Ausgang laeuft
	 */
	private void changestate(){
		
		
		if (p1.isHitExit()) {
			showStory=false;
			currentRoom++;
			
			if(currentRoom >4){
			
				currentLevel=currentLevel+currentRoom-2;
				currentRoom = 1;
			}
			if(currentLevel>8)
				currentLevel = 0;
			if(currentRoom==4){
	            newWorld(0);
			}
			else {
	            newWorld(currentLevel+currentRoom);
			}
            p1.changestate();
		}
		if(p1.playerChangeRoom){
			if(p1.checkpointRoom < currentRoom){
				currentRoom=p1.checkpointRoom;
				newWorld(currentLevel+currentRoom);
				p1.changestate();
			}
			p1.playerChangeRoom=false;
		}
	}
	/**
	 * erzeugt Feuer 
	 * @param coolOf ist die Zeit in der der Getroffene nicht mehr verletzt wird nach dem Schuss
	 * @param p1 der Spieler, der den Schuss ausgeloest hat
	 */
	private void shoot(long coolOf,Player p1){
		if (bulletCoolOf<System.nanoTime()) {
			
			bulletCoolOf = System.nanoTime()+coolOf;
			if(c.em.size()< 500){
				c.addEntity(new Bullet(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1, this));
				bulletCoolOf = System.nanoTime()+coolOf;
			}
		}
	}
	/**
	 * erzeugt Zauber
	 * @param coolOf ist die Zeit in der der Getroffene nicht mehr verletzt wird nach dem Schuss
	 * @param p1 der Spieler, der den Schuss ausgeloest hat
	 */
	private void castSpell(long coolOf,Player p1){
		if (spellCoolOf<System.nanoTime()) {
			
			spellCoolOf = System.nanoTime()+coolOf;
			if(c.em.size()< 500){
				c.addEntity(new Spell(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1, this));
				spellCoolOf = System.nanoTime()+coolOf;
			}
		}
	}
/**
 * verarbeitet  Spielereingabe und bewegt Spieler, Gegner, Feuer und Mana.
 * 
 */
	private void gameUpdate(){
		if(running && game !=null){
			//Update state
			p1.setYDirection(0);
			p1.setXDirection(0);
			if(k1.isKeyPressed(KeyEvent.VK_UP)){
				p1.setYDirection(-1);
				p1.lastDirection =3;
			}
			else if(k1.isKeyPressed(KeyEvent.VK_DOWN)){
				p1.setYDirection(+1);
				p1.lastDirection =1;
			}else{			
				if(k1.isKeyPressed(KeyEvent.VK_LEFT)){
						p1.setXDirection(-1);
						p1.lastDirection =2;
				}else if(k1.isKeyPressed(KeyEvent.VK_RIGHT)){
						p1.setXDirection(+1);       
						p1.lastDirection =0;
				}
			}
			if ((p1.isHitShop()) && (k1.isKeyPressed(KeyEvent.VK_SPACE))) {
				world.pause();
				k1.keys[KeyEvent.VK_SPACE]=false;
				shop = new Shopping(world,p1);
				shop.setFrame(mainWindow);
				addKeyListener(shop.getMyKeyListener());
				shop.loadImage("background","shop (1).jpg","papyrus","Pap.png","mana","mana01.png","life","life01.png","weapon","ArmWaffe02.png","munze","Muenze6.png");
			}
			if ((p1.isHitStory()) && (k1.isKeyPressed(KeyEvent.VK_SPACE))) {
				k1.keys[KeyEvent.VK_SPACE]=false;
				showStory=true;
			}
			if ((showStory) && (k1.isKeyPressed(KeyEvent.VK_ESCAPE))) {
				p1.setHitStory(false);
				showStory=false;
			}
			if ((p1.hasWeapon()) && (k1.isKeyPressed(KeyEvent.VK_SPACE))){
				//c.addEntity(new Bullet(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1));
				shoot(1000000000,p1);
			}
			if ((p1.getPlayerManapoints()>=10) && (k1.isKeyPressed(KeyEvent.VK_Z))){
				//c.addEntity(new Bullet(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1));
				if (spellCoolOf<System.nanoTime()) {
					p1.changePlayerManapoints(-10);
					castSpell(250000000,p1);
				}
			}
			p2.setYDirection(0);
			p2.setXDirection(0);
			if(k2.isKeyPressed(KeyEvent.VK_UP)){
				p2.setYDirection(-1);
				p2.lastDirection =3;
			}
			else if(k2.isKeyPressed(KeyEvent.VK_DOWN)){
				p2.setYDirection(+1);
				p2.lastDirection =1;
			}else{			
				if(k2.isKeyPressed(KeyEvent.VK_LEFT)){
						p2.setXDirection(-1);
						p2.lastDirection =2;
				}else if(k2.isKeyPressed(KeyEvent.VK_RIGHT)){
						p2.setXDirection(+1);       
						p2.lastDirection =0;
				}
			}
			if ((p2.isHitShop()) && (k2.isKeyPressed(KeyEvent.VK_SPACE))) {
				world.pause();
				k2.keys[KeyEvent.VK_SPACE]=false;
				shop = new Shopping(world,p2);
				shop.setFrame(mainWindow);
				addKeyListener(shop.getMyKeyListener());
				shop.loadImage("background","shop (1).jpg","papyrus","Pap.png","mana","mana01.png","life","life01.png","weapon","ArmWaffe02.png","munze","Muenze6.png");
			}
			if ((p2.isHitStory()) && (k2.isKeyPressed(KeyEvent.VK_SPACE))) {
				k2.keys[KeyEvent.VK_SPACE]=false;
				showStory=true;
			}
			if ((showStory) && (k2.isKeyPressed(KeyEvent.VK_ESCAPE))) {
				p2.setHitStory(false);
				showStory=false;
			}
			if ((p2.hasWeapon()) && (k2.isKeyPressed(KeyEvent.VK_SPACE))){
				shoot(1000000000,p2);
			}
			if ((p2.getPlayerManapoints()>=10) && (k2.isKeyPressed(KeyEvent.VK_Z))){
				if (spellCoolOf<System.nanoTime()) {
					p2.changePlayerManapoints(-10);
					castSpell(250000000,p2);
				}
			}
		if (mainWindow.gameServer!=null) {
			System.out.println("PLAYER 1 "+(int)p1.getX()+" "+(int)p1.getY());
			System.out.println("PLAYER 2 "+(int)p2.getX()+" "+(int)p2.getY());
			mainWindow.gameServer.gameServerThread.serverOut.println("PLAYER 1 "+(int)p1.getX()+" "+(int)p1.getY()+" "+(int)p1.getXDirection()+" "+(int)p1.getYDirection());
			mainWindow.gameServer.gameServerThread.serverOut.println("PLAYER 2 "+(int)p2.getX()+" "+(int)p2.getY()+" "+(int)p2.getXDirection()+" "+(int)p2.getYDirection());
		}
		c.update(p1);	
		c.update(p2);	
		p1.update(); //Updating Player
		p2.update(); //Updating Player
		checkForCollision(p1);
		checkForCollision(p2);
//		if(mob1!=null)
//		mob1.update();
		
		
		
//		if (!checkForCollision())
//		{
//			p1.update(); //Updating Player
//		}
//			if(k1.isKeyPressed(KeyEvent.VK_UP)){
//				if (!checkForCollision())
//				{
//				p1.setYDirection(-1);				
//				}
//			}
//			else if(k1.isKeyPressed(KeyEvent.VK_DOWN)){
//				if (!checkForCollision())
//				{
//				p1.setYDirection(+1);
//				}
//			}else{			
//				p1.setYDirection(0);
//				if(k1.isKeyPressed(KeyEvent.VK_LEFT)){
//					if (!checkForCollision())
//					{
//						p1.setXDirection(-1);
//					}
//				}else if(k1.isKeyPressed(KeyEvent.VK_RIGHT)){
//					if (!checkForCollision())
//					{
//						p1.setXDirection(+1);
//					}
//				}else{			
//					p1.setXDirection(0);
//					}
//			}
//			p1.update(); //Updating Player
		//	requestFocus(true); //to be able to move the player
			
			changestate();
		}
	}
	/**
	 * bereite das Buffering vor und loesche den alten Inhalt
	 */
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
	
	
	
	/**
	 * Malt die Welt aller Elemente in doubleBuffer
	 * @param g
	 */
	public void draw (Graphics g){
		if (!world.isPaused()) {
			world.draw(g);
			c.draw(g);
			if (showStory) {
				g.drawImage(blaseImg, 100, 200, null);   		// Sprechblase
				
//				g.setColor(Color.yellow);
//				g.drawString("Es gab einmal einen jungen Schatzsucher.",100,195);
//				g.drawString("Eines Tages fand er eine magische Armschiene,",100,215);
//				g.drawString("mit einem wunderschönem rotem Rubin.",100,235); 
//				g.drawString("Als er sie anprobierte,",100,255);
//				g.drawString("hörte er eine Stimme, die ihn darum bietete, sich auf die Suche zu machen",100,265);
//				g.drawString("und das Gegenstück zu finden. ",100,295);
//				g.drawString("Begleite den Ali während des Abenteuers und helfe ihm",100,325);
//				g.drawString("das Gegenstück der Armschiene zu finden.",100,365);		
			}
			p1.draw(g); //Drawing Player
			p2.draw(g); //Drawing Player
		}
//		if(mob1!=null)
//		mob1.draw(g);
		if (world.isPaused()) {
			System.out.println("Zeige Shop");
			g.drawImage(shop.paint(),0,0,mainWindow.getWidth(),mainWindow.getHeight(),null);
			
		}
		
	}
/**
 * Ueberprueft und stellt fest ob der Spieler den Objeck trifft 
 * boolean true - trifft
 * boolean false - nicht
 * @param Player,ist der, der ueberprueft wird
 * @return
 */
	public boolean checkForCollision(Player p1){ //Checking for collision
		boolean colide = false;
		for(int i=0;i<world.AWIDTH;i++){
			for(int j=0;j<world.AHIGHT;j++){
				if(world.isSolid[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
			//		System.out.println("Collision DETECTED at"+p1.playerRect.x +":"+p1.playerRect.y);
					p1.playerRect.x-=p1.getXDirection();
					p1.playerRect.y-=p1.getYDirection();
			//		System.out.println("Collision DETECTED at"+p1.playerRect.x +":"+p1.playerRect.y);
					p1.setYDirection(0);
					p1.setXDirection(0);
					colide =true;
				
				}
				if(world.exits[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
	
									
					p1.setHitExit(true);
				}
				if(world.checkpoints[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
					
					
					p1.setCheckpoint(currentRoom,j*25,i*25);					

				}
				
//				if(world.checkpoint[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
//					p1.setCheckpoint(currentLevel,i,j);
//				}

				if(world.trap[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
					p1.changePlayerLifepoints(-12,250000000);
				}
				
				if(world.finish[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
					p1.setHitFinish(true);
				}

				if(p1.playerRect.x<0) //Prevents player to move back from start Point
					p1.playerRect.x=0;
				
			}
			
			}
		if(Physics.CollisionGameObjectList(p1, ed) ){
			p1.changePlayerLifepoints(-12,250000000);
			System.out.println("Collision DETECTED PLAYER/MOB");
			}
	//	if(Physics.CollisionGameObjectList(p1, eMO)){
	//		log("MAP OBJECT PLAYER COLLLISION");
	//		}
		
		return colide;	
	}
	
	//	playerRect.x-=1;
	//	playerRect.y-=1;
	/*	if (colide) {
		System.out.println("Collision DETECTED at"+playerRect.x +":"+playerRect.y);
		}
		else {
//		System.out.println(0);
		}
	*/	
		
		
	
	
	/**
	 * laedt Spelegraphik aus dem doubleBuffer und zeigt sie auf
	 */
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
	/**
	 * Fuegt den Gegner hinzu
	 * @param x Koordinate der linken oberen Ecke
	 * @param y Koordinate der linken oberen Ecke
	 */
	public void addNPC(double x, double y){
		c.addEntity(new NPC(x, y, this, p1));
	}
	/**
	 * Fuegt Leben, Mana und Geld hinzu
	 * @param x Koordinate der linken oberen Ecke
	 * @param y Koordinate der linken oberen Ecke
	 * @param leben zeigt wiviel Leben es giebt, wenn der Spieler HealthPack sammelt
	 * @param mana zeigt wiviel Mana es giebt, wenn der Spieler HealthPack sammelt
	 * @param geld zeigt wiviel Geld es giebt, wenn der Spieler Geld sammelt
	 */
	public void addHealthPack(double x, double y, int leben, int mana, int geld){
		c.addEntity(new HealthPack(x, y, this, p1,leben, mana, geld));
	}
	/**
	 * Fuegt Leben, Mana und Geld hinzu
	 * @param x Koordinate der linken oberen Ecke
	 * @param y Koordinate der linken oberen Ecke
	 * @param leben zeigt wiviel Leben es giebt, wenn der Spieler HealthPack sammelt
	 * @param mana zeigt wiviel Mana es giebt, wenn der Spieler HealthPack sammelt
	 * @param geld zeigt wiviel Geld es giebt, wenn der Spieler Geld sammelt
	 * @param special gibt eine zusaetzliche Eigenschaft, wie zB. Collectiable und Shop  
	 * @param wert gibt an ob die Eigenschaft, die in special steht wahr oder falsch ist
	 */
	public void addHealthPack(double x, double y, int leben, int mana, int geld, String special, boolean wert){
		c.addEntity(new HealthPack(x, y, this, p1,leben, mana, geld),special,wert);
	}
	/**
	 * wird ein beliebiges Element hinzugefuegt
	 * @param x Koordinate der linken oberen Ecke
	 * @param y Koordinate der linken oberen Ecke
	 * @param image das Bild, das angezeigt wird
	 * @param width
	 * @param height
	 */
	public void addElement(double x, double y,Image image, double width, double height){
		c.addEntity(new Element(x, y, this, image, width, height));
	}
	public void addElement(double x, double y,Image image, double width, double height, String special, boolean wert){
		c.addEntity(new Element(x, y, this, image, width, height),special,wert);
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
//		System.out.println(s);
	}

	
	public void addBOSS1(double x, double y){
		c.addEntity(new BOSS1(x, y, this, p1));
	}

	public void addBOSS3(double x, double y){
		c.addEntity(new BOSS3(x, y, this, p1));
	}

	public void addNPC2(double x, double y){
		c.addEntity(new NPC2(x, y, this, p1));
	}

	public void addNPC3(double x, double y){
		c.addEntity(new NPC3(x, y, this, p1));
	}

	public void addBOSS2(double x, double y){
		c.addEntity(new BOSS2(x, y, this, p1));
	}

}
