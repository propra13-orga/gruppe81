import java.awt.Graphics;


public interface Entity {
	
	public void update();
	public void draw(Graphics g);
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
}
