package gameplay;

import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Bala extends GameObject{
	int direction=1;
	float duration;
	float temporary;
	float TafterCollide=0f;
	Player player;

	//CONSTRUCTORS
	public Bala(World wo,Player p) {
		super(wo,p.ilefohy,p.getCamera());
		setSpeed(1000);
		player=p;
		setShape(2, 2);
		width=10;height=10;
		flipHorizontally=player.isFliped();
		setDirection(player.isFliped());
		setTextures("balaAnimation.png");
		setFrame(1, 0.25f);
		setPosition(player.getBody().getPosition().x+(16*getDirection()),player.getBody().getPosition().y+(7));
		defineMe(BodyType.DynamicBody);
		body.setFixedRotation(false);
		body.setBullet(true);
	}
	//END
	
	
	
	
	
	//GET AND SET FUNCTIONS
	public void setAfterCollide(float a) {
		TafterCollide=a;
	}
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int i) {
		direction=i;
	}
	
	public void setDirection(boolean flip) {
		if(flip) {
			direction=-1;
		}else {
			direction=1;
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
	public void move(Vector<Integer> keys) {
	    body.applyLinearImpulse(new Vector2(getDirection()*speed,0),body.getWorldCenter(),true);
	}

	
	@Override
	public void mitifitra(int key) {
	}

	@Override
	public void maty() {
		world.destroyBody(body);
	}
	
	public void matyAnimation() {
		setTextures("balaPotika.png");
		setFrame(5,0.4f);
		body.setLinearVelocity(-getDirection()*speed, body.getLinearVelocity().y);
	}
	@Override
	public void hurt() {
	}

	@Override
	public void hurtAnimation() {
	}
	//END
	
	
	
	
	
	//UTILITY FUNCTIONS
	//END
}
