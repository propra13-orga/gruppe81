
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;


abstract class GameObject	
	{
//=====================================_____Class____Start
    private boolean alive = false,  explode = false;
    private int x,y;
    private int live;
    private static Image imageBuffer;
    private static Graphics graphicsBuffer;
//    private int width=1000, height=600;                  
//
//
//    
    public GameObject ()       // Construktor  !!!!!!!
    {
    this.alive = true;  
    }  

//    
//    
//+++++++++Graphics
    abstract void ladenImage (Object ... args);         // ABSTRACT  Methode fur Image aufzuladen  !!!!!!!
    abstract void erase (Graphics g);                  // ABSTRACT  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    abstract Image paint (Graphics g);                // ABSTRACT  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

    public int getX ()          { return x;  }
    public int getY ()          { return y;  }
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
//
    void explode ()
    {
	explode = true;
    }
//
//
//
    boolean alive ()
    {
	return alive;
    }
//====================================_____Class____Ende
}
