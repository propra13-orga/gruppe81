
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
	

/**
 * 
 * Klasse die Karte lädt
 *
 */

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
	public int startX=1;
	public int startY=25;
	public final int AWIDTH = 24, AHIGHT=40; //Array Dimension
	public final int BLOCKSIZE=25;
	NPC mob;
	public Image worldPics[];
	public ArrayList<Wall> wallslist;
	public Wall walls[][];
	boolean sol = true;
	//Block images
	public Image WALL, LEER,SMALLWALL, SMALLWHITE, EXIT,FIRE,ICEFIRE,TRAPUP,NPC2,NPC3,TRAPDOWN,TRAPREGHT,TRAPLEFT,SPIDER,WALLOB,WALLOB_2;
	public Image WALLOB_3,BOSS1,BOSS3,BOSS2,SNOWGR,SNOWGRASS,EICEWALL,WATER1,WATER2,WATER3,BLACK,SAND,BLUEMEN,GRASS,GRUND,GRUND1,BLUEME1,SHOP;
	public Image BAUM,STEIN,BUSH,HAUS,BANK1,BANK2,BLUMENBETT,KISTE,KISTE1,KISTE3,SAECKE,SAECKE1,GRASSFELD,TANNENBAUM,HOLZ3,WALD,BALKEN;
	public Image FELD2,HOLZ1,HOLZ2,AXT,BAUMFELD,FELD1,BAUMBANK,HEU1,HEU2,HEU3,KISTE4,WASSERFELD,STORYTELLER,STORYTELLER2,STORYTELLER3,ARMSCHIENE,RUESTUNG;
	public Image PYRAMIDE2,GRASS2;
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
	 GRASSFELD= new ImageIcon("grass_feld.png").getImage();
	 TANNENBAUM= new ImageIcon("tannenbaum.png").getImage();
	 HOLZ3= new ImageIcon("holz3.png").getImage();
	 WALD= new ImageIcon("wald1.png").getImage();
	 BALKEN = new ImageIcon("balken.png").getImage();
	 FELD1= new ImageIcon("feld1.png").getImage();
	 HOLZ1= new ImageIcon("holz1.png").getImage();
	 HOLZ2=new ImageIcon("holz2.png").getImage();
	 AXT=new ImageIcon("axt.png").getImage();
	 FELD2=new ImageIcon("feld.png").getImage();
	 GRASS2=new ImageIcon("grass2.png").getImage();
	
	 BAUMFELD=new ImageIcon("Baumfeld.png").getImage();
	 BAUMBANK =new ImageIcon("Baumbank.png").getImage();
	 HEU1=new ImageIcon("Heu1.png").getImage();
	 HEU2=new ImageIcon("Heu2.png").getImage();
	 HEU3=new ImageIcon("Heu3.png").getImage();
	 KISTE4=new ImageIcon("Kiste.png").getImage();
	 WASSERFELD=new ImageIcon("Brunnen.png").getImage();
	 
	 FIRE = new ImageIcon("redfire_eishintergrund.gif").getImage();
	 ICEFIRE = new ImageIcon("icefire_eishintergrund.gif").getImage();
	 EICEWALL = new ImageIcon("garmschuppe.png").getImage();
	 SNOWGR = new ImageIcon("Snowground.png").getImage();
	 SNOWGRASS = new ImageIcon("schneegrass.png").getImage();
	 STORYTELLER = new ImageIcon("Moench_Braun.png").getImage();
	 STORYTELLER2 = new ImageIcon("Moench_Rot.png").getImage();
	 STORYTELLER3 = new ImageIcon("Moench_Blau.png").getImage();
	 ARMSCHIENE = new ImageIcon("Armschiene_R.png").getImage();
	 RUESTUNG = new ImageIcon("Armor_B.png").getImage();
	 
	 NPC3 =new ImageIcon("familiar.gif").getImage();
	 NPC2 =new ImageIcon("cramp.gif").getImage();
	 SPIDER =new ImageIcon("Spinne_mit_Hintergrund2525.png").getImage();
	 WALLOB =new ImageIcon("Boden2525orangebrocken.png").getImage();
	 WALLOB_2 =new ImageIcon("Boden2525orangebrocken2.png").getImage();
	 WALLOB_3 =new ImageIcon("Wall2525braunhhieroglyphen.png").getImage();
	 PYRAMIDE2=new ImageIcon("Pyramide2.png").getImage();
	 
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
	 wallslist = new ArrayList<Wall>();
	 getLevel("level"+levelNumber+".txt");
	// loadArrays();
	
	 
	 
 }
  
 
 
 private void loadArrays(){

		
			
	
 }
/**
 * Gibt die Welt auf dem Bildschirm aus.
 * @param g Graphics
 */
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

  /**
   * 
   * @Load level from file.txt
   */
public void getLevel(String fileName) { //reading level from file

	BufferedReader input = null;
	try {
		int i=0;
		String line = null;
		if (game.mainWindow.gameClient != null) {
			System.out.println("getLevel Clientversion");
			String[] splitServerInput = fileName.split(" ");
			if (splitServerInput[0].equals("WORLD")) {
				i = Integer.parseInt(splitServerInput[1]);
				if (splitServerInput.length>2) {
					line = splitServerInput[2]; 					
				}
			}
		} else {
			System.out.println("getLevel Serverversion "+i);
			File file = new File(fileName);
			input = new BufferedReader(new FileReader(file));
			line = input.readLine();
		}

		
		if (game.mainWindow.gameServer!=null) {
			game.mainWindow.gameServer.gameServerThread.serverOut.println("WORLD NEW");
		}
		while (line!=null) {
			if (game.mainWindow.gameServer!=null) {
				game.mainWindow.gameServer.gameServerThread.serverOut.println("WORLD "+i+" "+line);
			}
			x=0;
			for (int  j= 0; j < line.length(); j++) {
				checkpoints[i][j]=false;
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
        	
        		case '0': 	blockImage[i][j]=SMALLWHITE;
            				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
            				break;
        		case '1': 	wallslist.add(new Wall(x, y, sol));
						//	walls[i][j] = new Wall(x, y, sol);
        					blockImage[i][j]= SMALLWALL;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case '2': 	blockImage[i][j]=EXIT;
        					game.addElement(x, y, EXIT, 25, 50);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							exits[i][j] = true;
							break;
        		case '"': 	blockImage[i][j]=EXIT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							exits[i][j] = true;
							break;
        		case '3': 	blockImage[i][j]=TRAPUP;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					trap[i][j] = true;
        					break;
        		
        		case '4': 	blockImage[i][j]=TRAPDOWN;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		case '5': 	blockImage[i][j]=SPIDER;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		case '6': 	blockImage[i][j]= BAUMBANK;
        					game.addElement(x, y, BAUMBANK, 103, 109);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
							

        		case '7':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//	log("X="+x+" Y="+y);
							game.addNPC(x, y);
							break;
        		case '8': 	blockImage[i][j]= SMALLWALL;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					break;
        		case '9': 	blockImage[i][j]=EXIT;
							game.addElement(x, y, EXIT, 25, 50);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							finish[i][j] = true;
							break;
        		case ')': 	blockImage[i][j]=EXIT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							finish[i][j] = true;
							break;
				
        		
        		
				
							
							
				case 'A': 	blockImage[i][j]=SNOWGRASS;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'a': 	blockImage[i][j]= HOLZ1;
        					game.addElement(x, y, HOLZ1, 33, 31);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'B':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//	log("X="+x+" Y="+y);
							game.addBOSS1(x, y);
							break;
        		case 'b': 	blockImage[i][j]=BALKEN;
        					game.addElement(x, y, BALKEN, 63, 29);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'C':	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//	log("X="+x+" Y="+y);
							game.addNPC2(x, y);
							break;	
        		case 'c': 	blockImage[i][j]=HOLZ3;
        					game.addElement(x, y, HOLZ3, 38, 29);
        					blocks[i][j] = new Rectangle	(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'D': blockImage[i][j]=ICEFIRE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		case 'd': 	blockImage[i][j]=PYRAMIDE2;
        					game.addElement(x, y, PYRAMIDE2, 1000, 600);
        					blocks[i][j] = new Rectangle	(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =false;
        					break;
        		case 'E': 	wallslist.add(new Wall(x, y, sol));
							//	walls[i][j] = new Wall(x, y, sol);
        					//   blockImage[i][j]= SMALLWALL;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'e': 	blockImage[i][j]=WALLOB_3;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'F':	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//	log("X="+x+" Y="+y);
							game.addNPC3(x, y);
							break;
        		case 'f': 	blockImage[i][j]=BLUEMEN;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'G':	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//	log("X="+x+" Y="+y);
							game.addBOSS3(x, y);
							break;
        		case 'g': 	blockImage[i][j]= AXT;
        					game.addElement(x, y, AXT, 38, 33);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'H':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//		log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 20, 0, 0,"collectable",true);
							break;
        		case 'h': 	blockImage[i][j]= HOLZ2;
        					game.addElement(x, y, HOLZ2, 40, 36);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;		
        		case 'I': 	blockImage[i][j]= BAUMFELD;
        					game.addElement(x, y, BAUMFELD, 87, 77);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;	
        		case 'i': 	blockImage[i][j]= FELD2;
        					blockImage[i][j]=FELD2;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
			
							break;	
        		case 'J':	blockImage[i][j]=SAND;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							//	log("X="+x+" Y="+y);
							game.addBOSS2(x, y);
							break;
        		 case 'j': 	blockImage[i][j]= GRASS2;  
							game.addElement(x, y, GRASS, 50, 50);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =false;
							break;	
				case 'K':	blockImage[i][j]=SNOWGR;
				        	blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
				        	//		log("X="+x+" Y="+y);
				        	game.addHealthPack(x, y, 20, 0, 0,"collectable",true);
				        	break;	
            	case 'k': 	blockImage[i][j]= FELD1;
        					game.addElement(x, y, FELD1, 25, 25);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
            	case 'L': 	blockImage[i][j]= HEU1;
        					game.addElement(x, y, HEU1, 60, 53);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'l': 	blockImage[i][j]=TRAPLEFT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
         		case 'M':	blockImage[i][j]=LEER;
         					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
         					log("X="+x+" Y="+y);
         					game.addHealthPack(x, y, 0, 20, 0,"collectable",true);
         					break;
        		case 'm': 	blockImage[i][j]=GRASS;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'N': 	blockImage[i][j]= HEU2;
        					game.addElement(x, y, HEU2, 71, 73);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'n': 	blockImage[i][j]= BLUEME1;
        					game.addElement(x, y, BLUEME1, 25, 25);
        					blocks[i][j] = new Rectangle	(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;
        					break;
        		case 'O': 	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		case 'o': 	blockImage[i][j]=BAUM;
        					game.addElement(x, y, BAUM, 65, 69);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =true;  //setze den Baum als Mauer
							break;
        		case 'P': 	blockImage[i][j]= HEU3;
							game.addElement(x, y, HEU3, 49, 51);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'p':	blockImage[i][j]=STEIN;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'Q': 	blockImage[i][j]=WALLOB;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case 'q': 	blockImage[i][j]=BUSH;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true; 
							break;
        		case 'R': 	blockImage[i][j]= KISTE4;
							game.addElement(x, y, KISTE4, 133, 103);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;		
        		case 'r': 	blockImage[i][j]=TRAPREGHT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							trap[i][j] = true;
							break;
        		case 'S': 	blockImage[i][j]= SHOP;
							game.addElement(x, y, SHOP, 128, 185,"shop",true);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;	
        		case 's': 	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;

        		case 'T': 	blockImage[i][j]= KISTE;
							game.addElement(x, y, KISTE, 64, 97);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;	
        		case 't': 	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'U': 	blockImage[i][j]=GRASS;
        					game.addElement(x, y, STORYTELLER, 25, 50,"story",true);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'u': 	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
        		case 'V': 	blockImage[i][j]=GRASS;
        					game.addElement(x, y, ARMSCHIENE, 25, 50,"collectable",true);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							finish[i][j] = true;
        					break;
        		case 'v': 	blockImage[i][j]=GRASS;
        					game.addElement(x, y, ARMSCHIENE, 25, 25,"weapon",true);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					break;
        		case 'W': 	blockImage[i][j]=FELD2;
        					game.addElement(x, y, RUESTUNG, 25, 25,"armor",true);
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					break;
				case 'w': 	blockImage[i][j]=WALLOB_2;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
			
				case 'X': 	blockImage[i][j]=GRASS;
							game.addElement(x, y, STORYTELLER2, 25, 50,"story",true);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
				case 'x': 	blockImage[i][j]=SMALLWHITE;
							game.addElement(x, y, STORYTELLER3, 25, 50,"story",true);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							break;
							
			// Y
			
			// y
							
				case 'Z': 	blockImage[i][j]= GRUND;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							startX=j*25;
							startY=i*25;
							System.out.println("x: "+startX+" y: "+startY);
				break;		
				case 'z': 	blockImage[i][j]= GRUND;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							checkpoints[i][j] = true;
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
        		case '<': 	blockImage[i][j]= WASSERFELD;
							game.addElement(x, y, WASSERFELD, 28, 22);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
							
				//case '>':
							
        		case '(': 	blockImage[i][j]= SAECKE1;
							game.addElement(x, y, SAECKE1, 77, 101);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
							
				//case ')':
				
				//case '?':
							
							
        		case '#': 	blockImage[i][j]= GRASSFELD;
							game.addElement(x, y, GRASSFELD, 580, 419);         // !!!!!!!
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case '%': 	blockImage[i][j]= TANNENBAUM;
							game.addElement(x, y, TANNENBAUM, 62, 66);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case '&': 	blockImage[i][j]= WALD;
							game.addElement(x, y, WALD, 62, 66);
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =true;
							break;
        		case '§':	blockImage[i][j]=SNOWGR;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 0, 0, 1,"collectable",true);
							break;
        		case '$':	blockImage[i][j]=SMALLWHITE;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							log("X="+x+" Y="+y);
							game.addHealthPack(x, y, 0, 0, 1,"collectable",true);
							break;
        				default:{}
        		}
        		
        
        		
        		
        		
                x=x+BLOCKSIZE;
        	}
        	y=y+BLOCKSIZE;
        	i++;
    		if (game.mainWindow.gameClient != null) {
    			line = null;
    		} else {
    			line = input.readLine();
    		}
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
//	System.out.println(s);
}



public void pause() {
	pause=true;
}

public void resume() {
	pause=false;
	game.shop=null;
}

public boolean isPaused() {
	return pause;
}

}