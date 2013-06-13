import java.awt.Graphics;
import java.util.LinkedList;

import Object.EntityDestroyable;
import Object.EntityMovable;


public class Controller {

	
	LinkedList<EntityDestroyable> ed = new LinkedList<EntityDestroyable>();
	LinkedList<EntityMovable> em = new LinkedList<EntityMovable>();
	
	EntityDestroyable tempEntDe;
	EntityMovable tempEntMov;
	DungeonCrawlerGame game;
	public Controller(DungeonCrawlerGame game){
		
		this.game=game;
		
		
	}
	
	public void update(){
		//Entity Destroyable
		for (int i=0;i<ed.size();i++){
			tempEntDe = ed.get(i);
			if(tempEntDe!=null){
			tempEntDe.update();
			if(tempEntDe.getX()>1100 || tempEntDe.getY()>650 || tempEntDe.getX()<0 || tempEntDe.getY()<0)
				removeEntity(tempEntDe);
			}
		}
		//Entity Movable
		for (int i=0;i<em.size();i++){
			tempEntMov = em.get(i);
			tempEntMov.update();
			if(tempEntMov.getX()>1100 || tempEntMov.getY()>650 || tempEntMov.getX()<0 || tempEntMov.getY()<0)
				removeEntity(tempEntMov);
		}
	}
	
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
	}

	public void addEntity(EntityDestroyable ent) {
		ed.add(ent);
		
	
	}

	public void removeEntity(EntityDestroyable ent){
		ed.remove(ent);
	
	}
	
	public void addEntity(EntityMovable ent) {
		em.add(ent);
		
	
	}
	
	public void removeEntity(EntityMovable ent){
		em.remove(ent);
	
	}
}

