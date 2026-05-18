import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class MarioBrothers extends GameBase
{	

	
	
	TileMap map = new TileMap("world_1-1_OW.map", 32, 16);

	static Mario smario = new Mario("sm", 50, 350, Mario.RT);
	static Mario bmario = new Mario("bm", 50, 350, Mario.RT);
	
	static Mario mario = smario;
	//if false mario = small mario, else big mario
	boolean size;
	
	
	Goomba goomba = new Goomba(500, 375, Goomba.FORWARD);
	
	public void inGameLoop()
	{
		mario.update(mario);
		//goomba.update(mario);

		mario.grounded = false;
		
	    for(int i = 0; i < TileMap.rects.size(); i++)
		{
			if(mario.overlaps(TileMap.rects.get(i)))
			{
				TileMap.rects.get(i).pushes(mario);
			
			}
			
			
		}
	    if(mario.control) {
			if(pressing[_W]) {
				mario.jump();
			}
			if(pressing[_S]) {
				if(mario.name == "bm") mario.duck();
			}
			if(pressing[_A]) {
				mario.goLT();
				if(mario.x <= Camera.x) mario.stayBounded();
	
			}
			if(pressing[_D]) {
				mario.goRT();
				if(mario.x > Camera.x + screenWidth/2) Camera.moveRight(5);
				
			}if(!(pressing[_S])) {
				mario.ducking = false;
			}
	    }
//		mario.move();

	}
	
	
	
	public void paint(Graphics g)
	{	
		
		map.draw(g);
		mario.draw(g);
		goomba.draw(g);
	
		
	}
	
	
}
