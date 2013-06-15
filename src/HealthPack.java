import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import Object.EntityDestroyable;
import Object.EntityMapObject;
import Object.EntityMovable;


public class HealthPack extends GameObject implements EntityMapObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7056820136332853615L;
	
	Controller c;
	DungeonCrawlerGame game;
	Player p;
	private double x,y;
	private Image pic = new ImageIcon("Health Pack-32x32.png").getImage();
	private boolean mapObject = true;
	private boolean hited;
	LinkedList<EntityMapObject> eMO = new LinkedList<EntityMapObject>();
	public HealthPack(double x, double y, DungeonCrawlerGame game, Player p ){
		
		
		this.x=x;
		this.y=y;
		this.game = game;
		this.c = c;
		this.p = p;
		//this.p = p;
		hited = false;
		this.setBounds((int)x, (int)y, 25, 25);
		log("HealthPack X ="+(int)x+" Y ="+(int)y);
		
	}
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.setBounds((int)x, (int)y, 25, 25);
	//log(this.toString());
////			if( this.intersects(game.p1.playerRect) ){
////				log("COLLSION HEALTHPACK!!!!!!!");
//			}
//			
//				
//					
//	}
//			
		}
		
	

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(this !=null)
			g.drawImage(pic ,(int) x,(int) y, null);
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}

	



	@Override
	void explode(boolean explode) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isHited() {
		// TODO Auto-generated method stub
		return hited;
	}


	@Override
	public void hit() {
		// TODO Auto-generated method stub
		boolean hited = true;
	}
	private void log(String s){
		System.out.println(s);
	}


//	@Override
//	public boolean isNPC() {
//		// TODO Auto-generated method stub
//		return false;
//	}


//	@Override
//	public boolean isMapObject() {
//		// TODO Auto-generated method stub
//		return true;
//	}
}
