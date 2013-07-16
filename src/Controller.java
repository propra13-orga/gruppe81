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
	private int schaden=-12;
	public Controller(DungeonCrawlerGame game){
		
		this.game=game;
	
		
	}
	
	public void update(Player thisplayer){
		//Entity Destroyable
		for (int i=0;i<ed.size();i++){
			tempEntDe = ed.get(i);
	//		System.out.println("Current index for Destroyable"+i);
			if(tempEntDe!=null){
				tempEntDe.update();
//				if((tempEntDe.getX()>1100 || tempEntDe.getY()>650 || tempEntDe.getX()<0 || tempEntDe.getY()<0) || Physics.CollisionWithMovable(tempEntDe, game.em)) {
				if(Physics.CollisionWithMovable(tempEntDe, game.em)) {
					tempEntMov = Physics.CollisionWithWhichMovable(tempEntDe, game.em);
					System.out.println("X: "+tempEntDe.getX()+"Y: "+tempEntDe.getY());
					 schaden=-12;
					
					
				
					
				//	if ((tempEntDe.getElementArt()==2)&& (tempEntMov.getElementArt()==1)) {schaden=schaden;}
					if ((tempEntDe.getElementArt()==2)&& (tempEntMov.getElementArt()==2)) {schaden=0;}
					if ((tempEntDe.getElementArt()==2)&& (tempEntMov.getElementArt()==3)) {schaden=schaden*2;}
					if ((tempEntDe.getElementArt()==1)&& (tempEntMov.getElementArt()==2)) {schaden=schaden*2;}
					if ((tempEntDe.getElementArt()==1)&& (tempEntMov.getElementArt()==1)) {schaden=0;}
				//	if ((tempEntDe.getElementArt()==1)&& (tempEntMov.getElementArt()==3)) {schaden=schaden;}
				    if ((tempEntDe.getElementArt()==3)&& (tempEntMov.getElementArt()==1)) {schaden=schaden*2;}
				//	if ((tempEntDe.getElementArt()==3)&& (tempEntMov.getElementArt()==2)) {schaden=schaden;}
					if ((tempEntDe.getElementArt()==3)&& (tempEntMov.getElementArt()==3)) {schaden=0;}
					
					
					
					
					
					
					tempEntDe.changeLifepoints(schaden, 250000000);
					if (!tempEntDe.isAlive()) {
						removeEntity(tempEntDe);
					}
				}
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
		thisplayer.setHitShop(false); 
		thisplayer.setHitStory(false); 
		for (int i=0;i<eWO.size();i++){
			tempEntWO = eWO.get(i);
	//		System.out.println("Current index for MapObject"+i);
			
			if((Physics.CollisionGameObjectList(thisplayer, tempEntWO))){
				if (tempEntWO.getLeben()!=0) {thisplayer.changePlayerLifepoints(tempEntWO.getLeben(), 0);}
				if (tempEntWO.getMana()!=0) {thisplayer.changePlayerManapoints(tempEntWO.getMana());}
				if (tempEntWO.getGeld()!=0) {thisplayer.changePlayerMoney(tempEntWO.getGeld());}
				if( tempEntWO.isWeapon()){					
					thisplayer.setWeapon(true); 
				}
				if( tempEntWO.isArmor()){					
					thisplayer.setArmor(true); 
				}
				if( tempEntWO.isShop()){					
					thisplayer.setHitShop(true); 
				}
				if( tempEntWO.isStory()){					
					thisplayer.setHitStory(true); 
				}
				if (tempEntWO.isCollectable()) removeEntity(tempEntWO);
			//	System.out.println("COLLISION "+i);
				//removeEntity(tempEntWO);
			}
			if(tempEntWO!=null)
				tempEntWO.update();
		}	
	
	}//======================END OF Update
	
	public void draw(Graphics g){
		//Ent. MO
		for (int i=0;i<eWO.size();i++){
			tempEntWO = eWO.get(i);
			if(tempEntWO!=null)
			tempEntWO.draw(g);
		}
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

	public void addEntity(EntityMapObject ent,String special,boolean wert) {
		if (special == "shop") {
			ent.setShop(wert);
		}
		if (special == "story") {
			ent.setStory(wert);
		}
		if (special == "weapon") {
			ent.setWeapon(wert);
			ent.setCollectable(wert);
		}
		if (special == "armor") {
			ent.setArmor(wert);
			ent.setCollectable(wert);
		}
		if (special == "collectable") {
			ent.setCollectable(wert);
		}
	
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



