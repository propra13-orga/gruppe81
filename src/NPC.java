
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;



import Object.EntityDestroyable;


public class NPC extends GameObject implements EntityDestroyable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2450052102355866638L;
	String picPath;
	private Image pic = new ImageIcon("Gegner1.png").getImage();
	
	
	double x, y;
	boolean solid = true;
	double tempx;
	private int direction =0;
	Player p;
	Random r;
	private boolean NPC = true;
	DungeonCrawlerGame game;
	NPC(double x,  double  y, DungeonCrawlerGame game, Player p){
		r = new Random();
		tempx =x;
		this.x=x;
		this.y=y;
		this.game = game;
		this.p =p;
		setBounds((int)x, (int)y, 25, 25);
		
	}
	
	@Override
	public void update() {
		// TODO Update NPC state
		
		if(direction==0){
				if(x<tempx+50){
				x+=r.nextDouble();
			//	log("(x++)X="+x);
				}else{
				//	log("X="+x);
					direction =1;
				}
		}else{ 
			if(x>tempx){
				x-=r.nextDouble();
			}else{
				direction =0;
			}
			
				
		}
			
			
		setBounds((int)x, (int)y, 25, 25);
	//	System.out.println(this.toString());
//		if(Physics.CollisionWithMovable(this, game.em)){
//			log("Collision IN NPC DETECTED");
//			
//			kill(this);
//		game.em.remove();
//			
//		}
		

	}

	@Override
	public void draw(Graphics g) {
		// TODO Draw the NPC on the screen
		if(this !=null)
		g.drawImage(pic ,(int) x,(int) y, null);
	}

	@Override
	public double getX() {
		// TODO Return x 
		
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}



	@Override
	void explode(boolean explode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setX(double x) {
		// TODO Set X
		this.x =x;
	}

	@Override
	public void setY(double y) {
		// TODO Set Y
		this.y= y;
		
	}
	public boolean isNPC(){
		return NPC;
	}
	private void log(String s){
		System.out.println(s);
	}

	@Override
	public boolean isMapObject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHited() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

}
