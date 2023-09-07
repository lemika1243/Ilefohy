package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation.Pow;

public class Player extends GameObject{
	boolean isMoving=false;
	int tempbala=0;
	float temphurt=0f;
	float timeDeath=0f;
	float afterHurtDuration=2f;
	float timejump=0.1f;
	int hery=1;
	boolean isHurt=false;
	boolean isGrounded=true;
	float jumpspeed=30;
	Vector<Bala> bala=new Vector<Bala>();
	
	
	//CONSTRUCTOR
	public Player(float x, float y, float w, float h) {
		super(x, y, w, h);
        setTextures("Player/Biker_idle.png");
        setFrame(4,0.25f);
	}
	//END
	
	
	
	//GET AND SET FUNCTIONS
	public void setJumpSpeed(float j) {
		jumpspeed=j;
	}
	//END
	
	
	
	
	//REDIFINITION OF ABSTRACT FUNCTIONS
	@Override
	public void maty() {
		if(hery==0) {
			isDead=true;
		}
	}
	
	public void matyAnimation() {
		setTextures("Player/Biker_death.png");
		setFrame(6,0.25f);
	}

	@Override
	public void hurt() {
		hery-=1;
	}

	@Override
	public void hurtAnimation() {
		setTextures("Player/Biker_hurt.png");
		setFrame(2,0.25f);
	}
	
	@Override
	public void move(Vector<Integer> keys) {
		int mpanisa=0;
    	int tempanisa=0;
    	for (int i = 0; i < keys.size(); i++) {
    		int ind=keys.elementAt(i).intValue();
            if (ind == Keys.D) {
                isMoving=true;
                mpanisa++;
                moveRight();
            }
            else if (ind == Keys.A) {
                moveLeft();
                mpanisa++;
                isMoving=true;
            }
            else if(ind==Keys.F&&tempanisa==0) {
            	isHurt=true;
            	tempanisa++;
            }
            else if(ind==Keys.SPACE) {
            	isGrounded=false;
            }
		}
    	maty();
    	if(mpanisa==0||mpanisa>=2) {
    		isMoving=false;
    	}
    	verifyMovingFlip();
    	verifyHurt();
    	verifyDeath();
    	jumpFunction();
    }
	//END
	
	
	
	
	
	//UTILITY FUNCTIONS
	public void mitifitra(int key) {
        moveBala();
        if(key==0) {
        	Bala temp=new Bala(flipHorizontally, getX(), getY());
        	tempbala++;
    		setTextures("Player/Biker_attack3.png");
    		setFrame(8,0.25f);
        	if(tempbala==1) {
            	bala.add(temp);
        	}
        }
        else {
        	tempbala=0;
        }
	}
	
	public void moveBala() {
		for (int i = 0; i < bala.size(); i++) {
			Bala temp=bala.elementAt(i);
			if(temp.getIsDead()) {
				bala.remove(i);
			}
			else {
				temp.move();
				temp.setTemps(temp.getTemps()+deltaTime);
				temp.drawMe(deltaTime);
			}
		}
	}

	public void verifyMovingFlip() {
		if (isMoving) {
    		setTextures("Player/Biker_run.png");
    		setFrame(6,0.25f);
    	}else {
    		setTextures("Player/Biker_idle.png");
    		setFrame(4,0.25f);
    	}
	}
	
	public void verifyHurt() {
		if(isHurt) {
			if(temphurt<=afterHurtDuration) {
	    		hurtAnimation();
	    		temphurt+=deltaTime;
	    		isHurt=true;
			}
			else {
				temphurt=0;
				hurt();
	    		isHurt=false;
			}
		}
	}
	
	public void verifyDeath() {
		maty();
		if(isDead) {
			timeDeath+=deltaTime;
			matyAnimation();
			if(timeDeath>animation.getAnimationDuration()-1) {
				setLoopAnimation(false);
			}
		}
		else {
			setLoopAnimation(true);
		}
	}
	
	public void jump() {
		setAcceleration(-gravity);
		float equation=((float) (1.1999*-Math.pow(timejump, 2)+timejump))*jumpspeed;
		setY(getY()+equation);
	}
	
	public void jumpFunction() {
		if(!isGrounded) {
			setTextures("Player/Biker_jump.png");
			setFrame(6, 0.25f);
			timejump+=deltaTime;
			jump();
			if(timejump>=animation.getAnimationDuration()-0.27f) {
				isGrounded=true;
				timejump=0;
			}
		}
	}
	
	//END
}
