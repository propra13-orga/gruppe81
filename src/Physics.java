import java.util.ArrayList;
import java.util.LinkedList;


import Object.EntityDestroyable;
import Object.EntityMapObject;
import Object.EntityMovable;


public class Physics {
//	private LinkedList<EntityDestroyable> ed = new LinkedList<EntityDestroyable>();
//	private LinkedList<EntityMovable> em = new LinkedList<EntityMovable>();
	
	
	public static  boolean CollisionWithDesroyable(EntityMovable entM, LinkedList<EntityDestroyable>	ed){
		
		for(int i =0; i<ed.size();i++)
		{
			if(entM.getBounds().intersects(ed.get(i).getBounds()) && ed.get(i).isNPC()){
				entM.hit();
				return true;
			}
		}
		
		return false;
	}
	
	
public static  boolean CollisionWithMovable(EntityDestroyable entD, LinkedList<EntityMovable> em){
		
		for(int j =0; j<em.size();j++)
		{	
			
			if(entD.getBounds().intersects(em.get(j).getBounds())) {
System.out.println("collision!!!!!");
				return true;
			}
		}
		
		return false;
	}
	
	public static  boolean CollisionGameObjectList(GameObject ob, LinkedList<EntityDestroyable> ed){ //For Player, or other game object
	
	for(int j =0; j<ed.size();j++){	
		
		if(ed.get(j).getBounds().intersects(ob.getBounds())){
		return true;
		}
	}
	
	return false;
	}
	
	public static  boolean CollisionGameObjectList(Entity ob, LinkedList<EntityMapObject> eWO){ //For Player, or other game object
		
		for(int i =0; i<eWO.size();i++){			
		//	System.out.println("Physics: Rect["+i+"] in List eWO "+eWO.get(i).getBounds().toString());
		//	eWO.get(i).getBounds().toString();
			if(eWO.get(i).getBounds().intersects(ob.getBounds())){
			return true;
			}
		}
		
		return false;
		}
	public static boolean CollisionGameObjectArrayList(GameObject ob, ArrayList<Wall> walls){
		
		for(Wall tempWall:walls){
		if(	tempWall.intersects(ob))
			return true;
			
		}
		
		return false;
	}
	public static void removeElement(){
		
	}
}
