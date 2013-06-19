import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;



public class Wall extends GameObject implements Entity {
	
	private double x,y;
	private Image Bild;
	
	public boolean solid;
	
	
	public Wall(double x, double y, boolean solid){
		this.x=x;
		this.y=y;
		Bild = new ImageIcon("Boden2525orangebrocken.png").getImage();
		Bild = new ImageIcon("garmschuppe.png").getImage();
		this.solid = solid;
		setBounds((int)x, (int)y, 25, 25);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		setBounds((int)x, (int)y, 25, 25);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(Bild!= null){
		g.drawImage(Bild,(int) x,(int) y, null);
		}else{
			System.err.println("NO PICTURE FOR WALL");
		}
			
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

	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub

	}

	@Override
	void explode(boolean explode) {
		// TODO Auto-generated method stub
		
	}

}
