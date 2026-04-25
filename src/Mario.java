
public class Mario extends Sprite
{

	final static String[] pose = {"lt", "rt"};
	

	public Mario(int x, int y, int direction)
	{
		super("m", x, y, 47, 52, direction, pose);
	}
	

}