package Object;

import java.awt.Graphics;
import java.awt.Rectangle;


public interface EntityDestroyable {
	
	
	
	public void update();
	public void draw(Graphics g);
	public void changeLifepoints(int lifepointsChange, long coolOf);
	public boolean isAlive();
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public void setBounds(int x, int y, int width,int height);
	public Rectangle getBounds();
	public boolean isNPC();
	public boolean isMapObject();
	boolean isHited();
	void hit();
	public int getElementArt();
	
}
