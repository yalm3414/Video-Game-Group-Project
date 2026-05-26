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
	
	
	static Goomba goomba = new Goomba(700, 385);
	
	public void inGameLoop()
	{
		mario.update(mario);
		Goomba.update(goomba);

		mario.grounded = false;
		
		// Collision detection and handling for all tiles in tileMap for mario
		map.collisionOn(mario);
	    
		// Moves items and handles collision/consumptions of items with mario and tiles
		for(int i = 0; i < TileMap.items.size(); i++)
		{	
			TileMap.items.get(i).move();
			
			if(mario.overlaps(TileMap.items.get(i)) && TileMap.items.get(i).name == "Mushroom")
			{	
				TileMap.items.remove(i);
				mario.grows();
				break;
			}
			else if(mario.overlaps(TileMap.items.get(i)) && TileMap.items.get(i).name == "Flower")
			{
				
			}
			
			map.collisionOn(TileMap.items.get(i));
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
				if(mario.x > Camera.x + screenWidth/2 && Camera.x < 5400) {
					Camera.x = Camera.x + mario.vx;
				}
				
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
		
		// Draw all the itesm
		for(int i = 0; i < TileMap.items.size(); i++)
		{	
			TileMap.items.get(i).draw(g);
		}
	}
	
	
}
