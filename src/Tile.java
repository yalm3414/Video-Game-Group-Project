import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Tile extends Rect {

	//static int totalFrames = 4;
	//static String name = "Coin_Block";
	//static String[] type = {"OW"};
	
	int x;
	int y;
	int w;
	int h;
	
	String name;
	Image image;
	public Tile(Image image,  int x, int y, int w, int h) {
		
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.image = image;
		
	}
	
	public Image getImage(String filename)
	{
		return Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	public void draw(Graphics g)
	{
		
		g.drawImage(image, (x-Camera.x) , (y - Camera.y), w, h, null);
	}
}
