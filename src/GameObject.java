
import java.awt.*;


abstract class GameObject extends Rectangle
	{
//=====================================_____Class____Start
    private boolean alive = true,  explode = false;
	private long coolOf;	
    private double x,y;
    private boolean solid;
    private Image Bild;
	public int lifepoints=1;
	public int lifepointsMax=1;
   
    private int live;
    private static Image imageBuffer;
    private static Graphics graphicsBuffer;
   
    
//    private int width=1000, height=600;                  
//
//
//    
    public GameObject (){	// Construktor  !!!!!!!
    this.alive = true; 
    this.x =x;
    this.y= y;
    this.Bild = Bild;
    this.solid = solid;
    
    }  

	public void changeLifepoints(int lifepointsChange, long coolOf){
		if (this.coolOf<System.nanoTime()) {
			this.lifepoints = this.lifepoints+lifepointsChange;
			System.out.println("Lifepoints "+this.lifepoints);
			this.coolOf = System.nanoTime()+coolOf;
			if (this.lifepoints<=0) {
				alive=false;
			}
		}
	}

	
//    
//    
//+++++++++Graphics
//  abstract void ladenImage (Object ... args);         // ABSTRACT  Methode fur Image aufzuladen  !!!!!!!
//    abstract void erase (Graphics g);                  // ABSTRACT  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//  abstract Image paint (Graphics g);                // ABSTRACT  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//    public Image pushImage (Image nextImage)     
//	{
//
//    imageBuffer = createImage(this.width, this.height);
//    graphicsBuffer = imageBuffer.getGraphics();
//    graphicsBuffer.drawImage(nextImage,getX(),getY(),this);
//    return imageBuffer;
//	}
//---------Graphics---Ende
//
//+++++++++Сoordinates
    public void setX (int x)    { this.x=x;  }
    public void setY (int y)    { this.y=y;  }

    public double getX ()          { return x;  }
    public double getY ()          { return y;  }
//---------Сoordinates--Ende
//
//
//
//+++++++++Lebenspunkte
    public void setLive (int live)         { this.live=live;     } 
    public int  getLive ()                 { return live;        }
    public void changeLive (int change)    { this.live += change;}
    abstract void explode (boolean explode);                                              // ABSTRACT   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//---------Lebenspunkte---Ende
//
//
//
    public boolean kill(GameObject ob){
    	ob.alive = false;
    	return alive;
    }
    
//
    void explode ()
    {
	explode = true;
    }
//
//
//
	public boolean isAlive() {
		return alive;
	}

    boolean alive ()
    {
	return alive;
    }
//====================================_____Class____Ende
}
