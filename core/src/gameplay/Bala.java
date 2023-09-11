package gameplay;

import java.util.Vector;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Bala extends GameObject{
	int direction=1;
	int speedball=500;
	float duration;
	float temporary;
	float TafterCollide=0f;
	Player player;

	//CONSTRUCTORS
	public Bala(World wo,Player p) {
		super(wo,p.ilefohy,p.getCamera());
		player=p;
		setShape(2, 2);
		width=10;height=10;
		flipHorizontally=player.isFliped();
		setDirection(player.isFliped());
		setTextures("balaAnimation.png");
		setFrame(1, 0.25f);
		defineMe(BodyType.DynamicBody,player.getBody().getPosition().x+(1*getDirection()),player.getBody().getPosition().y+(7));
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
	
	public void setSpeedBall(int i) {
		speedball=i;
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
	    body.setLinearVelocity(getDirection()*speedball,0);
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
		body.setLinearVelocity(-getDirection()*speedball, body.getLinearVelocity().y);
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
