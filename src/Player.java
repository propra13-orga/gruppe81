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
		System.out.println("Move at"+playerRect.x +":"+playerRect.y);
//		if (!checkForCollision()){
		playerRect.x+=(xDirection);
		playerRect.y+=(yDirection);
		System.out.println("Move to"+playerRect.x +":"+playerRect.y);
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
		
		/*
		if((xDirection!=0) || (yDirection!=0)){
			g.drawImage(playerImgGO, playerRect.x, playerRect.y, null);
		}else
			g.drawImage(playerImgStop, playerRect.x, playerRect.y, null);
	*/
	}
	
	
}
