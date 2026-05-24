import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
public class Mario extends Sprite


{
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = screenSize.width;
	
    final static String[] pose = {"lt", "rt"};
    boolean jumping = false;
    boolean ducking = false;
    boolean isDead = false;
    boolean skidding = false;
    
    // Velocity & physics
    double gravity = .5;
    double topSpeed = 5;
    double jumpForce = -12.0;
    double acceleration = 1;
   
    
    // Variables to handle marios transformations
    Image[] growFrames = new Image[6];
    boolean growing = false;
    boolean shrinking = false;
    int growFrame = 0;
    int growTimer = 0;
    int growFrameDuration = 12; // frames to show each image
    boolean control = true;;

    Image deadMario = Toolkit.getDefaultToolkit().getImage("sm_dies.png");
    
	boolean win;
	boolean walkOff;
    Image winBMario = Toolkit.getDefaultToolkit().getImage("bm_flag_rt.png");
	Image winSMario = Toolkit.getDefaultToolkit().getImage("sm_flag_rt.png");
	Image winMario;
	
	
    int deathBounceTimer = 0;
    final int DEATH_BOUNCE_DURATION = 60; // frames total
  
    
    public Mario(String name, int x, int y, int direction)
    {
        super(name, x, y, 0, 0, 7, direction, pose);
        
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
		if(s.x > 6340) {
			win(s);
		}
		if(win && grounded && walkOff) {
		
			if(s.x < 6525) {
				s.goRT(3);
			}
		}
		 if(s.y > 420 && !(isDead)) {
		        gameOver();
		    }
		    if(isDead) {
		    	if(deathBounceTimer == 0) {
		    		gameOver();
		    	}
		      	s.physics = false;
		        if(deathBounceTimer > 0) {
		            deathBounceTimer--;
		            if(deathBounceTimer > DEATH_BOUNCE_DURATION / 2) {
		                y -= 5; // ascend
		            } else {
		                y += 10; // descend
		            }
		            if(deathBounceTimer == 0) {
		            	deathBounceTimer = -1;
		            }
		        }
		        
		    }

		else {
		
			if(count == 0) {
				if(MarioBrothers.mario.x > 500)	{
				MarioBrothers.mario.grows();
				count++;
				}
			}
	
//			if(count == 1) {
//				if(MarioBrothers.mario.x > 1700) {
//				MarioBrothers.mario.shrinks();
//				count++;
//				}
//			}
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
    }
  
    private void win(Mario s) {
    	gravity = 0.1;
    	control = false;
    	vx = 0;

    	if(s.name.equals("bm")){
    		winMario = winBMario;
    	}
    	else {
    		winMario = winSMario;
    	}
    	win = true;
    	
    	if(grounded) walkOff = true;
    	
    
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

    public void goLT() {
        if(vx > 0) skidding = true;  // moving right but pressing left = skid
        
        if(!(ducking)) {
            if(skidding) {
                vx -= acceleration * .08; // decelerate faster
                if(vx <= 0) skidding = false; // stop skidding once stopped
            } else {
                if(vx > -topSpeed) vx -= acceleration;
                else vx = -topSpeed;
            }
            direction = LT;
            moving = true;
        }
    }
    
    public void goRT() {
        if(vx < 0) skidding = true;  // moving left but pressing right = skid
        
        if(!(ducking)) {
            if(skidding) {
                vx += acceleration * .08;
                if(vx >= 0) skidding = false;
            } else {
                if(vx < topSpeed) vx += acceleration;
                else vx = topSpeed;
            }
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
  
    public void gameOver() {
        if(isDead) {
            control = false;
            deathBounceTimer = DEATH_BOUNCE_DURATION;
        } else {
            MarioBrothers newGame = new MarioBrothers();
        }
    }
    
    
    public void draw(Graphics g)
    {
    	
    
		if(win && !(walkOff)) {
			g.drawImage(winMario, (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
			return;
    	}
    	if(isDead && MarioBrothers.mario.y < 450) {
    		
    		g.drawImage(deadMario, (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
    	    
    	}
    	if(growing || shrinking  && (!(isDead)))
        {
            g.drawImage(growFrames[growFrame], (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            return;
        }
        if(!(jumping) && (!(ducking)  && (!(growing)) && (!(shrinking))) && (!(isDead))) {
            if( vx > 1 || ((moving)  && ((direction == RT && Math.floor(vx) > 0))) || ((moving) && ((direction == LT && Math.ceil(vx) < 0)) ))
            {
                g.drawImage(animation[direction].nextImage(3), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            }
            else if (((direction == LT && Math.floor(vx) > 0) || (direction == RT && Math.ceil(vx) < 0) || skidding) && ((moving)))
            {
            	g.drawImage(animation[direction].getSkid(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            }   
            else {
            	g.drawImage(animation[direction].stillImage(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
            }
        }
        if(jumping  && (!(isDead))) {
            g.drawImage(animation[direction].getJump(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
        }
        if(ducking  && (!(isDead))) {
            g.drawImage(animation[direction].getDuck(), (int)(x-Camera.x), (int)(y-Camera.y), w, h, null);
        }
        
        moving = false;
    }
}
