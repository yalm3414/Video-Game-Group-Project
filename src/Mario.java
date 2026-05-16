import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Mario extends Sprite
{
    final static String[] pose = {"lt", "rt"};
    boolean jumping = false;
    boolean ducking = false;
    
    // Velocity & physics
    double gravity = .5;
    double speed = 5.0;
    double jumpForce = -12.0;
   
    
    // Variables to handle marios transformations
    Image[] growFrames = new Image[6];
    boolean growing = false;
    boolean shrinking = false;
    int growFrame = 0;
    int growTimer = 0;
    int growFrameDuration = 12; // frames to show each image
    boolean control = true;;


    public Mario(String name, int x, int y, int direction)
    {
        super(name, x, y, 0, 0, 6, direction, pose);
        
        if(name.equals("bm")) {
            this.name = "bm";
            this.w = 25;
            this.h = 63;
        }
        else {
            this.name = "sm";
            this.w = 24;
            this.h = 30;
        }
        createGrowFrames();
        
    }

    private void createGrowFrames() {
    	
    	// These 3 frames are for when he growing facing the left
    	  growFrames[0] = Toolkit.getDefaultToolkit().getImage("sm_lt_0.png");
    	  growFrames[1] = Toolkit.getDefaultToolkit().getImage("sm_grows_lt.png");
    	  growFrames[2] = Toolkit.getDefaultToolkit().getImage("bm_lt_0.png");
    	  
    	//These 3 frames are for when he grows facing right
    	  growFrames[3] = Toolkit.getDefaultToolkit().getImage("sm_rt_0.png");
    	  growFrames[4] = Toolkit.getDefaultToolkit().getImage("sm_grows_rt.png");
    	  growFrames[5] = Toolkit.getDefaultToolkit().getImage("bm_rt_0.png");
    	  
    	 // IF SHRINKING, THE FRAMES WILL BE LOADED IN REVERSE
	}
    int count = 0;
	public void update(Mario s)
    {

//		if(count == 0) {
//			if(MarioBrothers.mario.x > 500)	{
//			MarioBrothers.mario.grows();
//			count++;
//			}
//		}
//
//		if(count == 1) {
//			if(MarioBrothers.mario.x > 1700) {
//			MarioBrothers.mario.shrinks();
//			count++;
//			}
//		}
    	// Apply gravity
        if(!grounded) {
            vy += gravity;
        }

        // Apply velocity to position
        x += vx;
        y += vy;

        // Friction 
        vx *= .9;
        
        if(growing)
        {
            growTimer++;
            if(growTimer >= growFrameDuration)
            {
                growTimer = 0;
                growFrame++;
                if(growFrame >= 5)
                {
	                  MarioBrothers.mario = switchMarios();
	                  growing = false;
	                  control = true;
                }
            }
        }
        
        if(shrinking)
        {
        	growTimer++;
            if(growTimer >= growFrameDuration)
            {
                growTimer = 0;
                growFrame--;
                if((direction == LT && growFrame < 0) || (direction == RT && growFrame < 3))
                {
	                  MarioBrothers.mario = switchMarios();
	                  shrinking = false;
	                  control = true;
                }
            }
        }
       
    }
  
    private Mario switchMarios() {
		if(growing) {
			MarioBrothers.bmario.x = MarioBrothers.mario.x;
			MarioBrothers.bmario.y = MarioBrothers.mario.y;
			return 	MarioBrothers.mario = MarioBrothers.bmario;
		}
		else {
			MarioBrothers.smario.x = MarioBrothers.mario.x;
			MarioBrothers.smario.y = MarioBrothers.mario.y + 20;
			return 	MarioBrothers.mario = MarioBrothers.smario;
		
		}
		
	}

	public void jump()
    {
        if(grounded == true) {
            vy = jumpForce;
            jumping = true;
            grounded = false;
            moving = true;
        }
    }

    public void duck() {
        if(grounded) ducking = true;
    }

    public void goLT()
    {
        if(!(ducking)) {
            vx = -speed;
            direction = LT;
            moving = true;
        }
    }
    
    public void goRT()
    {
        if(!(ducking)) {
            vx = speed;
            direction = RT;
            moving = true;
        }
    }
    
    public void grows() {
    	growing = true;
    	control = false;
    	if(MarioBrothers.mario.direction == LT) {
    	     growFrame = 0;
    	}
    	else {
    		growFrame = 3;
    	}
   
        growTimer = 0;
    }
    public void shrinks() {
    	shrinking = true;
    	control = false;
    	vx = 0;
    	vy = 0;
    	if(MarioBrothers.mario.direction == LT) {
   	     growFrame = 2;
	   	}
	   	else {
	   		growFrame = 5;
	   	}
        growTimer = 0;
    }
    public void draw(Graphics g)
    {
    	
    	if(growing || shrinking)
        {
            g.drawImage(growFrames[growFrame], (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            return;
        }
        if(!(jumping) && (!(ducking)  && (!(growing)) && (!(shrinking)))) {
            if(moving || vx > 1)
            {
                g.drawImage(animation[direction].nextImage(3), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            }
            else
            {
                g.drawImage(animation[direction].stillImage(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            }   
        }
        if(jumping) {
            g.drawImage(animation[direction].getJump(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
        }
        if(ducking) {
            g.drawImage(animation[direction].getDuck(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
        }
        
        moving = false;
    }
}
