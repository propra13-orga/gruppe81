import java.util.LinkedList;

import Object.EntityDestroyable;
import Object.EntityMovable;


public class Physics {
//	private LinkedList<EntityDestroyable> ed = new LinkedList<EntityDestroyable>();
//	private LinkedList<EntityMovable> em = new LinkedList<EntityMovable>();
	
	
	public static  boolean CollisionWithDesroyable(EntityMovable entM, LinkedList<EntityDestroyable>	ed){
		
		for(int i =0; i<ed.size();i++)
		{
			if(entM.getBounds().intersects(ed.get(i).getBounds())){
				entM.hit();
				return true;
			}
		}
		
		return false;
	}
	
	
public static  boolean CollisionWithMovable(EntityDestroyable entD, LinkedList<EntityMovable> em){
		
		for(int j =0; j<em.size();j++)
		{	
			
			if(entD.getBounds().intersects(em.get(j).getBounds()))
			return true;
		}
		
		return false;
	}
	
	public static void removeElement(){
		
	}
}
