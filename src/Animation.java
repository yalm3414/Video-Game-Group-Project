import java.awt.Image;
import java.awt.Toolkit;

public class Animation
{
	Image[] image;
	
	int current = 0;
	
	int duration;
	int delay;
	
	public Animation(String name, int count, int duration, String filetype)
	{
		image = new Image[count];
		
		this.duration = duration;
		
		delay = duration;
		
		for(int i = 0; i < image.length; i++)
		{
			image[i] = getImage(name + "_" + i + "." + filetype);
		}
	}
	
	
	public Image stillImage()
	{
		return image[0];
	}
	
	//WalkFrames is how many frames are dedicated to walking
	//In the image array the walking frames are from index 1 to walkFrames
	//Anything after that is dedicated to other animation possibilities like jumping, dying, etc
	public Image nextImage(int walkFrames)
	{
		delay--;
		
		if(delay == 0)
		{
			if( current == walkFrames)   current = 1;
			else                             current++;
			
			delay = duration;
		}
				
		return image[current];
	}
	
	
	public Image getImage(String filename)
	{
		return Toolkit.getDefaultToolkit().getImage(filename);
	}


	public Image getJump() {
		return image[4];
	}
	public Image getDuck() {
		return image[5];
	}
	


}