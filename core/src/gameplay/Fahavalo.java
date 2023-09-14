package gameplay;

import java.util.Vector;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ilefohy.game.Ilefohy;

import mpanampy.VectorialCalculus;

public class Fahavalo extends GameObject{
	
	float timeOfReflection;
	Vector<Bala> bala=new Vector<Bala>();
	Player player=null;
	boolean playerDetected=false;
	float detectRange;
	Vector2 target=new Vector2();
	
	//CONSTRUCTORS
	public Fahavalo(World wo, Ilefohy i, Player p,OrthographicCamera cam) {
		super(wo, i, cam);
		player=p;
		setShape(5, 5);
        setPosition(p.getX()+100,32);
		defineMe(BodyType.DynamicBody);
		body.setBullet(true);
	}
	//END
	
	
	
	
	
	//GET AND SET FUNCTIONS
	public void setDetectionRange(float x) {
		detectRange=Math.abs(x);
	}
	public float getDetectionRange() {
		return detectRange;
	}
	//END

	
	
	
	
	
	//ABSTRACT FUNCTIONS
	@Override
	public void move(Vector<Integer> keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mitifitra(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maty() {
		world.destroyBody(body);
	}

	@Override
	public void matyAnimation() {

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
	public void handleDetection() {
		float xtarget=player.getPosition().x-getPosition().x;
		float ytarget=player.getPosition().y-getPosition().y;
		target=new Vector2(speed*xtarget/4,speed*ytarget/4+4);
		Vector2 tempTarget=VectorialCalculus.abs(target);
		if(tempTarget.x<=detectRange&&tempTarget.y<=detectRange) {
			playerDetected=true;
		}else {
			playerDetected=false;
		}
	}
	//END

}
