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
	
	
	static Goomba goomba1 = new Goomba(700, 385);
	static Goomba goomba2 = new Goomba(1100, 385);
	static Goomba goomba3 = new Goomba(1500, 385);
	static Goomba goomba4 = new Goomba(1700, 385);
	static Goomba goomba5 = new Goomba(2700, 385);
	static Goomba goomba6 = new Goomba(3200, 385);
	
	public void inGameLoop()
	{
		mario.update(mario);
		Goomba.update(goomba1);
		Goomba.update(goomba2);
		Goomba.update(goomba3);
		Goomba.update(goomba4);
		Goomba.update(goomba5);
		Goomba.update(goomba6);

		mario.grounded = false;
		
		// Collision detection and handling for all tiles in tileMap for mario
		map.collisionOn(mario);
		map.gcollisionOn(goomba1);
		map.gcollisionOn(goomba2);
		map.gcollisionOn(goomba3);
		map.gcollisionOn(goomba4);
		map.gcollisionOn(goomba5);
		map.gcollisionOn(goomba6);
	    
		// Moves items and handles collision/consumptions of items with mario and tiles
		for(int i = 0; i < TileMap.items.size(); i++)
		{	
			TileMap.items.get(i).move();
			
			if(mario.overlaps(TileMap.items.get(i)) && TileMap.items.get(i).name == "Mushroom")
			{	
				TileMap.items.remove(i);
				if(mario == smario) {
					mario.grows();
				}
				break;
			}
			else if(mario.overlaps(TileMap.items.get(i)) && TileMap.items.get(i).name == "Flower")
			{
				TileMap.items.remove(i);
				
				break;
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
		goomba1.draw(g);
		goomba2.draw(g);
		goomba3.draw(g);
		goomba4.draw(g);
		goomba5.draw(g);
		goomba6.draw(g);
		
		// Draw all the itesm
		for(int i = 0; i < TileMap.items.size(); i++)
		{	
			TileMap.items.get(i).draw(g);
		}
	}
	
	
}
