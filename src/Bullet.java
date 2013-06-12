import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Bullet extends GameObject implements Entity {
		double x,y;
		Player p;
		private Image pic = new ImageIcon("bullet.gif").getImage();
	public Bullet(double x, double y, Player p){
		//if(p1.lastDirection==0 )
		this.x = x;
		this.y = y;
		this.p =p;
		setBounds((int)x,(int) y  ,16,16	);
		
		
	}
	
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		x+=1.2;
		setBounds((int)x,(int) y-10 ,16,16	);
		
		
	}

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
		pic = null;
		
	}

}