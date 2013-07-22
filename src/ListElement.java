import java.awt.Image.*;
import java.awt.*;

//________!!!!!!!!!!!!!!!!!!!_________Es__gibt_auch__UnterClass!!!___ganz__unten______!!!!!!!!!!!!!!!!!!

class ListElement 
{
//______________________________________________________class______pencilListElement_____________________________

//++++Variable
private Element head;       // указатель на первый элемент
private Element tail;       // указатель последний элемент
//++++Variable_END


//public ListElement() 
//	{
	//===========================___Konstrukteur___=================================
	
	
	
	//==========END==============___Konstrukteur___===============END===============END
//	}






    void addElement(int x, int y,int width ,int height, Image imgNew, char newSign )           //добавить спереди
    {
//__________________________________________
        Element setNew = new Element();		//создаём новый элемент
        setNew.x = x;					//инициализируем данные.
        setNew.y = y;					//инициализируем данные.

        setNew.width = width;              //инициализируем данные.
        setNew.height = height;              //инициализируем данные.
	setNew.imgElement = imgNew;
	setNew.sign = newSign;

                                    // указатель на следующий элемент автоматически инициализируется как null
        if(head == null)            //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            head = setNew;               //т.е. список теперь состоит из одного элемента
            tail = setNew;
        }
        else	
	{
            setNew.next = head;          // новый элемент теперь ссылается на "бывший" первый
            head = setNew;               //а указатель на первый элемент теперь ссылается на новый элемент
	}
//__________________________________________
    }
 


public Image getElement (int x, int y)
    {
//______________check__and_getElement
	Element check = head;             //получаем ссылку на первый элемент  
	while (check != null)                 //пока элемент существуе
		{
		//+++
		if ( (check.x <= x) && ((check.x + check.width) >= x) )
			{
			if ( (check.y <= y) && ((check.y +check.height) >= y) )  { return check.imgElement; }    
			} 

		check = check.next;                     //и переключаемся на следующий
		//+++
		}
	return null;
//_______________getElement______ENDE
   }



public char getSign (int x, int y)
    {
//______________check__and_getElement
	Element check = head;             //получаем ссылку на первый элемент  
	while (check != null)                 //пока элемент существуе
		{
		//+++
		if ( (check.x <= x) && ((check.x + check.width) >= x) )
			{
			if ( (check.y <= y) && ((check.y +check.height) >= y) )  { return check.sign; }    
			} 

		check = check.next;                     //и переключаемся на следующий
		//+++
		}
	return 'q';
//_______________getElement______ENDE
   }



public void substitutionElement( int x, int y, Image imgNew, char newSign )                     
	{
//______________check__and_substitution
	Element check = head;             //получаем ссылку на первый элемент  
	while (check != null)                 //пока элемент существуе
		{
		//+++
		if ( (check.x < x) && ((check.x + check.width) > x) )
			{
			if ( (check.y < y) && ((check.y +check.height) > y) )  { check.imgElement = imgNew;  check.sign = newSign;}    
			} 

		check = check.next;                     //и переключаемся на следующий
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



