import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import Object.EntityDestroyable;





public class BOSS2 extends GameObject implements EntityDestroyable {
	private boolean alive=true;	
	private int BOSS2Life1;
	private int BOSS2Life;
	private long playerCoolOf;
	private boolean colide, hitExit = false, hitTrap = false, hitFinish = false;
	Random rnd = new Random ();
	
	String picPath;
	private Image pic = new ImageIcon("BossSpinne.gif").getImage();
	
	double x, y, width=50, height=50;
	boolean solid = true;
	double tempx;
	double tempy;
	private int direction =0;
	Player p;
	Random r;
	private boolean BOSS = true;
	DungeonCrawlerGame game;
	BOSS2(double x,  double  y, DungeonCrawlerGame game, Player p){
		r = new Random();
		tempx =x;
		tempy =y;
		this.x=x;
		this.y=y;
		this.game = game;
		this.p =p;
		this.lifepointsMax=50;
		this.lifepoints=this.lifepointsMax;
		setBounds((int)x, (int)y, (int)width, (int)height);

	}

	@Override
	public void update() {
		// TODO Update NPC state
		
		if(direction==0){
				if(x<tempx+100){
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
			
			
//		setBounds((int)x, (int)y, 25, 25);
//			
//			
//		setBounds((int)x, (int)y, 100, 100);
		setBounds((int)x, (int)y, (int)width, (int)height);
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
		g.setColor(Color.white);
		g.fill3DRect((int)x, (int)(y-5),(int) (this.width-5), 4, true);
		g.setColor(Color.green);
		g.fill3DRect((int)x, (int)(y-5),(int) ((this.width-5)*(float)lifepoints/(float)lifepointsMax), 4, true);
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
	public boolean isBOSS2(){
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
		
		
		
