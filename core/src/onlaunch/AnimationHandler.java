/*
 * This class is called when the game is first launching
 * It will display The Enterprise image (Papango) in fade in and then fade out
*/

package onlaunch;

import java.util.concurrent.TimeUnit;
import ScreenMenu.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ilefohy.game.Ilefohy;

public class AnimationHandler implements Screen{
	Ilefohy PrincipalGame;
	SpriteBatch PapangoImg;
	float alpha;
	int counter;
	Texture enterpriseimg;
	
	//Function that will handle the fade in fade out animation
	public void fade() throws Exception{
		TimeUnit.MILLISECONDS.sleep(100);
		
		
		if(getCounter() < 15) {
			setAlpha((float) (getAlpha()+0.1));
			getPapangoImg().setColor(1, 1, 1, (float) (getAlpha()));
		}
		else if(getCounter() > 26 && getCounter() < 50) {
			setAlpha((float) (getAlpha()-0.1));
			getPapangoImg().setColor(1, 1, 1, (float) (getAlpha()));
		}
		
		setCounter(getCounter()+1);		
	}
	
	//Constructor
	public AnimationHandler(Ilefohy PG) {
		setPapangoImg(new SpriteBatch());
		setPrincipalGame(PG);
		setAlpha(0);
		setCounter(0);
		setEnterpriseimg(new Texture(Gdx.files.internal("Papango.png")));
	}
	
	@Override
	public void render(float delta) {
        if(getCounter() == 51) {
        	getPrincipalGame().setScreen(new ScreenMenu(this.getPrincipalGame()));
        	this.dispose();
        }
        else {
    		ScreenUtils.clear(0, 0, 0, 1);
    		
    		getPapangoImg().begin();
    		try {
    			this.fade();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		getPapangoImg().draw(getEnterpriseimg(), getPrincipalGame().getWidth()/4, getPrincipalGame().getHeight()/4, getPrincipalGame().getWidth()/2, getPrincipalGame().getHeight()/2);
    		
    		
            getPapangoImg().end();
        }
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		getPapangoImg().dispose();
		getEnterpriseimg().dispose();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	//Getters
	public Ilefohy getPrincipalGame() {
		return PrincipalGame;
	}
	
	public SpriteBatch getPapangoImg() {
		return PapangoImg;
	}
	
	public float getAlpha() {
		return alpha;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public Texture getEnterpriseimg() {
		return enterpriseimg;
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
	
	public void setPrincipalGame(Ilefohy principalGame) {
		PrincipalGame = principalGame;
	}
	
	public void setEnterpriseimg(Texture enterpriseimg) {
		this.enterpriseimg = enterpriseimg;
	}
}