import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class NPC extends GameObject implements Entity {
	String picPath;
	private Image pic = new ImageIcon("mario.png").getImage();
	private double x, y;
	boolean solid = true;
	NPC(double x, double y){
		
	this.picPath = picPath;
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void update() {
		// TODO 
		
		if(x<300){
			x+=1;
		}else{
				x=250;
			}
				
		
		

	}

	@Override
	public void draw(Graphics g) {
		// TODO Draw the NPC on the screen
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
	void ladenImage(Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void erase(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	Image paint(Graphics g) {
		// TODO Auto-generated method stub
		return null;
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

}
