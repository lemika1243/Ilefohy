package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;


public class Player extends GameObject{
	int hery=1;
	float jumpspeed=10000000;
	float speed=0.5f;
	Vector<Bala> bala=new Vector<Bala>();
	Ilefohy game;
	int mpanisa=0;
	boolean isMitifitra=false;
	float timebala=0f;
	
	
	//CONSTRUCTOR
	public Player(World wo,Ilefohy i) {
		super(wo,i);
	}
	//END
	
	
	
	//SET AND GET FUNCTIONS
	public Vector<Bala> getBala(){
		return bala;
	}
	//END

	
	
	
	
	//ABSTRACT FUNCTIONS
	@Override
	public void move(Vector<Integer> keys) {
		if(isGrounded) {
	        setTextures("Player/Biker_idle.png");
	        setFrame(4,0.25f);
		}else {
			jumpAnimation();
		}
		for (int i = 0; i < keys.size(); i++) {
			int temp=keys.elementAt(i);
			if((temp==Keys.A&&isGrounded)||(temp==Keys.D&&isGrounded)) {
				movingAnimation();
			}
			if(temp==Keys.A) {
				moveLeft();
			}
			if(temp==Keys.D) {
				moveRight();
			}
			else if(temp==Keys.SPACE&&isGrounded) {
				jump();
			}
		}
		handleGrounded();
		
	}
	public void handleMitifitra(int key) {
		handleMitifitra();
		if(key==0) {
			isMitifitra=true;
		}
		if(isMitifitra) {
			mpanisa++;
			Bala temp=new Bala(world,ilefohy,getX(),getY(),flipHorizontally);
			temp.setScale(12,12);
			temp.setTemps(timebala+deltaTime);
			bala.add(temp);
		}
		if(mpanisa>0) {
			isMitifitra=false;
		}
	}


	@Override
	public void mitifitra(int key) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void maty() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void matyAnimation() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hurt() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hurtAnimation() {
		// TODO Auto-generated method stub
		
	}
	//END
	
	//UTILITY FUNCTIONS
	public void jump() {
    	body.applyLinearImpulse(new Vector2(0,jumpspeed), body.getWorldCenter(), true);
	}
	public void handleGrounded() {

    }
	public void movingAnimation() {
        setTextures("Player/Biker_run.png");
        setFrame(6,0.25f);
	}
	public void jumpAnimation() {
        setTextures("Player/Biker_jump.png");
        setFrame(6,0.25f);
	}
	
	public void handleMitifitra() {
		for (int i = 0; i < bala.size(); i++) {
			Bala temp=bala.elementAt(i);
			temp.drawMe(deltaTime);
			temp.move(null);
		}
	}
	
	//END
}
