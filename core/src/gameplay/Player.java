package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;

import ScreenMenu.GameOver;
import ScreenMenu.ScreenMenu;


public class Player extends GameObject{
	int hery=3;
	float jumpspeed=1500;
	Vector<Bala> bala=new Vector<Bala>();
	int mpanisa=0;
	boolean isHurt=false;
	float hurtTime=0f;
	float shootTimer=0f;
	float timeafterDeath=0f;
	boolean isMitifitra=false;
	float canShoot=1.5f;
	
	float afterHurtDuration=2f;
	
	
	//CONSTRUCTOR
	public Player(World wo,Ilefohy i,OrthographicCamera cam) {
		super(wo,i,cam);
        setShape(3.5f, 7f);
        setPosition(32,32);
        setSpeed(150);
		defineMe(BodyType.DynamicBody);
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
    public void drawMe(int adjust) {
        throughTime += Gdx.graphics.getDeltaTime();
        model.setProjectionMatrix(camera.combined);
        if (loopedAnimation) {
            loopAnimation();
        } else {
            finishAnimation();
        }
        // Set the sprite's position to match the Box2D body's position

        if (textures != null)
            this.setRegion(currentFrame);


        model.begin();
        // Flip the image horizontally if needed
        if (flipHorizontally) {
            this.setFlip(true, false); // Flip horizontally
            model.draw(this, body.getPosition().x-3.5f*adjust*shapeW, body.getPosition().y-adjust*shapeH, width, height);
        } else {
            this.setFlip(false, false); // Reset flip
            model.draw(this, body.getPosition().x-1.5f*adjust*shapeW, body.getPosition().y-adjust*shapeH, width, height);
        }
        model.end();
    }

    
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
		handleHurt();
		handleFahafatesana();
		
	}
	public void handleMitifitra(int key) {
		handleMitifitra();
		if(key==0&&!isMitifitra) {
			mpanisa++;
			isMitifitra=true;
			shootTimer=0f;
		}
		else {
			mpanisa=0;
		}
		if(isMitifitra) {
			shootTimer+=Gdx.graphics.getDeltaTime();
			if(mpanisa==1) {
				mpanisa++;
				Bala temp=new Bala(world,this);
				bala.add(temp);
			}
		}
		if(shootTimer>=canShoot)
			isMitifitra=false;
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
		if(hurtTime<=0.01666555f) {
			hery-=1;
		}
		else if(hurtTime>=afterHurtDuration) {
			hurtTime=0f;
			isHurt=false;
		}
		hurtTime+=Gdx.graphics.getDeltaTime();
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
			temp.drawMe(1);
			temp.move(null);
		}
	}
	public void handleFahafatesana() {
		if(hery<=0) {
			timeafterDeath+=Gdx.graphics.getDeltaTime();
			matyAnimation();
			maty();
			if(timeafterDeath>=2f)
				ilefohy.setScreen(new GameOver(ilefohy));
		}
	}
	
	//END
}
