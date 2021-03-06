import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


/**
 * 
 * Spielerklasse
 *
 */

public class Player extends GameObject implements Entity{

	private World world;
	public Rectangle playerRect;
	private Image playerImgGO, playerImgStop, playerImg;
	private Image playerImgR1, playerImgR2, playerImgR3, playerImgR4, playerImgR5,playerImgR6, playerImgL1, playerImgL2, playerImgL3,playerImgL4,playerImgL5,playerImgL6,armorImg,weaponImg;
	private Image playerImgU1,playerImgU2,playerImgU3,playerImgU4,playerImgU5,playerImgU6;
	private Image playerImgO1,playerImgO2,playerImgO3,playerImgO4,playerImgO5,playerImgO6;
	double xDirection, yDirection;
	protected int displayDirection;
	protected int lastDirection;
	protected int imageCounter=0;
	
	// displayDirection : 0 = right, 2= left
	// lastDirection : 0 = right, 1 = down, 2= left, 3 = up
	
	private boolean alive=true;	
	public boolean playerChangeRoom=false;
	public int playerMoney; // Das Geld des Spielers	
	public int playerLife;	// Das Leben des Spielers(der Spieler hat 3 Leben)
	private long playerCoolOf;	
	private int playerLifepoints;	
	private int playerLifepointsMax;	
	private int playerManapoints;	
	private int playerManapointsMax;	
	public int checkpointRoom=1;
	private int checkpointX=100;// Koordinate X von Ccheckpoints/ die Position auf dem Spielfeld, fue�r den Spieler
	private int checkpointY=100;// Koordinate Y -//-
	private boolean colide, hitExit = false, hitWaterhole = false, hitStory = false, hitTrap = false, hitFinish = false, hitShop = false, weapon = false, armor = false;
	
	public Player(World world){
		this.world = world;
	//	playerImgGO = new ImageIcon("player.gif").getImage();
	//	playerImgStop = new ImageIcon("player_stop.gif").getImage();
		playerImg = new ImageIcon("Boy_R1.png").getImage();
		playerImgR1 = new ImageIcon("Boy_R_mit_Armschiene1.png").getImage();
		playerImgR2 = new ImageIcon("Boy_R_mit_Armschiene2.png").getImage();
		playerImgR3 = new ImageIcon("Boy_R_mit_Armschiene3.png").getImage();
		playerImgR4 = new ImageIcon("Boy_R_mit_Armschiene4.png").getImage();
		playerImgR5 = new ImageIcon("Boy_R_mit_Armschiene5.png").getImage();
		playerImgR6 = new ImageIcon("Boy_R_mit_Armschiene6.png").getImage();
		
		
		
		playerImgU1 = new ImageIcon("Boy_U_1.png").getImage();
		playerImgU2 = new ImageIcon("Boy_U_2.png").getImage();
		playerImgU3 = new ImageIcon("Boy_U_3.png").getImage();
		playerImgU4 = new ImageIcon("Boy_U_4.png").getImage();
		playerImgU5 = new ImageIcon("Boy_U_5.png").getImage();
		playerImgU6 = new ImageIcon("Boy_U_6.png").getImage();
		
		
		playerImgO1 = new ImageIcon("Boy_O_1.png").getImage();
		playerImgO2 = new ImageIcon("Boy_O_2.png").getImage();
		playerImgO3 = new ImageIcon("Boy_O_3.png").getImage();
		playerImgO4 = new ImageIcon("Boy_O_4.png").getImage();
		playerImgO5 = new ImageIcon("Boy_O_5.png").getImage();
		playerImgO6 = new ImageIcon("Boy_O_6.png").getImage();
		
		playerImgL1 = new ImageIcon("Boy_L_mit_Armschiene1.png").getImage();
		playerImgL2 = new ImageIcon("Boy_L_mit_Armschiene2.png").getImage();
		playerImgL3 = new ImageIcon("Boy_L_mit_Armschiene3.png").getImage();
		playerImgL4 = new ImageIcon("Boy_L_mit_Armschiene4.png").getImage();
		playerImgL5 = new ImageIcon("Boy_L_mit_Armschiene5.png").getImage();
		playerImgL6 = new ImageIcon("Boy_L_mit_Armschiene6.png").getImage();
		armorImg  = new ImageIcon("Ruestung.png").getImage();
		weaponImg = new ImageIcon("Armschiene.png").getImage();
		
		
		playerRect = new Rectangle(world.startX,world.startY,world.BLOCKSIZE,50);
		playerLife = 3;
		playerLifepoints = 100;
		playerLifepointsMax = 100;
		playerManapoints = 0;
		playerManapointsMax = 100;
		setBounds(playerRect.getBounds());
		displayDirection = 0;
	}

	public void setWorld(World world){
		this.world = world;
		System.out.println("Setze Startpunkt x:" + world.startX + " y:" + world.startY);

		playerRect = new Rectangle(world.startX,world.startY,world.BLOCKSIZE,50);
	}

	public void setStart(double x, double y){
		playerRect = new Rectangle((int)x,(int)y,world.BLOCKSIZE,50);
	}

	public boolean isAlive(){
		return alive;
	}

	public void setAlive(boolean alive){
		this.alive=alive;
	}

	public int getPlayerMoney(){
		return playerMoney;
	}

	public void changePlayerMoney(int playerMoneyChange){
		this.playerMoney = this.playerMoney+playerMoneyChange;
	}

	public int getPlayerManapoints(){
		return playerManapoints;
	}

	public void changePlayerManapoints(int playerManaChange){
		this.playerManapoints = this.playerManapoints+playerManaChange;
	}

	public int getPlayerLifepoints(){
		return playerLifepoints;
	}

	public int getPlayerLifepointsMax(){
		return playerLifepointsMax;
	}

	public void changePlayerLifepoints(int playerLifepointsChange, long coolOf){
		if (playerCoolOf<System.nanoTime()) {
			if ((armor) && (playerLifepointsChange<0)) {
				playerLifepointsChange = (playerLifepointsChange/2);
			}
			this.playerLifepoints = this.playerLifepoints+playerLifepointsChange;
			playerCoolOf = System.nanoTime()+coolOf;
			if (this.playerLifepoints<=0) {
				changePlayerLife(-1);
			}
		}
	}
// Prueft auf welchem Stand ist das Leben vom Spieler
	
	public void changePlayerLife(int playerLifeChange){
		//Prueft ob das Spieler noch lebt in erstem Bloch der if-Abfrage, wenn nicht dann setze das Leben des Spielers auf "false", wenn es nicht der Fall ist geh in else und setze den Spieler auf Checkpoint
		playerLife = this.playerLife+playerLifeChange;//this. hier muss die globale Variable verwendet werden,naemlich
	     if (playerLife<=0) {
			alive=false;
		}
		else {
			playerRect = new Rectangle(checkpointX,checkpointY,25,50);
			playerLifepoints = 100;
			playerChangeRoom =true;//merkt sich, dass Der Spieler ein Leben verloren hat und es sein kann, dass er den Raum wechseln muss
		}
	}
//Hier setzen wir,die Werte fuer die Variablen checkpointRoom-X-Y 
	public void setCheckpoint(int checkpointRoom,int checkpointX,int checkpointY){
		this.checkpointRoom = checkpointRoom;
		this.checkpointX = checkpointX;
		this.checkpointY = checkpointY;
	}
	// Schaue ob der Spieler ein Leben verloren hat und ob der aktuelle Raum gleich dem checkpointRaum ist, wenn es so ist, dann setze den Spieler auf  die checkpoint-Koordinaten
	public void useCheckpoint(int room){
		if ((checkpointRoom==room) && (playerChangeRoom)) {
			playerRect = new Rectangle(checkpointX,checkpointY,world.BLOCKSIZE,50);
		}
	}

	public  void setWeapon(boolean weapon){
		this.weapon = weapon;
	}
	
	public boolean hasWeapon(){
		return weapon;
	}
	
	public  void setArmor(boolean armor){
		this.armor = armor;
	}
	
	public boolean hasArmor(){
		return armor;
	}
	
	public int getLastDirection(){
		return lastDirection;
	}
	
//	public void setLastDirection(int d){
//		lastDirection =d;
//	}

	public  void setXDirection(double d){
//		if(!checkForCollision()){
		
		xDirection =(int)d;
		yDirection =0;
//		}
	}
	
	public void setXDirection(int d){
		xDirection =d;
		yDirection =0;
	}
	
	public void setYDirection(double d){
//		if(!checkForCollision()){
		yDirection =(int) d;
		xDirection =0;
//		}
	}

	public void setYDirection(int d){
		yDirection =d;
		xDirection =0;
	}

	public double getXDirection(){
		return xDirection;
	}

	public double getYDirection(){
		return yDirection;
	}

	public double getX(){
		return playerRect.x;
	}

	public double getY(){
		return playerRect.y;
	}

	public void setX(double x){
		playerRect.x = (int)x;
	}
	
	public void setX(int x){
		playerRect.x = x;
	}

	public void setY(double y){
		playerRect.y = (int)y;
	}

	public void setY(int y){
		playerRect.y = y;
	}

	public void update(){
		move();	
		setBounds(playerRect.getBounds());
		
	//	if(Physics.CollisionGameObjectEntMO(this, eWO))
	}
	
	public void setHitFinish(boolean hitFinish){
		this.hitFinish = hitFinish;
	}

	public boolean isHitFinish(){
		return hitFinish;
	}

	public void setHitTrap(boolean hitTrap){
		this.hitTrap = hitTrap;
	}
	
	public boolean isHitTrap(){
		return hitTrap;
	}

	public void setHitExit(boolean hitExit){
		this.hitExit = hitExit;
	}
	
	public boolean isHitExit(){
		return hitExit;
	}
	
	public void setHitWaterhole(boolean hitWaterhole){
		this.hitWaterhole = hitWaterhole;
	}
	
	public boolean isHitWaterhole(){
		return hitWaterhole;
	}
	
	public void setHitShop(boolean hitShop){
		this.hitShop = hitShop;
	}
		
	public boolean isHitShop(){
		return hitShop;
	}
	
	public void setHitStory(boolean hitStory){
		this.hitStory = hitStory;
	}
		
	public boolean isHitStory(){
		return hitStory;
	}
	
	public void changestate(){
		if(hitExit){
			hitExit = false;
		}
		
		
		;
	}
	
	private void move(){
		if ((xDirection!=0) | (yDirection!=0))
		{
//		System.out.println("Move at"+playerRect.x +":"+playerRect.y);
//		if (!checkForCollision()){
		playerRect.x+=(xDirection);
		playerRect.y+=(yDirection);
		setBounds(playerRect.getBounds());
//		System.out.println("Move to"+playerRect.x +":"+playerRect.y);
//		}else{
//			setYDirection(0);
//			setXDirection(0);
//		 } 
			if(Physics.CollisionGameObjectArrayList(this, world.wallquest)){
	//			double xx= this.getXDirection();
	//			double yy= this.getYDirection();
				System.out.println("Move at"+playerRect.x +":"+playerRect.y+" xDirection"+xDirection+" yDirection"+yDirection);
				playerRect.x-=(xDirection);
				playerRect.y-=(yDirection);
				setBounds(playerRect.getBounds());
				
			}
		}
	}
	
//	public boolean checkForCollision(){ //Checking for collision
//		colide =false;
//		for(int i=0;i<world.AWIDTH;i++){
//			for(int j=0;j<world.AHIGHT;j++){
//				if(world.isSolid[i][j] && (playerRect.intersects(world.blocks[i][j]))){
//					playerRect.x-=xDirection;
//					playerRect.y-=yDirection;
//		//			setYDirection(0);
//		//			setXDirection(0);
//					colide =true;
//				
//				}
//				if(world.exits[i][j] && (playerRect.intersects(world.blocks[i][j]))){
//	//				playerRect.setLocation(0, 25);
//		//			world.levelNumber = 2;
//			//		world = null;
//				//	world = new World(2);
//									
//					hitExit=true;
//					
////				DungeonCrawlerGame.newWorld(2);
//				//	this.world= World();
//					
//					// NEUES LEVEL LADEN!!!!
//				//	world.getLevel("level"+world.levelNumber+".txt");
//				}
//				
//				if(world.trap[i][j] && (playerRect.intersects(world.blocks[i][j]))){
//					hitTrap=true;
//				}
//				
//				if(world.finish[i][j] && (playerRect.intersects(world.blocks[i][j]))){
//					hitFinish=true;
//				}
//
//				if(playerRect.x<0) //Prevents player to move back from start Point
//					playerRect.x=0;
//			}
//		
//		}
//	//	playerRect.x-=1;
//	//	playerRect.y-=1;
//	/*	if (colide) {
//		System.out.println("Collision DETECTED at"+playerRect.x +":"+playerRect.y);
//		}
//		else {
////		System.out.println(0);
//		}
//	*/	
//		return colide;
//		
//	}
	
	public void draw(Graphics g){
		
	}
	
	public void draw(Graphics g,boolean aktiv){
//		if ((lastDirection==2) & (displayDirection==0)) {
//			playerImg = playerImgL1;
//			displayDirection=2;
//			imageCounter=0;
//		}
//		else if ((lastDirection==0) & (displayDirection==2)) {
//			playerImg = playerImgR1;
//			displayDirection=0;
//			imageCounter=0;
//		}
		if ((xDirection!=0) | (yDirection!=0)) {imageCounter++;}
		int steps=15;
		if (imageCounter>6*steps) {
			imageCounter=1;
		}
		if (imageCounter>5*steps) {
			if (lastDirection==0) {
				playerImg = playerImgR6;
			}
			if (lastDirection==1) {
				playerImg = playerImgU6;
			}
			
			if (lastDirection==2) {
				playerImg = playerImgL6;
			}
			
			if (lastDirection==3) {
				playerImg = playerImgO6;
			}
			
		}
		else if (imageCounter>4*steps) {
			if (lastDirection==0) {
				playerImg = playerImgR5;
			}
			
			if (lastDirection==1) {
				playerImg = playerImgU5;
			
			}
			if (lastDirection==2) {
				playerImg = playerImgL5;
			}
			
			if (lastDirection==3) {
				playerImg = playerImgO5;
			}
			
		}
		else if (imageCounter>3*steps) {
			if (lastDirection==0) {
				playerImg = playerImgR4;
			}
			
			if (lastDirection==1) {
				playerImg = playerImgU4;
			}
			
			if (lastDirection==2) {
				playerImg = playerImgL4;
			}
			
			if (lastDirection==3) {
				playerImg = playerImgO4;
			}
			
		}
		else if (imageCounter>2*steps) {
			if (lastDirection==0) {
				playerImg = playerImgR3;
			
			}
				if (lastDirection==1) {
					playerImg = playerImgU3;
				
			}
			if (lastDirection==2) {
				playerImg = playerImgL3;
			}
			
			if (lastDirection==3) {
				playerImg = playerImgO3;
			}
			
		}
		else if (imageCounter>1*steps) {
			if (lastDirection==0) {
				playerImg = playerImgR2;
			}
				if (lastDirection==1) {
					playerImg = playerImgU2;
				
			}
			if (lastDirection==2) {
				playerImg = playerImgL2;
			}
		
			if (lastDirection==3) {
				playerImg = playerImgO2;
			}
			
		}
		else {
			if (lastDirection==0) {
				playerImg = playerImgR1;
			}
			
			if (lastDirection==1) {
				playerImg = playerImgU1;
			}
			
			if (lastDirection==2) {
				playerImg = playerImgL1;
			}
		
			if (lastDirection==3) {
				playerImg = playerImgO1;
			}
		
		}
		g.drawImage(playerImg, playerRect.x, playerRect.y, null);
//		g.setColor(Color.white);
//		g.fill3DRect(playerRect.x, playerRect.y-5, world.BLOCKSIZE-5, 4, true);
//		g.setColor(Color.green);
//		g.fill3DRect(playerRect.x, playerRect.y-5, (world.BLOCKSIZE-5)*(playerLifepoints/playerLifepointsMax), 4, true);
		if (aktiv) {
			g.setColor(Color.white);		
			g.fill3DRect(5, 5, 202, 12, true);
			g.setColor(Color.green);
			g.fill3DRect(6, 6, (int)(200*((float)playerLifepoints/(float)playerLifepointsMax)), 10, true);
			g.setColor(Color.black);
			g.drawString(playerLifepoints+"/"+playerLifepointsMax, 10, 15);
					
			g.setColor(Color.white);
			g.fill3DRect(209, 5, 202, 12, true);
			g.setColor(Color.blue);
			g.fill3DRect(210, 6, (int)(200*((float)playerManapoints/(float)playerManapointsMax)), 10, true);
			g.setColor(Color.black);
			g.drawString(playerManapoints+"/"+playerManapointsMax, 220, 15);
			g.setColor(Color.white);
			g.fill3DRect(419, 5, 9, 12, true);
			g.setColor(Color.red);
			g.fill3DRect(420, 6, 7, 10, true);
			g.setColor(Color.white);
			g.fill3DRect(429, 5, 9, 12, true);
			if (playerLife>1){
				g.setColor(Color.red);
				g.fill3DRect(430, 6, 7, 10, true);
			}
			g.setColor(Color.white);
			g.fill3DRect(439, 5, 9, 12, true);
			if (playerLife>2){
				g.setColor(Color.red);
				g.fill3DRect(440, 6, 7, 10, true);
			}
			g.setColor(Color.yellow);
			g.fill3DRect(454, 5, 50, 12, true);
			g.setColor(Color.black);
			g.drawString("�"+playerMoney, 460, 15);

			if (weapon) {
				g.drawImage(weaponImg, 520, 5, null);   	
				if (armor) {
					g.drawImage(armorImg, 550, 5, null);   		
				}
			} else if (armor) {
				g.drawImage(armorImg, 520, 5, null);   		
			}
		}
		
		
		/*
		if((xDirection!=0) || (yDirection!=0)){
			g.drawImage(playerImgGO, playerRect.x, playerRect.y, null);
		}else
			g.drawImage(playerImgStop, playerRect.x, playerRect.y, null);
	*/
	}

	@Override
	void explode(boolean explode) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void setX(double x) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setY(double y) {
//		// TODO Auto-generated method stub
//		
//	}
//	
	
}
