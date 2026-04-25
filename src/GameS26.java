

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GameS26 extends GameBase
{	
	
	Background bg = new Background("mariobg.jpg",1192,670);
//	Soldier s = new Soldier(800, 0, Soldier.LT);
	Mario s = new Mario(50, 500, Mario.LT);
	
	
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
		new Rect(0, 600, 1200, 40),
			
	};
	
	int v = 4;
	
	
	
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
	public void inGameLoop()
	{
		loadTiles();
		
		
	    s.physics = true;
			
			// Move User Controlled Objects
	    
	    
		if(pressing[UP]) {
			s.jump();
		}
		if(pressing[DN]) {
			s.goDN(v);
		}
		if(pressing[LT]) {
			s.goLT(v);
			Camera.moveRight(v);
		}
		if(pressing[RT]) {
			s.goRT(v);
			Camera.moveLeft(v);
		}else {
			
//		  	s.idle(); 
		}

		s.move();
		
			

			
			// Move Computer Controlled Objects

			
			// Handle Collisions
			
		
		for(int i = 0; i < platform.length; i++)
		{
			if(s.overlaps(platform[i]))
			{
				platform[i].pushes(s);
				
				s.vx = 0;
				s.vy = 0;
				
				s.grounded = true;
			}
		}
		
			//if(r2.overlaps(s))  r2.pushes(s);
			//if(r3.overlaps(s))  r3.pushes(s);
			
			// Update the Screen
	}
	
	
	
	public void paint(Graphics g)
	{	
		bg.draw(g);
		s.draw(g);
		
		
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
