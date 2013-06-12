import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;



public class Player {

	private World world;
	public Rectangle playerRect;
	private Image playerImgGO, playerImgStop, playerImg;
	protected int xDirection, yDirection, lastDirection;
	
	// lastDirection : 0 = right, 1 = down, 2= left, 3 = up
	
	private boolean alive=true;	
	private int playerLife;	
	private long playerCoolOf;	
	private int playerLifepoints;	
	private int playerLifepointsMax;	
	private int playerManapoints;	
	private int playerManapointsMax;	
	private int checkpointRaum;
	private int checkpointX;
	private int checkpointY;
	private boolean colide, hitExit = false, hitTrap = false, hitFinish = false;
	
	public Player(World world){
		this.world = world;
	//	playerImgGO = new ImageIcon("player.gif").getImage();
	//	playerImgStop = new ImageIcon("player_stop.gif").getImage();
		playerImg = new ImageIcon("Boy_R1.png").getImage();
		playerRect = new Rectangle(1,25,world.BLOCKSIZE,50);
		playerLife = 3;
		playerLifepoints = 100;
		playerLifepointsMax = 100;
		playerManapoints = 0;
		playerManapointsMax = 100;
	}

	public boolean isAlive(){
		return alive;
	}

	public void setAlive(boolean alive){
		this.alive=alive;
	}

	public int getPlayerLifepoints(){
		return playerLifepoints;
	}

	public int getPlayerLifepointsMax(){
		return playerLifepointsMax;
	}

	public void changePlayerLifepoints(int playerLifepointsChange, long coolOf){
		if (playerCoolOf<System.nanoTime()) {
			this.playerLifepoints = this.playerLifepoints+playerLifepointsChange;
			playerCoolOf = System.nanoTime()+coolOf;
			if (this.playerLifepoints<=0) {
				changePlayerLife(-1);
			}
		}
	}

	public void changePlayerLife(int playerLifeChange){
		playerLife = this.playerLife+playerLifeChange;
		if (playerLife<=0) {
			alive=false;
		}
		else {
			playerRect = new Rectangle(1,25,world.BLOCKSIZE,50);
			playerLifepoints = 100;
		}
	}

	public void setCheckpoint(int checkpointRaum,int checkpointX,int checkpointY){
		this.checkpointRaum = checkpointRaum;
		this.checkpointX = checkpointX;
		this.checkpointY = checkpointY;
	}
	
	
	public  void setXDirection(int d){
//		if(!checkForCollision()){
		
		xDirection =d;
		yDirection =0;
//		}
	}
	
	public void setYDirection(int d){
//		if(!checkForCollision()){
		yDirection =d;
		xDirection =0;
//		}
	}

	public int getXDirection(){
		return xDirection;
	}

	public int getYDirection(){
		return yDirection;
	}

	public void update(){
		move();		
	}
	
	public void setHitExit(boolean hitExit){
		this.hitExit = hitExit;
	}
	
	public void setHitTrap(boolean hitTrap){
		this.hitTrap = hitTrap;
	}
	
	public void setHitFinish(boolean hitFinish){
		this.hitFinish = hitFinish;
	}

	public boolean isHitExit(){
		return hitExit;
	}
	
	public boolean isHitTrap(){
		return hitTrap;
	}
	
	public boolean isHitFinish(){
		return hitFinish;
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
//		System.out.println("Move to"+playerRect.x +":"+playerRect.y);
//		}else{
//			setYDirection(0);
//			setXDirection(0);
//		 } 
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
	
	public int getLastDirection(){
		return lastDirection;
	}
	
	public void draw(Graphics g){
		g.drawImage(playerImg, playerRect.x, playerRect.y, null);
//		g.setColor(Color.white);
//		g.fill3DRect(playerRect.x, playerRect.y-5, world.BLOCKSIZE-5, 4, true);
//		g.setColor(Color.green);
//		g.fill3DRect(playerRect.x, playerRect.y-5, (world.BLOCKSIZE-5)*(playerLifepoints/playerLifepointsMax), 4, true);
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
		/*
		if((xDirection!=0) || (yDirection!=0)){
			g.drawImage(playerImgGO, playerRect.x, playerRect.y, null);
		}else
			g.drawImage(playerImgStop, playerRect.x, playerRect.y, null);
	*/
	}
	
	
}
