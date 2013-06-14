package Object;
import java.awt.Graphics;
import java.awt.Rectangle;


public interface EntityMovable {
	
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
	
}
