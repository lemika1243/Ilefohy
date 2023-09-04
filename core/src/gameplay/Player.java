package gameplay;

import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends GameObject{
	boolean isMoving=false;
	int tempbala=0;
	float tempmitifitra=0f;
	Vector<Bala> bala=new Vector<Bala>();
	
	public Player(float x, float y, float w, float h) {
		super(x, y, w, h);
        setTextures("Player/Biker_idle.png");
        setFrame(4);
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
	
	public void mitifitra(int key) {
        if(key==0) {
        	Bala temp=new Bala(flipHorizontally, getX(), getY());
        	tempbala++;
    		setTextures("Player/Biker_attack3.png");
    		setFrame(8);
        	if(tempbala==1) {
            	bala.add(temp);
        	}
        }
        else {
        	tempbala=0;
        }
        moveBala();
	}
	
	public void moveBala() {
		for (int i = 0; i < bala.size(); i++) {
			Bala temp=bala.elementAt(i);
			temp.move();
			temp.drawMe(throughTime);
		}
	}

}
