import java.awt.Image.*;
import java.awt.*;

//________!!!!!!!!!!!!!!!!!!!_________Es__gibt_auch__UnterClass!!!___ganz__unten______!!!!!!!!!!!!!!!!!!

class ListElement 
{
//______________________________________________________class______pencilListElement_____________________________

//++++Variable
private Element head;       
private Element tail;       
//++++Variable_END


//public ListElement() 
//	{
	//===========================___Konstrukteur___=================================
	
	
	
	//==========END==============___Konstrukteur___===============END===============END
//	}






    void addElement(int x, int y,int width ,int height, Image imgNew, char newSign )          
    {
//__________________________________________
        Element setNew = new Element();		
        setNew.x = x;					
        setNew.y = y;					

        setNew.width = width;              
        setNew.height = height;             
	setNew.imgElement = imgNew;
	setNew.sign = newSign;

                                    
        if(head == null)            
        {                           
            head = setNew;               
            tail = setNew;
        }
        else	
	{
            setNew.next = head;          
            head = setNew;               
	}
//__________________________________________
    }
 


public Image getElement (int x, int y)
    {
//______________check__and_getElement
	Element check = head;            
	while (check != null)                 
		{
		//+++
		if ( (check.x <= x) && ((check.x + check.width) >= x) )
			{
			if ( (check.y <= y) && ((check.y +check.height) >= y) )  { return check.imgElement; }    
			} 

		check = check.next;                    
		//+++
		}
	return null;
//_______________getElement______ENDE
   }



public char getSign (int x, int y)
    {
//______________check__and_getElement
	Element check = head;               
	while (check != null)                 
		{
		//+++
		if ( (check.x <= x) && ((check.x + check.width) >= x) )
			{
			if ( (check.y <= y) && ((check.y +check.height) >= y) )  { return check.sign; }    
			} 

		check = check.next;                     
		//+++
		}
	return 'q';
//_______________getElement______ENDE
   }



public void substitutionElement( int x, int y, Image imgNew, char newSign )                     
	{
//______________check__and_substitution
	Element check = head;             
	while (check != null)                 
		{
		//+++
		if ( (check.x < x) && ((check.x + check.width) > x) )
			{
			if ( (check.y < y) && ((check.y +check.height) > y) )  { check.imgElement = imgNew;  check.sign = newSign;}    
			} 

		check = check.next;                     
		//+++
		}
//_______________substitution______ENDE
	}


class Element 
	{
	//==============================UnterCLASS=================================

	Element next;
	int x, y;
	int  width, height;
	Image imgElement;
	char sign;
	//===========_END_==============UnterCLASS=================_END_===========
	}


//_________END_______________END___________________class___pencilListElement_______________END_______________END
}



