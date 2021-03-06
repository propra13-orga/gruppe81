import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
//import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
//import javax.swing.JLabel;
import javax.swing.JPanel;

import files.game.createFile;
//import Object.EntityDestroyable;

import Object.EntityDestroyable;
import Object.EntityMapObject;
import Object.EntityMovable;



public class DungeonCrawlerGame extends JPanel implements Runnable {

	/**
	 Hauptspiel Klasse
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
	public boolean noDrawing = false;
	public boolean waterholeDone = false;
	public int currentLevel = 0;
	public int currentRoom = 1;
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
	public Controller c;
	public MainWindow mainWindow;
	public LinkedList<EntityDestroyable> ed;
	public LinkedList<EntityMovable> em;
	public LinkedList<EntityMapObject> eMO;
	private Image blaseImg;
	private createFile save;
	
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
		
		if (mainWindow.gameServer!=null || mainWindow.gameClient!=null) {
			p2 = new Player(world);
			p2.setStart(p1.getX()+25, p1.getY()+25);
		}
		ed = c.getEntDestrList();
		em = c.getEntMovList();
		eMO = c.getEntMO();
		this.k1 = new MyKeyListener(); 
		if (mainWindow.gameClient==null) {
			addKeyListener(k1);
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
		// mob1 = new NPC( 250, 26, this, p1);
		b = new Bullet(p1.getX(), p1.getY(), p1,this);
		
		blaseImg = new ImageIcon("Sprechblase_mit_Text1.png").getImage();

		save = new createFile();
		setPreferredSize(gameDim);
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus(true);
		

	} 
	
	public DungeonCrawlerGame(MainWindow mainWindow, boolean test){
    	
		this.mainWindow = mainWindow;
		c = new Controller(this);
		if(test){
			world = new World(999,  this);
		}else{
		world = new World(currentLevel + currentRoom,  this);
		}
		System.out.println(world);
		p1 = new Player(world);
		
		if (mainWindow.gameServer!=null || mainWindow.gameClient!=null) {
			p2 = new Player(world);
			p2.setStart(p1.getX()+25, p1.getY()+25);
		}
		ed = c.getEntDestrList();
		em = c.getEntMovList();
		eMO = c.getEntMO();
		this.k1 = new MyKeyListener(); 
		if (mainWindow.gameClient==null) {
			addKeyListener(k1);
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
		// mob1 = new NPC( 250, 26, this, p1);
		b = new Bullet(p1.getX(), p1.getY(), p1,this);
		
		blaseImg = new ImageIcon("Sprechblase_mit_Text1.png").getImage();

		save = new createFile();
		setPreferredSize(gameDim);
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus(true);
		

	} 
	
	public void changelevel(){
	//	hitExit =true;
		
		
	}
	/**
	 *  die Klasse erzeugt einen neuen Raum
	 * @param levelNumber die Nummer der Leveldatei, die geladen werden soll
	 
	 */
	public void newWorld(int levelNumber){
		System.out.println("New World: " + levelNumber);
		world.wallquest.clear();
		world.wallslist.clear();
		c.ed.clear();
		c.em.clear();
		c.eWO.clear(); //loescht die Objekte aus den fr�heren Levels
		world = null;
		world = new World(levelNumber, this);
		p1.setWorld(world);
		p1.useCheckpoint(currentRoom);
		if (p2!=null) {
			p2.setWorld(world);
			p2.useCheckpoint(currentRoom);
		}
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
				delta--;
				if (!world.isPaused()) {
					gameUpdate();
					updates ++;
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
				if (delta <1){
					gameRender();
					paintScreen();
					frames++;
				}
//				System.out.println("P1 x:"+p1.playerRect.x+" y:"+p1.playerRect.y+" Level:"+currentLevel+" Raum:"+currentRoom);
			}
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
	 * sorgt f�r den Levelwechsel wenn der Spieler in den Ausgang laeuft
	 */
	private void changestate(){
		
		
		if (((mainWindow.gameServer!=null || mainWindow.gameClient!=null) && p1.isHitExit() && p2.isHitExit()) || (mainWindow.gameServer==null && mainWindow.gameClient==null && p1.isHitExit())) {
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
    		if(p2!=null){
    			p2.changestate();
    		}
		}
		if(p1.playerChangeRoom){
	// Prueft ob aktueller Raum der Raum in dem checkpoint ist ist, wenn nicht setzt den aktuellen Raum gleich checkpointRaum
			if(p1.checkpointRoom < currentRoom){
				currentRoom=p1.checkpointRoom;
				newWorld(currentLevel+currentRoom);
				p1.changestate();
			}
			p1.playerChangeRoom=false;
		}
		if (((mainWindow.gameServer!=null || mainWindow.gameClient!=null) && p1.isHitWaterhole() && p2.isHitWaterhole() && !waterholeDone)) {
			addHealthPack(50, 50, 0, 20, 0,"collectable",true);
			addHealthPack(50, 50+150+150, 0, 20, 0,"collectable",true);			
			for (int ii=1;ii<=10;ii++){
				addHealthPack(50+ii*15-ii*5, 50+ii*15+ii*5, 0, 20, 0,"collectable",true);
				addHealthPack(50+ii*15+ii*5, 50+ii*15-ii*5, 0, 20, 0,"collectable",true);
				addHealthPack(50+150+150-ii*15-ii*5, 50+ii*15-ii*5, 0, 20, 0,"collectable",true);
				addHealthPack(50+150+150-ii*15+ii*5, 50+ii*15+ii*5, 0, 20, 0,"collectable",true);
			}
			for (int ii=1;ii<10;ii++){
				addHealthPack(50+150+ii*15-(10-ii)*5, 50+150+ii*15+(10-ii)*5, 0, 20, 0,"collectable",true);
				addHealthPack(50+150+ii*15+(10-ii)*5, 50+150+ii*15-(10-ii)*5, 0, 20, 0,"collectable",true);
				addHealthPack(50+ii*15-ii*5, 50+150+150-ii*15-ii*5, 0, 20, 0,"collectable",true);
				addHealthPack(50+ii*15+ii*5, 50+150+150-ii*15+ii*5, 0, 20, 0,"collectable",true);
			}
			addHealthPack(50+150+150, 50, 0, 20, 0,"collectable",true);			
			addHealthPack(50+150+150, 50+150+150, 0, 20, 0,"collectable",true);			
			waterholeDone=true;
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
				if (mainWindow.gameServer!=null) {
					if (this.p1==p1) {
						mainWindow.gameServer.gameServerThread.serverOut.println("SHOOT 1 "+(int)p1.playerRect.getCenterX()+" "+(int)p1.playerRect.getCenterY());
					} else {
						mainWindow.gameServer.gameServerThread.serverOut.println("SHOOT 2 "+(int)p1.playerRect.getCenterX()+" "+(int)p1.playerRect.getCenterY());
					}
				}
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
				if (mainWindow.gameServer!=null) {
					if (this.p1==p1) {
						mainWindow.gameServer.gameServerThread.serverOut.println("CAST 1 "+(int)p1.playerRect.getCenterX()+" "+(int)p1.playerRect.getCenterY());
					} else {
						mainWindow.gameServer.gameServerThread.serverOut.println("CAST 2 "+(int)p1.playerRect.getCenterX()+" "+(int)p1.playerRect.getCenterY());
					}
				}
				c.addEntity(new Spell(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1, this));
				spellCoolOf = System.nanoTime()+coolOf;
			}
		}
	}
	private void castSpell2(long coolOf,Player p1){
		if (spellCoolOf<System.nanoTime()) {
			
			spellCoolOf = System.nanoTime()+coolOf;
			if(c.em.size()< 500){
				if (mainWindow.gameServer!=null) {
					if (this.p1==p1) {
						mainWindow.gameServer.gameServerThread.serverOut.println("CAST2 1 "+(int)p1.playerRect.getCenterX()+" "+(int)p1.playerRect.getCenterY());
					} else {
						mainWindow.gameServer.gameServerThread.serverOut.println("CAST2 2 "+(int)p1.playerRect.getCenterX()+" "+(int)p1.playerRect.getCenterY());
					}
				}
				c.addEntity(new Spell2(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1, this));
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
			if(k1.isKeyPressed(KeyEvent.VK_S)){
				save.openFile();
				save.addRecords(p1.getX(), p1.getY(), p1.getLive(),p1.getLifepoints(), p1.playerMoney, p1.hasArmor(), p1.hasWeapon(), currentLevel,currentRoom);
				save.closeFile();
			}
			if(k1.isKeyPressed(KeyEvent.VK_L)){
				loadSavedGame();
			}
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
			if (mainWindow.gameClient==null) {
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
			}
				
			if ((p1.hasWeapon()) && (k1.isKeyPressed(KeyEvent.VK_SPACE))){
				//c.addEntity(new Bullet(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1));
				shoot(1000000000,p1);
			}
			
			
			if ((p1.getPlayerManapoints()>=10) && (k1.isKeyPressed(KeyEvent.VK_U))){
				//c.addEntity(new Bullet(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1));
				if (spellCoolOf<System.nanoTime()) {
					p1.changePlayerManapoints(-10);
					castSpell2(250000000,p1);
				}
			}
			
			
			
			
			if ((p1.getPlayerManapoints()>=10) && (k1.isKeyPressed(KeyEvent.VK_Z))){
				//c.addEntity(new Bullet(p1.playerRect.getCenterX(), p1.playerRect.getCenterY(), p1));
				if (spellCoolOf<System.nanoTime()) {
					p1.changePlayerManapoints(-10);
					castSpell(250000000,p1);
				}
			}
			if (p2!=null) {
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
				if (mainWindow.gameClient!=null) {
					if ((p2.isHitShop()) && (mainWindow.kNC.isKeyPressed(KeyEvent.VK_SPACE))) {
						world.pause();
						k2.keys[KeyEvent.VK_SPACE]=false;
						shop = new Shopping(world,p2);
						shop.setFrame(mainWindow);
						addKeyListener(shop.getMyKeyListener());
						shop.loadImage("background","shop (1).jpg","papyrus","Pap.png","mana","mana01.png","life","life01.png","weapon","ArmWaffe02.png","munze","Muenze6.png");
					}
					if ((p2.isHitStory()) && (mainWindow.kNC.isKeyPressed(KeyEvent.VK_SPACE))) {
						k2.keys[KeyEvent.VK_SPACE]=false;
						showStory=true;
					}
					if ((showStory) && (mainWindow.kNC.isKeyPressed(KeyEvent.VK_ESCAPE))) {
						p2.setHitStory(false);
						showStory=false;
					}
				} else {
					if ((p2.isHitShop()) && (k2.isKeyPressed(KeyEvent.VK_SPACE))) {
						k2.keys[KeyEvent.VK_SPACE]=false;
					}
					if ((p2.isHitStory()) && (k2.isKeyPressed(KeyEvent.VK_SPACE))) {
						k2.keys[KeyEvent.VK_SPACE]=false;
					}
				}
				if ((p2.hasWeapon()) && (k2.isKeyPressed(KeyEvent.VK_SPACE))){
					shoot(1000000000,p2);
				}
				
				if ((p2.getPlayerManapoints()>=10) && (k2.isKeyPressed(KeyEvent.VK_U))){
					if (spellCoolOf<System.nanoTime()) {
						p2.changePlayerManapoints(-10);
						castSpell2(250000000,p2);
					}
				}
				
				if ((p2.getPlayerManapoints()>=10) && (k2.isKeyPressed(KeyEvent.VK_Z))){
					if (spellCoolOf<System.nanoTime()) {
						p2.changePlayerManapoints(-10);
						castSpell(250000000,p2);
					}
				}
			}
		if (mainWindow.gameServer!=null) {
//			System.out.println("PLAYER 1 "+(int)p1.getX()+" "+(int)p1.getY());
//			System.out.println("PLAYER 2 "+(int)p2.getX()+" "+(int)p2.getY());
			mainWindow.gameServer.gameServerThread.serverOut.println("PLAYER 1 "+(int)p1.getX()+" "+(int)p1.getY()+" "+(int)p1.getXDirection()+" "+(int)p1.getYDirection());
			mainWindow.gameServer.gameServerThread.serverOut.println("PLAYER 2 "+(int)p2.getX()+" "+(int)p2.getY()+" "+(int)p2.getXDirection()+" "+(int)p2.getYDirection());
		}
		c.update(p1);	
		if (p2!=null) {
			c.update(p2);	
		}	
		p1.update(); //Updating Player
		if (p2!=null) {
			p2.update(); //Updating Player
		}
		checkForCollision(p1);
		if (p2!=null) {
			checkForCollision(p2);
		}
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
		if (!world.isPaused() && !noDrawing) {
			world.draw(g);
			c.draw(g);
			if (showStory) {
				g.drawImage(blaseImg, 100, 200, null);   		// Sprechblase
				
//				g.setColor(Color.yellow);
//				g.drawString("Es gab einmal einen jungen Schatzsucher.",100,195);
//				g.drawString("Eines Tages fand er eine magische Armschiene,",100,215);
//				g.drawString("mit einem wundersch�nem rotem Rubin.",100,235); 
//				g.drawString("Als er sie anprobierte,",100,255);
//				g.drawString("h�rte er eine Stimme, die ihn darum bietete, sich auf die Suche zu machen",100,265);
//				g.drawString("und das Gegenst�ck zu finden. ",100,295);
//				g.drawString("Begleite den Ali w�hrend des Abenteuers und helfe ihm",100,325);
//				g.drawString("das Gegenst�ck der Armschiene zu finden.",100,365);		
			}
			if (mainWindow.gameClient!=null) {
				if (p2!=null) {
					p2.draw(g,true);
				}
				p1.draw(g,false); //Drawing Player
			} else {
				if (p2!=null) {
					p2.draw(g,false);
				}
				p1.draw(g,true); //Drawing Player				
			}
			 //Drawing Player
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
	public boolean checkForCollision(Player p1){ //Checking for collision hier wurde ueberprueft ob der Spieler etwas trifft
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
				if(world.waterhole[i][j] && (p1.playerRect.intersects(world.blocks[i][j]))){
					p1.setHitWaterhole(true);
				}
				// Hier wird geprueft ob der Spieler ueber checkpoint gelaufen ist
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
	
	/**
	 * Start game method
	 */
	
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

	public void loadSavedGame(){
		BufferedReader input = null;
		String line = null;
		File file = new File("save.txt");
		try {
			input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			line = input.readLine();
			String[] splitLine = line.split(":");
			newWorld(Integer.valueOf(splitLine[7])+Integer.valueOf(splitLine[8]));
			p1.setX(Integer.valueOf(splitLine[0]));
			p1.setY(Integer.valueOf(splitLine[1]));
			p1.setLive(Integer.valueOf(splitLine[2]));
			p1.setLifepoints(Integer.valueOf(splitLine[3]));
			p1.playerMoney=Integer.valueOf(splitLine[4]);
			if(Integer.valueOf(splitLine[5])==1){
				p1.setArmor(true);
			}else{
				p1.setArmor(false);
			}
			if(Integer.valueOf(splitLine[6])==1){
				p1.setWeapon(true);
			}else{
				p1.setWeapon(false);
			}
		currentLevel=Integer.valueOf(splitLine[7]);
		currentRoom=Integer.valueOf(splitLine[8]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//liesst die Zeile aus der Datei und schreibt eine Variable rein
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
