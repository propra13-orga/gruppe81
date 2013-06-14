import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import Object.EntityMovable;


public class Bullet extends GameObject implements EntityMovable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 7491567929155038278L;
		double x,y;
		Player p;
		DungeonCrawlerGame game;
		int direction;
		private boolean hited;
		private Image pic = new ImageIcon("bullet.gif").getImage();
		
	public Bullet(double x, double y, Player p,DungeonCrawlerGame game){
		//if(p1.lastDirection==0 )
		this.x = x;
		this.y = y;
		this.p =p;
		this.game = game;
		this.direction=p.lastDirection;
		hited = false;
		setBounds((int)x,(int) y  ,16,16	);
		
		
	}
	
	
	
	
	@Override
	public void update() {
		// TODO Bullets are flying  ;)
		switch(direction){
		case 0:	x+=1.2;
				setBounds((int)x,(int) y-10 ,16,16	);
				break;
		case 1:	y+=1.2;
				setBounds((int)x,(int) y-10 ,16,16	);
				break;
		case 2:	x-=1.2;
				setBounds((int)x,(int) y-10 ,16,16	);
				break;
		case 3:	y-=1.2;
				setBounds((int)x,(int) y-10 ,16,16	);
				break;		
		default:{}
		}
		if(Physics.CollisionWithDesroyable(this, game.ed)){
			hit();
		}
	//	log("Hited="+isHited());
	}
	
	/*
	private void checkforcollision() {
		// TODO checking collision Bullet/ Mob
		if(game.mob1!=null)
			if(this.intersects(game.mob1)){
				game.mob1 = null;
			}
		}

*/


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.drawImage(pic, (int)x, (int)y-10, null);
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
		this.x = x;
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		this.y=y;
	}

	@Override
	void explode(boolean explode) {
		// TODO Auto-generated method stub
		
		
	}
	
	public void hit(){
		hited = true;
	}
	
	public boolean isHited(){
		return hited;
	}
	
	private void log(String s){
		System.out.println(s);
	}
}
