
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Rectangle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;


public class World {

	public int levelNumber = 1;
	public Rectangle[][] blocks;
	private Image[][] blockImage;
	public boolean[][] isSolid;
	public boolean [][] exits;
	public boolean [][] trap;
	public boolean [][] finish;
	public boolean [][] npc;
	public final int AWIDTH = 24, AHIGHT=40; //Array Dimension
	public final int BLOCKSIZE=25;
	
	//Block images
	private Image WALL, LEER,SMALLWALL, SMALLWHITE, EXIT, TRAPH,TRAPL,SPIDER; 
	private int x=0, y=0;
	

 public World(int levelNumber){
	 
//	 SMALLWHITE = new ImageIcon("smallwhite.gif").getImage();
	 SMALLWHITE = new ImageIcon("Boden2525.png").getImage();
	 SMALLWALL = new ImageIcon("Wall2525.png").getImage();
	 EXIT =new ImageIcon("Door2550.png").getImage();
	 TRAPH =new ImageIcon("Falle_mit_Hintergrund_Oben2525.png").getImage();
	 TRAPL =new ImageIcon("Falle_mit_Hintergrund_Unten2525.png").getImage();
	 SPIDER =new ImageIcon("Spinne_mit_Hintergrund2525.png").getImage();
	 this.levelNumber = levelNumber;
	// WALL = new ImageIcon("wall.jpg").getImage();
	// LEER = new ImageIcon("whiteBlock.gif").getImage();
	 blocks = new Rectangle[AWIDTH][AHIGHT];
	 blockImage = new Image [AWIDTH][AHIGHT];
	 isSolid = new boolean[AWIDTH][AHIGHT];
	 trap = new boolean[AWIDTH][AHIGHT];
	 finish = new boolean[AWIDTH][AHIGHT];
	 exits = new boolean[AWIDTH][AHIGHT];
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
			g.drawImage(blockImage[i][j], blocks[i][j].x, blocks[i][j].y, null);
		}
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
        		case '1': 	blockImage[i][j]= SMALLWALL;
                			blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
                			isSolid[i][j] =true;
                			exits[i][j] = false;
            				trap[i][j] = false;
            				finish[i][j] = false;
                			break;
        		case '0': 	blockImage[i][j]=SMALLWHITE;
            				blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
            				isSolid[i][j] =false;
            				exits[i][j] = false;
            				trap[i][j] = false;
            				finish[i][j] = false;
            				break;
        		case '2': 	blockImage[i][j]=EXIT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =false;
							exits[i][j] = true;
							trap[i][j] = false;
							finish[i][j] = false;
							break;
        		case '3': 	blockImage[i][j]=TRAPH;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =false;
        					exits[i][j] = false;
        					trap[i][j] = true;
        					finish[i][j] = false;
        					break;
        		case '4': 	blockImage[i][j]=TRAPL;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =false;
							exits[i][j] = false;
							trap[i][j] = true;
							finish[i][j] = false;
							break;
        		case '5': 	blockImage[i][j]=SPIDER;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =false;
							exits[i][j] = false;
							trap[i][j] = true;
							finish[i][j] = false;
							break;
        		case '8': 	blockImage[i][j]= SMALLWALL;
        					blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
        					isSolid[i][j] =false;
        					exits[i][j] = false;
        					trap[i][j] = false;
        					finish[i][j] = false;
        					break;
        		case '9': 	blockImage[i][j]=EXIT;
							blocks[i][j] = new Rectangle(x,y, BLOCKSIZE,BLOCKSIZE);
							isSolid[i][j] =false;
							exits[i][j] = false;
							trap[i][j] = false;
							finish[i][j] = true;
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
 
}