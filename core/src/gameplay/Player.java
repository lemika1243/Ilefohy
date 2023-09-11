package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;


public class Player extends GameObject{
	int hery=3;
	float jumpspeed=1500;
	float speed=0.5f;
	Vector<Bala> bala=new Vector<Bala>();
	int mpanisa=0;
	boolean isMitifitra=false;
	boolean isHurt=false;
	float hurtTime=0f;
	
	float afterHurtDuration=2f;
	
	
	//CONSTRUCTOR
	public Player(World wo,Ilefohy i,OrthographicCamera cam) {
		super(wo,i,cam);
        setShape(8.5f, 7f);
		defineMe(BodyType.DynamicBody,32,32);
	}
	//END
	
	
	
	//SET AND GET FUNCTIONS
	public Vector<Bala> getBala(){
		return bala;
	}
	public void setHurt(boolean a) {
		isHurt=a;
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
		System.out.println(hery);
		handleGrounded();
		handleHurt();
		handleFahafatesana();
		
	}
	public void handleMitifitra(int key) {
		handleMitifitra();
		if(key==0) {
			isMitifitra=true;
			mpanisa++;
		}
		if(isMitifitra&&mpanisa==1) {
			mpanisa++;
			Bala temp=new Bala(world,this);
			bala.add(temp);
		}
		if(mpanisa>8) {
			isMitifitra=false;
			mpanisa=0;
		}
	}


	@Override
	public void mitifitra(int key) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void maty() {
		
	}


	@Override
	public void matyAnimation() {
		setTextures("Player/Biker_death.png");
		setFrame(6,0.25f);
		setLoopAnimation(false);
	}


	@Override
	public void hurt() {
		if(hurtTime>=0.1f&&hurtTime<=0.117f) {
			hery-=1;
		}
		else if(hurtTime>=afterHurtDuration) {
			hurtTime=0f;
			isHurt=false;
		}
		hurtTime+=deltaTime;
	}


	@Override
	public void hurtAnimation() {
		setTextures("Player/Biker_hurt.png");
		setFrame(2,0.25f);
	}
	//END
	
	//UTILITY FUNCTIONS
	public void handleHurt() {
		if(isHurt) {
			hurt();
			hurtAnimation();
		}
	}
	public void jump() {
    	body.setLinearVelocity(new Vector2(0, jumpspeed));
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
	public void handleFahafatesana() {
		if(hery<=0) {
			matyAnimation();
			maty();
		}
	}
	
	//END
}
