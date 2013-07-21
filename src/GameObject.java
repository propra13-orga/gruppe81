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
	public int elementArt; 
    private int live;
    private static Image imageBuffer;
    private static Graphics graphicsBuffer;
   
    
//    private int width=1000, height=600;                  
//
//
//    

  //Erzeugt Object vom Typ GameObjekt  
    public GameObject (){	// Construktor  !!!!!!!
    this.alive = true; 
    this.x =x;
    this.y= y;
    this.Bild = Bild;
    this.solid = solid;
//    this.elementArt=2;                         //2=Feuer
//    this.elementArt=1;						   //1=Eis,
    }  
//ver�ndert die Lebenspunkt um lifepointsChange, coolOf gibt an wie Lange das Objekt vor weiteren Veraenderungen geschuetzt ist
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

	public int getLifepoints(){
		return lifepoints;
	}

	public void setLifepoints(int lifepoints){
		this.lifepoints = lifepoints;
	}

	public int getElementArt(){
		
		return elementArt;
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
// setze X Koordinate der oberen linken Ecke des Objekts
	public void setX (int x)    { this.x=x;  }
// setze Y Koordinate der oberen linken Ecke des Objekts
    public void setY (int y)    { this.y=y;  }

 // gebe X Koordinate der oberen linken Ecke des Objekts aus
    public double getX ()          { return x;  }
// gebe Y Koordinate der oberen linken Ecke des Objekts aus
    public double getY ()          { return y;  }
//---------Сoordinates--Ende
//
//
//
//+++++++++Lebenspunkte
// setze Anzahl Leben
    public void setLive (int live)         { this.live=live;     } 
// lese Anzahl Leben
    public int  getLive ()                 { return live;        }
// veraendere die Anzahl der Leben des Objekts
    public void changeLive (int change)    { this.live += change;}
    abstract void explode (boolean explode);                                              // ABSTRACT   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//---------Lebenspunkte---Ende
//
//
//
	// setze alive auf falsch und gebe alive zurueck
    public boolean kill(GameObject ob){
    	ob.alive = false;
    	return alive;
    }
    
//
	// setze Explosion auf wahr
    void explode ()
    {
	explode = true;
    }
//
//
//
	// gebe aus ob das Objekt lebt
	public boolean isAlive() {
		return alive;
	}

	// gebe aus ob das Objekt lebt
    boolean alive ()
    {
	return alive;
    }
//====================================_____Class____Ende
}
