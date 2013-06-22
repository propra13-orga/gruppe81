import java.awt.Graphics;
import java.util.LinkedList;

import Object.EntityDestroyable;
import Object.EntityMapObject;
import Object.EntityMovable;


public class Controller {

	
	LinkedList<EntityDestroyable> ed = new LinkedList<EntityDestroyable>();
	LinkedList<EntityMovable> em = new LinkedList<EntityMovable>();
	LinkedList<EntityMapObject> eWO = new LinkedList<EntityMapObject>();
	public EntityDestroyable tempEntDe;
	public EntityMovable tempEntMov;
	public EntityMapObject tempEntWO;
	DungeonCrawlerGame game;
	public Controller(DungeonCrawlerGame game){
		
		this.game=game;
		
		
	}
	
	public void update(){
		//Entity Destroyable
		for (int i=0;i<ed.size();i++){
			tempEntDe = ed.get(i);
	//		System.out.println("Current index for Destroyable"+i);
			if(tempEntDe!=null){
			tempEntDe.update();
			if((tempEntDe.getX()>1100 || tempEntDe.getY()>650 || tempEntDe.getX()<0 || tempEntDe.getY()<0) || Physics.CollisionWithMovable(tempEntDe, game.em))
				removeEntity(tempEntDe);
			}
		}
		//Entity Movable
//		System.out.println("Size of Bullets"+em.size());
		for (int i=0;i<em.size();i++){
			tempEntMov = em.get(i);
			if(((tempEntMov.getX()>1100 || tempEntMov.getY()>650 || tempEntMov.getX()<0 || tempEntMov.getY()<0))|| tempEntMov.isHited() ){
				removeEntity(tempEntMov);
			}
			if(tempEntMov!=null)
			tempEntMov.update();
		}
		//EntityMapObject
	//	System.out.println("Size of eWO "+eWO.size());
		for (int i=0;i<eWO.size();i++){
			tempEntWO = eWO.get(i);
	//		System.out.println("Current index for MapObject"+i);
			
			if((Physics.CollisionGameObjectList(game.p1, eWO))){
				if (tempEntWO.getLeben()!=0) {game.p1.changePlayerLifepoints(tempEntWO.getLeben(), 0);}
				if (tempEntWO.getMana()!=0) {game.p1.changePlayerManapoints(tempEntWO.getMana());}
				if (tempEntWO.getGeld()!=0) {game.p1.changePlayerMoney(tempEntWO.getGeld());}
				if( tempEntWO.isShop()){
					game.world.pause();
					game.p1.setHitShop(true); 
				}
				if (!tempEntWO.isShop()) removeEntity(tempEntWO);
			//	System.out.println("COLLISION "+i);
				//removeEntity(tempEntWO);
			}
			if(tempEntWO!=null)
				tempEntWO.update();
		}	
	
	}//======================END OF Update
	
	public void draw(Graphics g){
		//Entity Destroyable
		for (int i=0;i<ed.size();i++){
			tempEntDe = ed.get(i);
			tempEntDe.draw(g);
			}
		//Entity Movable
		for (int i=0;i<em.size();i++){
			tempEntMov = em.get(i);
			tempEntMov.draw(g);
			}
	//Ent. MO
		for (int i=0;i<eWO.size();i++){
			tempEntWO = eWO.get(i);
			if(tempEntWO!=null)
			tempEntWO.draw(g);
		}
	}
	

	public void addEntity(EntityDestroyable ent) {
		ed.add(ent);
		
	
	}

	public void removeEntity(EntityDestroyable ent){
		ed.remove(ent);
	
	}
	
	public void addEntity(EntityMovable ent) { //EntityMovable
		em.add(ent);
		
	
	}
	
	public void removeEntity(EntityMovable ent){
		em.remove(ent);
	
	}
	
	public void addEntity(EntityMapObject ent) {
		eWO.add(ent);
		
	
	}

	public void removeEntity(EntityMapObject ent){
		eWO.remove(ent);
	
	}
	public LinkedList<EntityDestroyable> getEntDestrList(){
		
		return ed;
	}
	public LinkedList<EntityMovable> getEntMovList(){
		
		return em;
	}
	
	public LinkedList<EntityMapObject> getEntMO(){
		
		return eWO;
	}
	


}



