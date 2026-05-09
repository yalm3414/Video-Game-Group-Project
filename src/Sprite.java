import java.awt.Graphics;

public class Sprite extends Rect
{
	String name;
		
	boolean moving = false;
	
	public boolean grounded = true;
	
	
	boolean physics = false;
	
	double g = 2.6;
	
	animCycle = 2;
	
	Animation[] animation = new Animation[animCycle];
	
	
	public Sprite(String name, int x, int y, int w, int h, int animCycle, int direction, String[] pose)
	{
		super(x, y, w, h);

		
		this.name = name;
		
		for(int i = 0; i < animation.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i], 3, 10, "png");
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
		if(grounded == true) {
			vy = -20;
		}
		
		grounded = false;
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
		
	
	public void draw(Graphics g)
	{
		if(moving)
		{
			g.drawImage(animation[direction].nextImage(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
		}
		else
		{
			g.drawImage(animation[direction].stillImage(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
		}		
		
		moving = false;
	}
	

}