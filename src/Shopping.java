import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;
import java.awt.Image.*;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * organisiert Einkaufen (Mana, Leben, Waffe)
 * */
class Shopping
{

private static boolean enter=false, up=false, down=false, left=false, right=false;
private boolean [] positionStuff;
private boolean [] aktivCommodity;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
//++++Index
private static JFrame MyFrame;
private static MyKeyListener MyKeyL; 
private int width = 1000, height = 600;   // !!! Nur kurze Zeit so, es wird geandert, vielleicht bei Anruf von Konstrukteur!!!! 
//++++Index__ENDE

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//++++Was_hat_Spieler:__Golt,_Waffe...
private int gold = 2;

//++++Was_hat_Spieler__:Golt,_Waffe...___ENDE

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//++++GRAPHICS______!!!!___img__bedeutet_IMAGE___!!!!________off__bedeutet_Element__von__doppelteBuffer___!!!!___
private Image imgBackground, imgPapyrus, imgMana, imgLife, imgWeapon, imgMunze;
private Image [] imgCommodity;
private World world;
private Player player;
private Image offImage;                                    //   copy Image fur buffer, unsehenbares Bild
private Graphics offGraphics;                              //   copy graphics fur buffer, wird nicht zum Monitor ubergegeben
//++++GRAPHICS__ENDE
//_________________Variable__ende_____________________

/**
 * Die Klassadresse Schop uebernimmt "publik" Eigenschaft
 * 
 * 
 * */
	public Shopping(World world,Player player)
	{
	//===========================___Konstrukteur___=================================
	
		MyKeyL = new MyKeyListener();
		positionStuff = new boolean[3];
		this.MyKeyL = MyKeyL;// speichert die Adresse
		this.world = world;// -*-
		this.player = player; //-*-
		gold = player.getPlayerMoney(); //  übernimmt den GeldAnzahl
		positionStuff[0] =true; // Reihenfolge der Waren
		positionStuff[1] =false;
		positionStuff[2] =false;
	
	//===========================___Konstrukteur___=================================ENDE
	}





	public MyKeyListener getMyKeyListener() { return MyKeyL;  }

	public void setFrame (JFrame MyFrame)  {  this.MyFrame = MyFrame ; }

	public boolean pay(int gold) { if( this.gold >= gold ) {this.gold-= gold; return true;} else {return false;} }
	//_____________________________________________________


/**
 * Uebernimmt den Namen der Bilder und laedt diese
 * */
	public void loadImage( String...args)  
	{
	int numerator; 
for (numerator=0; numerator < args.length; numerator++)
{
	

	if ("background".equals(args [numerator]))    
			{ numerator++;  imgBackground  = Toolkit.getDefaultToolkit().getImage(args[numerator]);  }
	if ("papyrus".equals(args [numerator]))       
			{ numerator++;  imgPapyrus  = Toolkit.getDefaultToolkit().getImage(args[numerator]);  } 
	if ("mana".equals(args [numerator]))       
			{ numerator++;  imgMana  = Toolkit.getDefaultToolkit().getImage(args[numerator]);  } 
	if ("life".equals(args [numerator]))       
			{ numerator++;  imgLife  = Toolkit.getDefaultToolkit().getImage(args[numerator]);  } 
	if ("weapon".equals(args [numerator]))       
			{ numerator++;  imgWeapon  = Toolkit.getDefaultToolkit().getImage(args[numerator]);  } 
	if ("munze".equals(args [numerator]))       
			{ numerator++;  imgMunze = Toolkit.getDefaultToolkit().getImage(args[numerator]);  } 

	///////////////////////////////////////////////////////////////////////////////////

			//if ("commodity".equals(args [numerator])) 
			//{
			//int i=(args.length-numerator)-1;imgCommodity=new Image[i];aktivCommodity=new boolean[i];
			//for(int j=0;j<i;j++){imgCommodity[j]=Toolkit.getDefaultToolkit().getImage(args[j]);}
			//} 

}


	}



/**
 * erzeugts das Endebild und gibt das aus(vergroessert)
 * */
	public Image paint()
	{
	//______________________________________________________paint
	keyControlling();
	offImage = MyFrame.createImage(width, height);
	offGraphics = offImage.getGraphics();
	offGraphics.drawImage( imgBackground,0,0,1000,800,MyFrame);
	offGraphics.drawImage( imgPapyrus,120,-20,700,650,MyFrame);

	if  (positionStuff[0] == true) { offGraphics.drawImage( imgMana,290,200-50, 150+50,150+50 ,MyFrame);  }
	else
		{offGraphics.drawImage( imgMana,290,200, 150,150 ,MyFrame);}

	if  (positionStuff[1] == true) { offGraphics.drawImage( imgLife,490,200-50, 150+50, 150+50, MyFrame);  }
	else
		{offGraphics.drawImage( imgLife,490,200, 150, 150, MyFrame);}

	if  (positionStuff[2] == true) { offGraphics.drawImage (imgWeapon,290,350,300+100,150+50,MyFrame);   }
	else
		{offGraphics.drawImage( imgWeapon,290,350,300,150,MyFrame);}

//____MU:NZE
	int j = 0;
	for ( int i=0; i < gold; i++, j+=30)  { offGraphics.drawImage (imgMunze,340+(j),540,MyFrame); }

	return offImage;// 
	//______________________________________________________paint___ENDE
	}


/**
 * Wechsel der Tasten  und kaufen
 * 
 * * */
	public void keyControlling() 
	{
	//____________________________________________________was_passiert,_wenn_man_Taste_dru:ckt
	if (enter == true) 
		{ enter = false;   
		if (positionStuff[0] == true) { if ( pay(1) ) {imgMana = null; player.changePlayerManapoints(30);} }
		if (positionStuff[1] == true) { if ( pay(1) ) {imgLife = null; player.changePlayerLifepoints(30,0);} }
		if (positionStuff[2] == true) { if ( pay(3) ) {imgWeapon = null; player.setWeapon(true);} }
		}


	if (up == true)	{ 
		enter=false; up=false; down=false; left=false; right=false;   
		if(positionStuff[0]==true) {positionStuff[0]=false; positionStuff[2]=true; return;}
		if(positionStuff[1]==true) {positionStuff[1]=false; positionStuff[0]=true; return;}
		if(positionStuff[2]==true) {positionStuff[2]=false; positionStuff[1]=true; return;}	
		}


	if (down == true)	{ 
		enter=false; up=false; down=false; left=false; right=false;   
		if(positionStuff[0]==true) {positionStuff[0]=false; positionStuff[1]=true; return;}
		if(positionStuff[1]==true) {positionStuff[1]=false; positionStuff[2]=true; return;}
		if(positionStuff[2]==true) {positionStuff[2]=false; positionStuff[0]=true; return;}	
		}
	//____________________________________________________was_passiert,_wenn_man_Taste_dru:ckt___ENDE!!!
	}


/**
 * KeyListener
 * */
class MyKeyListener extends KeyAdapter{
//>>>>>>>>>>>>>>>>>> !!  UntreClass  !! >>>>>>>>>>>>>>>>>>>


	public void keyPressed(KeyEvent event)
	{
	//************* ***************** 

	if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
		{ 
			player.changePlayerMoney(gold-player.getPlayerMoney());
			world.resume();
			
		}

	if (event.getKeyCode() == KeyEvent.VK_ENTER)    { enter = true;  up = false; down = false; left= false; right = false; }
	if (event.getKeyCode() == KeyEvent.VK_UP)       { enter = false; up = true;  down = false; left= false; right = false; }
	if (event.getKeyCode() == KeyEvent.VK_DOWN)     { enter = false; up = false; down = true;  left= false; right = false; }
	if (event.getKeyCode() == KeyEvent.VK_LEFT)     { enter = false; up = false; down = false; left= true;  right = false; }
	if (event.getKeyCode() == KeyEvent.VK_RIGHT)    { enter = false; up = false; down = false; left= false; right = true;  }

	//************** *****************
	}



}

}
