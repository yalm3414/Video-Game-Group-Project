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
		moveDir = false;
	//	When moveDir is false, goomba is moving left.  When true, it moves right
		
	}
	
	public void draw(Graphics g)
	{
	
		g.drawImage(animation[direction].nextImage(2), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);

	}
	

	
	
	
	public static void update(Goomba g) {
		if(g.pushed == false && g.moveDir == false)
		{
			g.x -=1;
			g.gtop.x -= 1;
		}
		if(g.pushed == false && g.moveDir == true) {
			g.x += 1;
			g.gtop.x += 1;
		}
		if(g.pushed == true && g.moveDir == false) {
			g.x -=1;
			g.gtop.x -= 1;
			g.moveDir = true;
			g.pushed = false;
		}
		if(g.pushed == true && g.moveDir == true) {
			g.x +=1;
			g.gtop.x += 1;
			g.moveDir = false;
			g.pushed = false;
		}
		
		//the goomba death function; just drops it downward offscreen fast enough to not risk also killing mario
		if(MarioBrothers.mario.overlaps(g.gtop)){
			g.alive = false;
			g.physics = false;
			g.moveDN(5000);
		}
		
		if(MarioBrothers.mario.overlaps(g) && g.alive && MarioBrothers.mario.overlaps(g.gtop)== false ) {
			MarioBrothers.mario.isDead = true;
		}
		
			
		
	}

		
	
}

