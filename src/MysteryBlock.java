import java.awt.Graphics;
import java.awt.Image;
import java.util.*;

public class MysteryBlock extends Sprite {

	static int totalFrames = 4;
	static String name = "Coin_Block";
	static String[] type = {"OW"};
	
	
	boolean activated = false;
	int frames;
	
	
	//Map<String, Integer> items = new HashMap<>();

	Item[] items;
	
	Random random = new Random();
	
	Item item;
	Sprite s;
	
	public MysteryBlock(int x, int y, int w, int h, int direction) {
		
		super(name, x, y, w, h, totalFrames, direction, type);
		
		items = new Item[]
				{
						new Item("Coin",     5, x + (w/4), y-(h + 1), w/2, h,0),
						new Item("Mushroom", 1, x, y-(h+1), w, h,0),
						new Item("Flower",   4, x, y-(h+1), w, h,0)
				};
	}
	
	
	
	public void pushes(Mario s)
	{
		if(pushed == false) {
			pushUp(s);
		}
		if(pushed == false) {
			pushDown(s);
		}
		if(pushed == false) {
			pushRight(s);
		}
		if(pushed == false) {
			pushLeft(s);
		}
		if(pushed == true) pushed = false;
	}
	public void pushLeft(Mario s)
	{
		double penetration = s.x + s.w - x;
		
		if(penetration < s.w/2) {
			s.x -= penetration + 2;
	
			s.vx = 0;
		}
	}
	
	public void pushRight(Mario s)
	{
		double penetration = x + w - s.x;
		
		if(penetration < s.w/2) {

			s.x += penetration + 2;
			pushed = true;
			s.vx = 0;
		}
	}
	
	public void pushUp(Mario s)
	{
		double penetration = s.y + s.h - y ;
		
		if(penetration < s.h) {
			
			s.y -= penetration + 1;
			s.grounded = true;
			s.jumping = false;
			pushed = true;
		}
	}
	
	public void pushDown(Mario s)
	{
		double penetration = y + h - s.y;
		
		if(penetration < s.h/2) {
			
			s.y += penetration + 5;
			s.vy = 0;
			pushed = true;
			
			if(!activated)
			{
				activated = true;
				frames = 3;
				
				createItem();
			}
			
		}
	}
	
	public void createItem()
	{
		int chance = random.nextInt(100);
		
		if(chance < 80 && chance >= 0)
		{
			TileMap.items.add(items[0]);
			TileMap.items.get(TileMap.items.size() - 1).jump();
		} else if (chance < 90 && chance >= 80)
		{
			TileMap.items.add(items[1]);
		} else if (chance < 100 && chance >=90)
		{
			TileMap.items.add(items[2]);
		}
		
	}
	
	public void draw(Graphics g)
	{
		
		if (activated && frames > 0)
		{
			y -= 8;
			frames -=1;
		}
		if (activated && frames == 0)
		{
			y += 24;
			frames -= 1;
		}
		
		
		if (activated)
		{
			
			g.drawImage(animation[direction].stillImage(), (int) (x-Camera.x) , (int) (y - Camera.y), w, h, null);
		} else {
			g.drawImage(animation[direction].nextImage(3), (int) (x-Camera.x) , (int) (y - Camera.y), w, h, null);
		}
		
		
		
	}
}
