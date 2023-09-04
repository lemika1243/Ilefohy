package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject{
	boolean isMoving=false;
	public Player(float x, float y, float w, float h) {
		super(x, y, w, h);
	}
	@Override
	public void move(Vector<Integer> keys) {
		int mpanisa=0;
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
		}
    	if(mpanisa==0||mpanisa>=2) {
    		isMoving=false;
    	}

    	if(isMoving) {
    		setTextures("Player/Biker_run.png");
    		setFrame(6);
    	}else {
    		setTextures("Player/Biker_idle.png");
    		setFrame(4);
    	}
	}

}
