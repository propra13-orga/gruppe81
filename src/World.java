
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Rectangle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import javax.swing.ImageIcon;

import Object.EntityDestroyable;
	



public class World {

	
	
	public int levelNumber = 1;
	public Rectangle[][] blocks;
	private Image[][] blockImage;
	public boolean [][] checkpoints;
	public boolean[][] isSolid;
	public boolean [][] exits;
	public boolean [][] trap;
	public boolean [][] finish;
	public boolean [][] npc;
	public boolean [][] shop;
	public final int AWIDTH = 24, AHIGHT=40; //Array Dimension
	public final int BLOCKSIZE=25;
	NPC mob;
	public Image worldPics[];
	public ArrayList<Wall> wallslist;
	public Wall walls[][];
	boolean sol = true;
	//Block images
	public Image WALL, LEER,SMALLWALL, SMALLWHITE, EXIT,FIRE,ICEFIRE,TRAPUP,NPC2,NPC3,TRAPDOWN,TRAPREGHT,TRAPLEFT,SPIDER,WALLOB,WALLOB_2,WALLOB_3,BOSS1,BOSS3,BOSS2,SNOWGR,SNOWGRASS,EICEWALL,WATER1,WATER2,WATER3,BLACK,SAND,BLUEMEN,GRASS,GRUND,GRUND1,BLUEME1,SHOP,BAUM,STEIN,BUSH,HAUS;
	public Image BANK1,BANK2,BLUMENBETT,KISTE,KISTE1,KISTE3,SAECKE,SAECKE1;
	private boolean pause=false;
	private int x=0, y=0;
	DungeonCrawlerGame game;
	Controller c;
	

 public World(int levelNumber, DungeonCrawlerGame game){

	 this.game = game;
	 worldPics = new Image[31];
//	 for(int i=0;i<)
//	 SMALLWHITE = new ImageIcon("smallwhite.gif").getImage();
	 SMALLWHITE = new ImageIcon("Boden2525.png").getImage();
	 SMALLWALL = new ImageIcon("Wall2525.png").getImage();
	 EXIT =new ImageIcon("Door2550.png").getImage();
	 TRAPUP =new ImageIcon("Falle_O.png").getImage();
	 TRAPDOWN =new ImageIcon("Falle_U.png").getImage();
	 TRAPREGHT =new ImageIcon("Falle_R.png").getImage();
	 TRAPLEFT =new ImageIcon("Falle_L.png").getImage();
	 BOSS1 =new ImageIcon("Bossmummy.png").getImage();
	 BOSS3 =new ImageIcon("Bossmummy.gif").getImage();
	 BOSS2 =new ImageIcon("Boss1gifanimation.gif").getImage();
	 
	 WATER1 = new ImageIcon("water1.png").getImage();
	 WATER2 = new ImageIcon("water2.png").getImage();
	 WATER3 = new ImageIcon("water3.png").getImage();
	 BLACK = new ImageIcon("black.png").getImage();
	 SAND = new ImageIcon("sand.png").getImage();
	 BLUEMEN = new ImageIcon("bluemen.png").getImage();
	 GRASS = new ImageIcon("grass.png").getImage();	
	 GRUND = new ImageIcon("grund.png").getImage();
	 GRUND1 = new ImageIcon("grund1.png").getImage();
     BLUEME1 = new ImageIcon("blueme1.png").getImage();


	 SHOP= new ImageIcon("laden1.png").getImage(); //shop
	 KISTE= new ImageIcon("Kiste2.png").getImage(); //shop
	 KISTE1= new ImageIcon("2.png").getImage();
	 KISTE3= new ImageIcon("Kiste3.png").getImage();
	 LEER= new ImageIcon("leer.png").getImage();   //shop
	 BAUM=new ImageIcon("Baum.png").getImage();    //shop
	 STEIN=new ImageIcon("stein.png").getImage();  //shop
	 BUSH=new ImageIcon("Bush.png").getImage();    //shop
	 HAUS=new ImageIcon("Haus.png").getImage();    //shop
	 BLUMENBETT=new ImageIcon("Blumenbeet.png").getImage(); //shop
	 BANK1 = new ImageIcon("bank_horizont.png").getImage(); //shop
	 BANK2 = new ImageIcon("bank_vertikal.png").getImage(); //shop
	 SAECKE= new ImageIcon("Saecke.png").getImage();
	 SAECKE1= new ImageIcon("Saecke2.png").getImage();
	 
	
	 
	 FIRE = new ImageIcon("redfire_eishintergrund.gif").getImage();
	 ICEFIRE = new ImageIcon("icefire_eishintergrund.gif").getImage();
	 EICEWALL = new ImageIcon("garmschuppe.png").getImage();
	 SNOWGR = new ImageIcon("Snowground.png").getImage();
	 SNOWGRASS = new ImageIcon("schneegrass.png").getImage();
	 
	 
	 NPC3 =new ImageIcon("familiar.gif").getImage();
	 NPC2 =new ImageIcon("cramp.gif").getImage();
	 SPIDER =new ImageIcon("Spinne_mit_Hintergrund2525.png").getImage();
	 WALLOB =new ImageIcon("Boden2525orangebrocken.png").getImage();
	 WALLOB_2 =new ImageIcon("Boden2525orangebrocken2.png").getImage();
	 WALLOB_3 =new ImageIcon("Wall2525braunhhieroglyphen.png").getImage();
	 
	 this.levelNumber = levelNumber;
	// WALL = new ImageIcon("wall.jpg").getImage();
	// LEER = new ImageIcon("whiteBlock.gif").getImage();
	 blocks = new Rectangle[AWIDTH][AHIGHT];
	 blockImage = new Image [AWIDTH][AHIGHT];
	 isSolid = new boolean[AWIDTH][AHIGHT];
	 trap = new boolean[AWIDTH][AHIGHT];
	 finish = new boolean[AWIDTH][AHIGHT];
	 exits = new boolean[AWIDTH][AHIGHT];
	 walls = new Wall[AWIDTH][AHIGHT];
	 checkpoints = new boolean[AWIDTH][AHIGHT];
	 shop= new boolean[AWIDTH][AHIGHT];
	 wallslist = new ArrayList<Wall>();
	 getLevel("level"+levelNumber+".txt");
	// loadArrays();
	
	 
	 
 }
  
 
 
 private void loadArrays(){

		
			
	
 }

public void draw(Graphics g){
	
	
	//g.setColor(Color.RED);
	//g.drawString("TEST DRAW STRING", 100, 250);
	for(int i=0;i <AWIDTH;i++){
		for(int j=0;j<AHIGHT; j++){
			if(blockImage[i][j]!=null)
			g.drawImage(blockImage[i][j], blocks[i][j].x, blocks[i][j].y, null);
//			if (walls[i][j]!=null) {
//				walls[i][j].draw(g);
//			}
			}
	}
	for(Wall tempWall: wallslist){
	//log(wallslist.get(k).toString());
	//	wallslist.get(k).update();
		
		tempWall.draw(g);
	}
}

  /*
   * 
   * @Load level from file.txt
   */
public void getLevel(String fileName) { //reading level from file
    
   

    BufferedReader input = null;
    try {
        File file = new File(fileName);
        input = new BufferedReader(new FileReader(file));
        String line = null;
//        for (int i = 0; (line = input.readLine()) != null; i++) {
       int i=0;
        while ((line = input.readLine())!=null) {
            
            x=0;
        	for (int  j= 0; j < line.length(); j++) {
       
                System.out.print("i="+i+" j="+j+" Gelesen:   ");
               System.out.println(line.charAt(j));
               
               checkpoints[i][j]=false;
               shop[i][j]=false;
               exits[i][j] = false;
               trap[i][j] = false;
               finish[i][j] = false;
               isSolid[i][j] =false;
        
              /*  
                if (line.charAt(j) == '1') {
                 blockImage[i][j]= SMALLWALL;
                 blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
                 isSolid[i][j] =true;
                }else {
                	blockImage[i][j]=SMALLWHITE;
                	blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
                	  isSolid[i][j] =false;
                } */
        		
        		
        		
        		switch (line.charAt(j)){
        		case '1': 	wallslist.add(new Wall(x, y, sol));
        				//	walls[i][j] = new Wall(x, y, sol);
        				    blockImage[i][j]= SMALLWALL;
                			blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
                			isSolid[i][j] =true;
            				break;
        		case '0': 	blockImage[i][j]=SMALLWHITE;
            				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
            				break;
        		case '2': 	blockImage[i][j]=EXIT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							exits[i][j] = true;
							break;
        		case '3': 	blockImage[i][j]=TRAPUP;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					trap[i][j] = true;
        					break;
        					
        					
        		case 'D': blockImage[i][j]=ICEFIRE;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
				break;
        		case 'O': 	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
				break;
				
        					
        		case '4': 	blockImage[i][j]=TRAPDOWN;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		
        		case 'r': 	blockImage[i][j]=TRAPREGHT;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				trap[i][j] = true;
				break;
        		
        		
        		case 'l': 	blockImage[i][j]=TRAPLEFT;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				trap[i][j] = true;
				break;
        		
        		
        		
        		case 'E': 	wallslist.add(new Wall(x, y, sol));
				//	walls[i][j] = new Wall(x, y, sol);
				 //   blockImage[i][j]= SMALLWALL;
        			blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        			isSolid[i][j] =true;
    				break;
				
        		
        		
        		case '5': 	blockImage[i][j]=SPIDER;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		case '8': 	blockImage[i][j]= SMALLWALL;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					break;
        		case '9': 	blockImage[i][j]=EXIT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							finish[i][j] = true;
							break;
				
        		case '7':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
						//	log("X="+x+" Y="+y);
							game.addNPC(x, y);
							break;
        		
							
        		case 'F':	blockImage[i][j]=SNOWGR;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
			//	log("X="+x+" Y="+y);
				game.addNPC3(x, y);
				break;
							
        	
        		case 'C':	blockImage[i][j]=SNOWGR;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
			//	log("X="+x+" Y="+y);
				game.addNPC2(x, y);
				break;		
							
							
							
        		case 'B':	blockImage[i][j]=SMALLWHITE;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
			//	log("X="+x+" Y="+y);
				game.addBOSS1(x, y);
				break;
        		
        		
				
        		case 'J':	blockImage[i][j]=SAND;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
			//	log("X="+x+" Y="+y);
				game.addBOSS2(x, y);
				break;
				
        		case 'G':	blockImage[i][j]=SNOWGR;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
			//	log("X="+x+" Y="+y);
				game.addBOSS3(x, y);
				break;
				
				
        		case 'Q': 	blockImage[i][j]=WALLOB;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				isSolid[i][j] =true;
				break;
        		
        		case 'w': 	blockImage[i][j]=WALLOB_2;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				isSolid[i][j] =true;
				break;
        		
        		
        		case 'e': 	blockImage[i][j]=WALLOB_3;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				isSolid[i][j] =true;
				break;
				
				
				case 's': 	blockImage[i][j]=SNOWGR;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				break;
				
				
				case 'A': 	blockImage[i][j]=SNOWGRASS;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				isSolid[i][j] =true;
				break;
				
				
				case 'K':	blockImage[i][j]=SNOWGR;
				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
		//		log("X="+x+" Y="+y);
				game.addHealthPack(x, y, 20, 0, 0);
				break;	
				
				
        		case 'H':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
					//		log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 20, 0, 0);
							break;
        		case 'M':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 0, 20, 0);
							break;
        		
							
							
							
        		case 'a': 	blockImage[i][j]= WATER1;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'b': 	blockImage[i][j]=BLACK;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'c': 	blockImage[i][j]=WATER2;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'd': 	blockImage[i][j]=WATER3;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'q': 	blockImage[i][j]=BUSH;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true; 
							break;
        		case 'm': 	blockImage[i][j]=GRASS;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'n': 	blockImage[i][j]= BLUEME1;
        					game.addElement(x, y, BLUEME1, 25, 25);
        					blocks[i][j] = new Rectangle	(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'o': 	blockImage[i][j]=BAUM;
        					game.addElement(x, y, BAUM, 65, 69);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;  //setze den Baum als Mauer
							break;
        		case 'f': 	blockImage[i][j]=BLUEMEN;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'p':	blockImage[i][j]=STEIN;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
           		case '*': 	blockImage[i][j]=HAUS;
							game.addElement(x, y, HAUS, 135, 217);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;  
							break;
        		case '+': 	blockImage[i][j]=BANK1;
							game.addElement(x, y, BANK1, 78, 29);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;  
							break;
        		case '-': 	blockImage[i][j]=BLUMENBETT;
							game.addElement(x, y, BLUMENBETT, 34, 35);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;  
							break;
        		case '/': 	blockImage[i][j]=BANK2;
							game.addElement(x, y, BANK2, 30, 92);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;  
							break;
        		case '|': 	blockImage[i][j]= KISTE3;
							game.addElement(x, y, KISTE3, 65, 31);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case '!': 	blockImage[i][j]= KISTE1;
							game.addElement(x, y, KISTE1, 68, 104);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;	
        		case '~': 	blockImage[i][j]= SAECKE;
							game.addElement(x, y, SAECKE, 37, 63);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;	
        		case '@': 	blockImage[i][j]= SAECKE1;
							game.addElement(x, y, SAECKE1, 77, 101);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'T': 	blockImage[i][j]= KISTE;
							game.addElement(x, y, KISTE, 64, 97);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;	
							
        		
							
							
	case 'k': 	blockImage[i][j]= GRUND1;
	blocks[i][j] = new Rectangle	(x,y, BLOCKSIZE,BLOCKSIZE);
	break;



				
							
	case 'h': 	blockImage[i][j]= GRUND;
	blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
	break;

				
	case 'g': 	blockImage[i][j]= SAND;
	blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
	break;			
							
				case 'z': 	blockImage[i][j]= GRUND;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							checkpoints[i][j] = true;
							break;			
							
				case 'S': 	blockImage[i][j]= SHOP;
							game.addElement(x, y, SHOP, 128, 185);
							//
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							shop[i][j] = true;
							break;	
				
				
							
        		
        		
        		case '§':	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 0, 0, 1);
							break;
			
        		
        		
        		
        		
        		case '$':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 0, 0, 1);
							break;
        				default:{}
        		}
        		
        
        		
        		
        		
                x=x+BLOCKSIZE;
        	}
        	y=y+BLOCKSIZE;
        	i++;
        }
    }catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
   
    
}
private void log(String s){
	System.out.println(s);
}



public void pause() {
	pause=true;
}

public void resume() {
	pause=false;
}

public boolean isPaused() {
	return pause;
}

}