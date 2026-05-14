import java.awt.Graphics;
import java.awt.Toolkit;

public class Mario extends Sprite
{

	final static String[] pose = {"lt", "rt"};
	boolean jumping = false;
	boolean ducking = false;

	public Mario(String name, int x, int y, int direction)
	{
		super(name, x, y, 0, 0, 6, direction, pose);
		
		if(name == "bm") {
			this.name = "bm";
			this.w = 38;
			this.h = 96;
		}
		else {
			this.name = "sm";
			this.w = 24;
			this.h = 30;
		}
	}
	public void jump()
	{
		if(grounded == true) {
			vy = -20;
			jumping = true;
		}
		
		grounded = false;
		moving = true;
	}
	public void duck(int v) {
		
		if(grounded) ducking = true;
		
	}
	public void goLT(int dx)
	{
		if(!(ducking)) {
			vx = -dx;
	
			direction = LT;
			
			moving = true;
		}
	}
	
	public void goRT(int dx)
	{
		
		if(!(ducking)) {
		vx = dx;
		
		direction = RT;

		moving = true;
		}

	}
	
	public void draw(Graphics g)
	{
		
		if(!(jumping) && (!(ducking))) {
			if(moving)
			{
				g.drawImage(animation[direction].nextImage(3), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
			}
			else
			{
				g.drawImage(animation[direction].stillImage(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
			}	
		}
		if(jumping) {
			g.drawImage(animation[direction].getJump(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
		}
		if(ducking) {
			g.drawImage(animation[direction].getDuck(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
		}
		
		moving = false;
	}

	
}