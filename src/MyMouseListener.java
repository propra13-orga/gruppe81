import java.awt.*;
import java.awt.event.*;


/**
 * Klasse fuer die Maus-Steuerung im Editor
 */
public class MyMouseListener extends MouseAdapter
{
//______________________________________________class_MyMouseListener________________________________________________________

//+++++Variable
private int x,y;
private boolean press = false, pull = false;
//+++++Variable__ENDE

/**
 * ist die Maustaste gedrueckt?
 */
public boolean eventPress()  
	{
	//_______Ereignis_wird_gefragt________________
	return press;
	//_______END__________event________________END
	}

/**
 * wird die Muas bewegt?
 */
public boolean eventPull()  
	{
	//_______Ereignis_wird_gefragt________________
	return pull;
	//_______END__________event________________END
	}

public int getX() { return x; }
public int getY() { return y; }

/**
 * speichere die Koordinaten des Mauszeigers
 */
public void mouserMoved(MouseEvent e) 
	{
	x = e.getX();
	y = e.getY();
	}	

/**
 * speichere, dass die Maustaste gedrueckt wird
 * @param e
 */
public void mousePressed(MouseEvent e) 
	{
	//________eine_moustaste_wurde_gedru:ckt_______кнопка_мыши_была_нажата__________
	x = e.getX();
	y = e.getY();
	press = true;
	//_________________________________________END______mousePressed_______END
	}


/**
 * speichere, dass die Maustaste nicht mehr gedrueckt wird
 * @param e
 */
public void mouseReleased(MouseEvent e) 
	{
	//_____eine_moustaste_wurde_losgelassen_______кнопка_мыши_была_отпущена_________
	
	x = e.getX();
	y = e.getY();
	press = false;
	pull = false;	
	//________________________________________END______mouseReleased______END
	}

/**
 * speichere, dass die Maus bewegt wird
 * @param e
 */
public void mouseDragged(MouseEvent e)
	{
	//________eine_moustaste_wurde_gedru:ckt_______кнопка_мыши_была_нажата__________
	x = e.getX();
	y = e.getY();
	pull = true;
	//_________________________________________END______mousePressed_______END
	}

//_______________________END____________________class_MyMouseListener______________________END____________________________END
}
