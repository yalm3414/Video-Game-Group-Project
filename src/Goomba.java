import java.awt.Graphics;
import java.awt.Toolkit;

public class Goomba extends Sprite
{
	final static String[] pose = {"forward"};
	boolean moveDir = false;
	boolean alive = true;
	Rect gtop;

	public Goomba(int x, int y, int direction)
	{
		super("g", x, y, 40, 40, 4, 0, pose);
		Rect gtop = new Rect(x, y+1, 40, 0);
		this.physics = true;
		boolean moveDir = false;
	//	When moveDir is false, goomba is moving left.  When true, it moves right
		
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(animation[direction].nextImage(2), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);

	}
	
	public void update(Mario m) {
		if(moveDir == false)
		{
			this.goLT(5);
		}
		if(pushed == true && moveDir == false) {
			this.goRT(5);
			moveDir = true;
		}
		if(pushed == true && moveDir == true) {
			this.goLT(5);
			moveDir = false;
		}
		if(moveDir == true) {
			this.goRT(5);
		}
		//the goomba death function; just yeets it downward offscreen
		if(this.gtop.overlaps(m)){
			this.physics = false;
			this.goDN(5000);
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
