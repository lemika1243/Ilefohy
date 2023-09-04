package gameplay;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bala extends GameObject{
	int direction=1;
	int speedball=12;

	public Bala(int d,float x, float y) {
		super(x, y-100,100,100);
		direction=d;
		setTextures("bala.png");
		setFrame(1);
	}


	public Bala(boolean flip,float x, float y) {
		super(x, y-100,200,200);
		flipHorizontally=flip;
		setDirection(flip);
		setTextures("bala.png");
	}
	
	@Override
	public void move(Vector<Integer> keys) {}
	
	public void drawMe(float delta) {
        model.begin();
        throughTime+=delta;
        model2d=new Sprite(getTextures());

        // Flip the image horizontally if needed
        if (flipHorizontally) {
            model2d.setFlip(true, false); // Flip horizontally
        } else {
            model2d.setFlip(false, false); // Reset flip
        }

        model.draw(model2d, getX(), getY(), getHeight(), getWidth());
        model.end();
	}
	
	public void move() {
		setX(getX()+getDirection());
	}
	
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
}
