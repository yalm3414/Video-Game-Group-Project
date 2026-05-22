import java.awt.*;

public class Rect
{
	double x;
	double y;
	
	int w;
	int h;
	
	Boolean pushed = false;
	
	double vx;
	double vy;
	
	int direction = LT;
	
	// Constant values that are used to index the
	// Animation array to select the correct
	// Animation for the direction the solder
	// is moving.
	static final int LT = 0;
	static final int RT = 1;
	static final int IDLE = 2;
	static final int FORWARD = 3;
	static final int DL = 4;
	static final int UR = 5;
	static final int DR = 6;
	
	
	boolean selected = false;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
	}
	
	public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected()
	{
		selected = true;
	}
	
	public void clearSelected()
	{
		selected = false;
	}
	
	public void toggle()
	{
		selected = ! selected;
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
			
			s.y += penetration + 1;
			s.vy = 0;
			pushed = true;
		}
	}
	
//	public void pushes(Rect r)
//	{
//		pushDown(r);
//		pushUp(r);
//		pushRight(r);
//		pushLeft(r);		
//	}
//	public void pushDown(Rect r)
//	{
//		double penetration = y + h - r.y;
//		
//		if(penetration < r.h/2) {
//			
//			r.y += penetration + 1;
//			pushed = true;
//		}
//	}
//	public void pushLeft(Rect r)
//	{
//		double penetration = r.x + r.w - x;
//		
//		if(penetration < r.w/2)
//			
//			r.x -= penetration + 1;
//	}
//	
//	public void pushRight(Rect r)
//	{
//		double penetration = x + w - r.x;
//		
//		if(penetration < r.w/2)
//			
//			r.x += penetration + 1;
//	}
//	
//	public void pushUp(Rect r)
//	{
//		double penetration = r.y + r.h - y ;
//		
//		if(penetration < r.h/2)
//			
//			r.y -= penetration + 1;
//	}
//	

	
	public boolean overlaps(Rect r)
	{
		return (x <= r.x + r.w) &&
			   (y <= r.y + r.h) &&
			   
			   (r.x <= x + w)   &&
			   (r.y <= y + h);	
	}
	
	public boolean contains(int mx, int my)
	{
		return (mx > x)   && 
			   (mx < x+w) && 
			   (my > y)   && 
			   (my < y+h);
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	
	public void draw(Graphics g)
	{
		g.drawRect((int)x, (int)y, w, h);
	}
	
}	
	
