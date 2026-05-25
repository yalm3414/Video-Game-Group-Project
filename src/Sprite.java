import java.awt.Graphics;
import java.awt.Toolkit;

public class Sprite extends Rect
{
	String name;
		
	boolean moving = false;
	public boolean grounded = false;
	boolean physics = true;
	
	double g = 0.8;
	
	
	Animation[] animation;
	
	//totalFrames is the total amount of frames a sprite will have including still frames, walking frames, jumping, dying, etc.
	public Sprite(String name, int x, int y, int w, int h, int totalFrames, int direction, String[] pose)
	{
		super(x, y, w, h);
		
		this.name = name;
		animation = new Animation[pose.length];
		
		for(int i = 0; i < animation.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i], totalFrames, 10, "png");
		}		
		
		
		this.direction = direction;
	}
	
	//Removed poses array for sprites that only have one pose
	public Sprite(String name, int x, int y, int w, int h, int totalFrames, int direction)
	{
		super(x, y, w, h);
		
		this.name = name;
		animation = new Animation[1];
		
		for(int i = 0; i < animation.length; i++)
		{
			animation[i] = new Animation(name, totalFrames, 10, "png");
		}		
		
		
		this.direction = direction;
	}
	
	
	public void move()
	{
		x += vx;		
		y += vy;
		
		vy += g;

		
		if (physics == false)
		{
			vx = 0;
			vy = 0;
		}
	}
	
	public void jump()
	{
		vy = -10;
		
		moving = true;
	}
	
	public void goUP(int dy)
	{
		vy = -dy;
	//	direction = UP;
		
		moving = true;
	}
	
	public void goDN(int dy)
	{
		vy = dy;

	//	direction = DN;

		moving = true;
	}
	
	public void goLT(int dx)
	{
		vx = -dx;

		direction = LT;
		
		moving = true;
	}
	
	public void goRT(int dx)
	{
		vx = dx;
		
		direction = RT;

		moving = true;

	}
		
	public void moveUP(int dy)
	{
		y -= dy;
		
	//	direction = UP;
		
		moving = true;
	}
	
	public void moveDN(int dy)
	{
		y += dy;

	//	direction = DN;

		moving = true;
	}
	
	public void moveLT(int dx)
	{
		x -= dx;

		direction = RT;
		
		moving = true;
	}
	
	public void moveRT(int dx)
	{
		x += dx;
		
		direction = LT;

		moving = true;

	}
	
	public void stayBounded()
	{
		x = Camera.x;
		
		direction = LT;

		moving = true;

	}
		
	
	
	

}