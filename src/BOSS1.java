import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import Object.EntityDestroyable;





public class BOSS1 extends GameObject implements EntityDestroyable {
	private boolean alive=true;	
	private int BOSS1Life1;
	private int BOSS1Life;
	private long playerCoolOf;
	private boolean colide, hitExit = false, hitTrap = false, hitFinish = false;
	Random rnd = new Random ();
	
	String picPath;
	private Image pic = new ImageIcon("bossmummy.png").getImage();
	
	double x, y;
	boolean solid = true;
	double tempx;
	double tempy;
	private int direction =0;
	Player p;
	Random r;
	private boolean BOSS = true;
	DungeonCrawlerGame game;
	private int BOSS1Lifepoints;
	private int BOSS1LifepointsMax;
	BOSS1(double x,  double  y, DungeonCrawlerGame game, Player p){
		r = new Random();
		tempx =x;
		tempy =y;
		this.x=x;
		this.y=y;
		this.game = game;
		this.p =p;
		setBounds((int)x, (int)y, 100, 100);

	}

	@Override
	public void update() {
		// TODO Update NPC state
		
		if(direction==0){
				if(x<tempx+200){
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
			
			
		setBounds((int)x, (int)y, 100, 100);
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
	public boolean isBOSS1(){
		return BOSS;
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



	@Override
	public boolean isNPC() {
		// TODO Auto-generated method stub
		return false;
	}
}
		
		
		
		