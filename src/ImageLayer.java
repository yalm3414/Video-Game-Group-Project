import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer
{
	Image image;
	
	int x;
	int y;
	int z;
	
	int w;
	int h;
	
	public ImageLayer(String filename, int x, int y, int z, int w, int h)
	{
		image = Toolkit.getDefaultToolkit().getImage(filename);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.w = w;
		this.h = h;
	}
	
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < 20; i++)
		{
			g.drawImage(image, x  + w*i - Camera.x/z, y - Camera.y/z, w, h, null);
			
		}
	}
	

}