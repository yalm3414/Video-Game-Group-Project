import java.awt.Graphics;
import java.awt.Toolkit;

public class Goomba extends Sprite
{
	final static String[] pose = {"forward"};
	

	

	public Goomba(int x, int y, int direction)
	{
		super("g", x, y, 40, 40, 4, 0, pose);
		this.physics = true;
		boolean moveDir = false; //When moveDir is false, goomba is moving left.  When true, it moves right
		
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(animation[direction].nextImage(2), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);

	}
	
	public void movement() {
		if(moveDir == false)
		{
			vx = -10;
		}
		if(moveDir == true) {
			vx == 10;
		}

	}
	
	public void changeDir() {
		if(Goomba.overlaps() == true && moveDir == false) {
			moveDir = true;
		}
		else if(Goomba.overlaps() == true && moveDir == true) {
			moveDir = false;
		} else {
			continue;
		}
		
	}
	

}
