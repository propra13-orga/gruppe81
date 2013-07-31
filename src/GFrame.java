import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Frame fuer den Editor
 */
public class GFrame extends Frame {
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>___class___GameFrame_____ 
private int height, width;
private static Editor editor;
public static MyMouseListener MouseListener;
public static GFrame myFrame;

public GFrame()
	{
	//************* ***************** *************_______Konstrukteur
	super("MyGame: Version 1.0");


	setBackground( new Color(0,0,0));
	setSize(1000,600);
//	setSize(getToolkit().getScreenSize());            // 
	setLocation(50,50);                               //
	setVisible(true);
	width = getWidth();
	height = getHeight();
	MouseListener = new MyMouseListener();


	addMouseMotionListener(MouseListener);

	addMouseListener(MouseListener);
	addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent ev){System.exit(0);}});



	//*************	***************** *************_______Konstrukteur___ENDE
	}


public void paint(Graphics g)
	{
	editor = new Editor(this , MouseListener);
	g.drawImage( editor.paint(),0,0,width,height ,this );	
	repaint();
	}


public void update(Graphics g) { 	g.drawImage( editor.paint(),0,0,width,height ,this );      repaint(); }

//public static void main (String [] args)  
//	{
//	myFrame = new GFrame();
//	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>___class___GameFrame_____ENDE
}
