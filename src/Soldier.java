
public class Soldier extends Sprite
{

	final static String[] pose = {"up", "dn", "lt", "rt"};
	

	public Soldier(int x, int y, int direction)
	{
		super("g", x, y, 80, 120, direction, pose);
	}
	

}