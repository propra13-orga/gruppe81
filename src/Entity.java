
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Interface Entity is used as a blueprint for a class which implements it
 * */
public interface Entity { 
	
	public void update();
	public void draw(Graphics g);
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public void setBounds(int x, int y, int width,int height);
	public Rectangle getBounds();
	
	
}
