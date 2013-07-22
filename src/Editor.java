import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;
import java.awt.Image.*;
import java.awt.Toolkit;
import java.io.*;

class Editor 
{

//_________________Variable___________________________
FileWriter fileOut;
 
//++++Index
private static Frame MainFrame;
private static MyMouseListener MouseListener; 
private static ListElement paintListener;
private static ListElement editorListElement;
private int width = 1000, height = 600;    
//++++Index__ENDE

private static int paintX = 100, paintY=100;
private static boolean paintL = false, paintR = true;
private static char aktivSign = '0';

//++++GRAPHICS______!!!!___img__bedeutet_IMAGE___!!!!________off__bedeutet_Element__von__doppelteBuffer___!!!!___
private Image imgAktivMouse, imgPaint;
private Image img$, img0, img2, img3, img7, imgQ, imgB;
private static final char sign$ = '$', sign0 = '0', sign2 = '2',sign3 = '3', sign7 = '7', signQ = 'Q', signB = 'B'; 
private Image offImage;                                    //   copy Image fur buffer, unsehenbares Bild
private Graphics offGraphics;                              //   copy graphics fur buffer, wird nicht zum Monitor ubergegeben
//++++GRAPHICS__ENDE
//_________________Variable__ende_____________________

	public Editor(Frame MainFrame, MyMouseListener MouseListener)
	{
	//===========================___Konstrukteur___=================================
	this.MouseListener = MouseListener;
	this.MainFrame = MainFrame;
	editorListElement = new ListElement();
	paintListener = new ListElement();
	imgPaint  = Toolkit.getDefaultToolkit().getImage("paint01.png");  
	img$  = Toolkit.getDefaultToolkit().getImage("$.png");
	img0  = Toolkit.getDefaultToolkit().getImage("0.png");
	img2  = Toolkit.getDefaultToolkit().getImage("2.png");
	img3  = Toolkit.getDefaultToolkit().getImage("3.png");
	img7  = Toolkit.getDefaultToolkit().getImage("7.png");
	imgQ  = Toolkit.getDefaultToolkit().getImage("Q.png");
	imgB  = Toolkit.getDefaultToolkit().getImage("B.png");
	imgAktivMouse = img0;
		for ( int i= 0, y = 0; i <= 24; i++, y += 25 )
		{
		for ( int j = 0, x = 0; j <= 40; j++, x +=25 )
			{
			editorListElement.addElement( x,y,25,25,img0, '0' );
			}
		}
	paintListener.addElement( 18,110,30,50,img3,'3');
	paintListener.addElement( 70,100,30,50,img2,'2');
	paintListener.addElement( 110,110,30,50,imgQ,'q');
	paintListener.addElement( 200,170,35,50,imgB,'B');
	paintListener.addElement( 251,170,30,50,img$,'$');
	paintListener.addElement( 301,170,30,50,img7,'7');
	paintListener.addElement( 18,250,30,50,img0, '0');
	

	//===========================___Konstrukteur___=================================ENDE
	}

	public Image paint()
	{
	//______________________________________________________paint
	mouseControlling();
	offImage = MainFrame.createImage(width, height);
	offGraphics = offImage.getGraphics();
//	offGraphics.drawImage( imgBackground,0,0,1000,800,MainFrame);
//	offGraphics.drawImage( ,0,0,MainFrame);

	for ( int i= 0, yy = 0; i < 24; i++, yy += 25 )
		{
		for ( int j = 0, xx = 0; j < 40; j++, xx +=25 )
			{
			offGraphics.drawImage( editorListElement.getElement(xx,yy),xx,yy,25,25,MainFrame);
			}
		}
	offGraphics.drawImage( imgPaint,paintX,paintY,MainFrame);
//	offGraphics.drawImage( imgAktivMouse,MouseListener.getX(),MouseListener.getY(),25,25,MainFrame);
try{
writerFile();
} catch (IOException e)  {  }
	return offImage;
	//______________________________________________________paint___ENDE
	}


public void writerFile() throws IOException
	{
	try
		{
		fileOut = new FileWriter("LevelEditor.txt");
		
			for ( int i= 0, y = 0; i < 24; i++, y += 25 )
			{
			for ( int j = 0, x = 0; j < 40; j++, x +=25 )
				{
				fileOut.write( editorListElement.getSign ( x,y ) );
				if (j == 39) { fileOut.write('\n'); }
				}
			}
		fileOut.close();
		} catch (IOException e)  {  }
	}

public void mouseControlling() 
{
//____________________________________________________

	if (  (MouseListener.eventPress()) ) 
	{
//+++++
		if ((MouseListener.getX() >= paintX) && (MouseListener.getX() <= (paintX + 345)) )
		{
			if ((MouseListener.getY() >= paintY) && (MouseListener.getY() <= (paintY + 400)) )
			{ 
			imgAktivMouse = paintListener.getElement ( MouseListener.getX() - paintX, MouseListener.getY() - paintY );
			aktivSign = paintListener.getSign ( MouseListener.getX() - paintX, MouseListener.getY() - paintY );
			}
			if ( imgAktivMouse == null )  
				{	
					if ( paintR ){ paintR = false; paintL = true; paintX += 500;}
					else { paintR = true; paintL = false; paintX -= 500; } 
				}
		}
		
		else 
		{
		if ( !(imgAktivMouse == null) )  { editorListElement.substitutionElement( MouseListener.getX(),MouseListener.getY(),imgAktivMouse, aktivSign );  }
		}
//+++++
	}



	//_____________________________________________________ENDE!!!
	}


}

