import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import Object.EntityDestroyable;
import Object.EntityMapObject;
import Object.EntityMovable;


public class Element extends GameObject implements EntityMapObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7056820136332853615L;
	
	Controller c;
	DungeonCrawlerGame game;
	Player p;
	private double x,y;

	private boolean mapObject = true;

	private boolean hited;
	private Image pic;
	private boolean shop=false;
	private boolean story=false;
	private boolean armor=false;
	private boolean weapon=false;
	private boolean collectable=false;
	private double width, height; //Variablen
	LinkedList<EntityMapObject> eMO = new LinkedList<EntityMapObject>();
	public Element(double x, double y, DungeonCrawlerGame game, Image image,double width,double height){
		
		
		this.x=x;
		this.y=y;
		this.game = game;
		this.p = p;
		this.pic=image;
		this.width=width;
		this.height=height;
		hited = false;
		this.setBounds((int)x, (int)y, (int)width, (int)height);
	
	}

	
	public void setShop(boolean shop) {
		this.shop=shop;
		}
	public boolean isShop() {
		return shop;
	}
	
	public void setStory(boolean story) {
		this.story=story;
		}
	public boolean isStory() {
		return story;
	}
	
	public void setWeapon(boolean weapon) {
		this.weapon=weapon;
		}
	public boolean isWeapon() {
		return weapon;
	}
	
	public void setArmor(boolean armor) {
		this.armor=armor;
		}
	public boolean isArmor() {
		return armor;
	}
	
	public void setCollectable(boolean collectable) {
		this.collectable=collectable;
		}
	public boolean isCollectable() {
		return collectable;
	}
	
	
	@Override
	public void update() {
		this.setBounds((int)x, (int)y, (int)width, (int)height);
		}
		
	

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(this !=null)
			g.drawImage(pic ,(int) x,(int) y, null);
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


	@Override
	public boolean isHited() {
		// TODO Auto-generated method stub
		return hited;
	}


	@Override
	public void hit() {
		// TODO Auto-generated method stub
		boolean hited = true;
	}




	@Override
	public void setLeben(int leben) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public int getLeben() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public void setMana(int mana) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public int getMana() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public void setGeld(int geld) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public int getGeld() {
		// TODO Auto-generated method stub
		return 0;
	}


}
