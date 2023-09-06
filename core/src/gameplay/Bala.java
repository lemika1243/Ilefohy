package gameplay;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bala extends GameObject{
	int direction=1;
	int speedball=12;
	float duration;
	float temporary;

	//CONSTRUCTORS
	public Bala(boolean flip,float x, float y) {
		super(x, y-100,200,200);
		flipHorizontally=flip;
		setDirection(flip);
		setTextures("balaAnimation.png");
		setFrame(4,0.25f);
		temporary=0f;
		duration=1;
	}
	//END
	
	
	
	
	
	//GET AND SET FUNCTIONS
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int i) {
		direction=i;
	}
	
	public void setSpeedBall(int i) {
		speedball=i;
	}
	
	public void setDirection(boolean flip) {
		if(flip) {
			direction=-speedball;
		}else {
			direction=speedball;
		}
	}
	
	public void setDuration(float d) {
		duration=d;
	}
	
	public float getDuration() {
		return duration;
	}
	
	public void setTemps(float t) {
		temporary=t;
	}
	
	public float getTemps() {
		return temporary;
	}
	
	public boolean getIsDead() {
		return isDead;
	}

	//END
	
	
	
	
	
	
	//REDEFINITION OF ABSTRACT FUNCTIONS
	
	@Override
	public void move(Vector<Integer> keys) {}
	
	@Override
	public void mitifitra(int key) {
	}

	@Override
	public void maty() {
		setIsDead(true);
	}
	
	public void matyAnimation() {
		setTextures("balaPotika.png");
		setFrame(5,25);
		setX(getX()-(getDirection()));
	}
	@Override
	public void hurt() {
	}

	@Override
	public void hurtAnimation() {
	}
	//END
	
	
	
	
	
	//UTILITY FUNCTIONS
	public void move() {
		setX(getX()+getDirection());
		if(temporary>=getDuration()) {
			matyAnimation();
		}
		if(temporary>=getDuration()+0.25f) {
			maty();
		}
	}

	//END
}
