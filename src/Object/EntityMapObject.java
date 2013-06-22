package Object;

import java.awt.Graphics;
import java.awt.Rectangle;


public interface EntityMapObject {
	
	public void update();
	public void draw(Graphics g);
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public void setBounds(int x, int y, int width,int height);
	public Rectangle getBounds();
	public boolean isHited(); 
	public void hit();

	public void setLeben(int leben);
	public int getLeben();
	public void setMana(int mana);
	public int getMana();
	public void setGeld(int geld);
	public int getGeld();	
	public void setShop(boolean shop);
	public boolean isShop();
}
