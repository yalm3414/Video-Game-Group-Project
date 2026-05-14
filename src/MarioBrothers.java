import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MarioBrothers extends GameBase
{	

	TileMap tilemap = new TileMap("world_1-1_OW.map", 48, 16);
	ArrayList<Rect> tiles = tilemap.rects;
	
	Mario mario = new Mario("bm", 50, 500, Mario.RT);
	Goomba goomba = new Goomba(500, 555, Goomba.FORWARD);
	
	int v = 6;
	int[] x = 
	{
			3, 
			5, 
			62, 
			23, 
			3, 
			65
	};
	/*
	Rect[] platform = 
	{
		new Rect(-100, 590, 120000, 40),
			
	};
	*/
	
	
	
	
	
	public void inGameLoop()
	{
		
		
	    mario.physics = true;
			
			// Move User Controlled Objects
	    
	    
		if(pressing[_W]) {
			mario.jump();
		}
		if(pressing[_S]) {
			mario.duck(v);
		}
		if(pressing[_A]) {
			mario.goLT(v);
			if(mario.x <= Camera.x) mario.stayBounded();

		}
		if(pressing[_D]) {
			mario.goRT(v);
			if(mario.x > Camera.x + screenWidth/2) Camera.moveRight(6);
			
		}if(!(pressing[_S])) {
			mario.ducking = false;
		}

		mario.move();
		
			

			
			// Move Computer Controlled Objects

			
			// Handle Collisions
			
		for(int i = 0; i < tiles.size(); i++)
		{
			if(mario.overlaps(tiles.get(i)))
			{
				tiles.get(i).pushes(mario);
			
			}
		}
		
		
		/*
		for(int i = 0; i < platform.length; i++)
		{
			if(mario.overlaps(platform[i]))
			{
				platform[i].pushes(mario);
				
				mario.vx = 0;
				mario.vy = 0;
				
				mario.grounded = true;
				mario.jumping = false;
			}
		}
		*/
		
		
			
			// Update the Screen
	}
	
	
	
	public void paint(Graphics g)
	{	
		tilemap.draw(g);
		mario.draw(g);
		goomba.draw(g);
		
		//g.fillRect((int)platform[0].x, (int)platform[0].y, platform[0].w, platform[0].h);
		
		
	}
	
	
}
