package files.game;

import java.util.Formatter;

public class createFile {
	private Formatter x;
	
	public void openFile(){
		try {
			x = new Formatter("save.txt");
		
			}
	catch (Exception e){
		System.out.println("File can not be created");
		
		}
	}
	
	public void addRecords(double xx, double y, int life,int lifePoints, int money, boolean armor, boolean weapon, int level, int room){
		int iarmor = 0; if (armor) iarmor=1;
		int iweapon = 0; if (weapon) iweapon=1;
		x.format("%d:%d:%d:%d:%d:%d:%d:%d:%d",(int) xx,(int)y,life, lifePoints,money,iarmor,iweapon, level, room);
	}
	
	public void closeFile(){
		x.close();
	}
}