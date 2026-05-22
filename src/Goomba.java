import java.awt.Graphics;
import java.awt.Toolkit;

public class Goomba extends Sprite
{
	final static String[] pose = {"forward"};
	boolean moveDir = false;
	boolean alive = true;
	Rect gtop;

	public Goomba(int x, int y)
	{
		super("g", x, y, 30, 30, 3, 0, pose);
		gtop = new Rect(x, y+1, 40, 0);
		this.physics = true;
		moveDir = true;
	//	When moveDir is false, goomba is moving left.  When true, it moves right
		
	}
	
	public void draw(Graphics g)
	{
	
		g.drawImage(animation[0].nextImage(2), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);

	}
	
	public static void update(Goomba g) {
		if(g.moveDir == false)
		{
			g.moveLT(1);
		}
		if(g.moveDir == true) {
			g.moveRT(1);
		}
		if(g.pushed == true && g.moveDir == false) {
			g.moveRT(2);
			g.moveDir = true;
		}
		if(g.pushed == true && g.moveDir == true) {
			g.moveRT(1);
			g.moveDir = false;
		}
		
		//the goomba death function; just yeets it downward offscreen
		if(g.gtop.overlaps(g)){
			g.physics = false;
			g.goDN(5000);
		}
		
			
		
	}

		
	
}

//		if(Goomba.overlaps() == true && moveDir == false) {
//			moveDir = true;
//		}
//		else if(Goomba.overlaps() == true && moveDir == true) {
//			moveDir = false;
//		} else {
//			continue;
//		}
//		
//	}
//	
//
//}
//import java.awt.Graphics;
//import java.awt.Toolkit;
//
//public class Goomba extends Sprite
//{
//	final static String[] pose = {"forward"};
//	
//
//	
//
//	public Goomba(int x, int y, int direction)
//	{
//		super("g", x, y, 40, 40, 4, 0, pose);
//		this.physics = true;
//		boolean moveDir = false; //When moveDir is false, goomba is moving left.  When true, it moves right
//		
//	}
//	
//	public void draw(Graphics g)
//	{
//		g.drawImage(animation[direction].nextImage(2), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
//
//	}
//	
//	public void movement() {
//		if(moveDir == false)
//		{
//			vx = -10;
//		}
//		if(moveDir == true) {
//			vx == 10;
//		}
//
//	}
//	
//	public void changeDir() {
//		if(Goomba.overlaps() == true && moveDir == false) {
//			moveDir = true;
//		}
//		else if(Goomba.overlaps() == true && moveDir == true) {
//			moveDir = false;
//		} else {
//			continue;
//		}
//		
//	}
//	
//
//}
