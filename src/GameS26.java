

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GameS26 extends GameBase
{	

//	Background bg = new Background("mariobg.jpg",1192,670);
	
	ImageLayer bg = new ImageLayer("mariobg.jpg",1,1,1,1192,670);
	

	Mario mario = new Mario(50, 500, Mario.LT);
	
	int[] x = 
	{
			3, 
			5, 
			62, 
			23, 
			3, 
			65
	};
	
	Rect[] platform = 
	{
		new Rect(-100, 590, 120000, 40),
			
	};
	
	
	
	
	
	Image[] tile = new Image[18];
	
	
	public void loadTiles()
	{
		
		for(int i = 0; i < tile.length; i++)
		{
			tile[i] = Toolkit.getDefaultToolkit().getImage("winter" + (i+1) + ".png");
		}
	}
	
	/*
	String[] map = 
	{
		"...ABC................ABC",
		".........................",
		".........................",
		".........................",
		".........................",
		"......NOOOOOOOOP.........",
		".........................",
		".........................",
		".........................",
		".........................",
		".........................",
		".........................",
		"QQQQQQQQQQQQQQQQQQQQQQQQQ",
		"RRRRRRRRRRRRRRRRRRRRRRRRR",
	};
	*/
	
	int v = 6;
	
	public void inGameLoop()
	{
		
		loadTiles();
		
		
	    mario.physics = true;
			
			// Move User Controlled Objects
	    
	    
		if(pressing[UP]) {
			mario.jump();
		}
		if(pressing[DN]) {
			mario.goDN(v);
		}
		if(pressing[LT]) {
			mario.goLT(v);
			if(mario.x <= Camera.x) mario.stayBounded();
//			Camera.moveLeft(6);
		}
		if(pressing[RT]) {
			mario.goRT(v);
			if(mario.x > Camera.x + screenWidth/2) Camera.moveRight(6);
			
		}else {
			
//		  	s.idle(); 
		}

		mario.move();
		
			

			
			// Move Computer Controlled Objects

			
			// Handle Collisions
			
		
		for(int i = 0; i < platform.length; i++)
		{
			if(mario.overlaps(platform[i]))
			{
				platform[i].pushes(mario);
				
				mario.vx = 0;
				mario.vy = 0;
				
				mario.grounded = true;
			}
		}
		
			//if(r2.overlaps(s))  r2.pushes(s);
			//if(r3.overlaps(s))  r3.pushes(s);
			
			// Update the Screen
	}
	
	
	
	public void paint(Graphics g)
	{	
		bg.draw(g);
		mario.draw(g);
		
		
		g.fillRect((int)platform[0].x, (int)platform[0].y, platform[0].w, platform[0].h);
		
		for(int i = 0; i < platform.length; i++)
		{
			//platform[i].draw(g);
		}
		
		Image image = null;
/*		
		int w = 64;
		int h = 64;
		
		for(int row = 0; row < map.length; row++)
		{
			for(int col = 0; col < map[row].length(); col++)
			{
				char c = map[row].charAt(col);
				
				if(c == 'A')  image = tile[0];
				if(c == 'B')  image = tile[1];
				if(c == 'C')  image = tile[2];
				if(c == 'N')  image = tile[13];
				if(c == 'O')  image = tile[14];
				if(c == 'P')  image = tile[15];
				if(c == 'Q')  image = tile[16];
				if(c == 'R')  image = tile[17];
				if(c == '.')  image = null;
				
				
				if(image != null)  g.drawImage(image, col * w, row * h, w, h, null);
			}
			
		}
		*/
	}
	
	
}
