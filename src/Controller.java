import java.awt.Graphics;
import java.util.LinkedList;


public class Controller {

	
	LinkedList<Entity> e = new LinkedList<Entity>();
	
	Entity tempEnt;
	
	public Controller(DungeonCrawlerGame game){
		
		
		
	}
	
	public void update(){
		for (int i=0;i<e.size();i++){
			tempEnt = e.get(i);
			tempEnt.update();
			if(tempEnt.getX()>1100 || tempEnt.getY()>650 || tempEnt.getX()<0 || tempEnt.getY()<0)
				removeEntity(tempEnt);
		}
	}
	
	public void draw(Graphics g){
		for (int i=0;i<e.size();i++){
			tempEnt = e.get(i);
			tempEnt.draw(g);
			}
	}

	public void addEntity(Entity ent) {
		e.add(ent);
		
	
	}

	public void removeEntity(Entity ent){
		e.remove(ent);
	
	}

}

