/*
 * This class is called when the game is first launching
 * It will display The Enterprise image (Papango) in fade in and then fade out
*/

package onlaunch;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimationHandler{
	SpriteBatch PapangoImg;
	float alpha;
	int counter;
	
	//Launching the Animation
	public void fade() throws InterruptedException {
	        TimeUnit.MILLISECONDS.sleep(90);
	        setCounter(getCounter()+1);
	        
	        if(getCounter() < 15) {
	        	setAlpha((float) (getAlpha()+0.1));
	        }
	        else if(getCounter() > 25) {
	        	setAlpha((float) (getAlpha()-0.1));
	        }
	        getPapangoImg().setColor(1, 1, 1, getAlpha());
	}
	
	//Constructor
	public AnimationHandler(SpriteBatch EnterpriseImg) {
		setPapangoImg(EnterpriseImg);
		setAlpha(0);
	}
	
	//Getters
	public SpriteBatch getPapangoImg() {
		return PapangoImg;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public int getCounter() {
		return counter;
	}

	//Setters
	public void setPapangoImg(SpriteBatch papangoImg) {
		PapangoImg = papangoImg;
	}
	
	public void setAlpha(float d) {
		this.alpha = d;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
}
