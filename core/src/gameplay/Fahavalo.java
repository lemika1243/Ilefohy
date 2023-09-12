package gameplay;

import java.util.Vector;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.ilefohy.game.Ilefohy;

public class Fahavalo extends GameObject{
	
	float timeOfReflection;
	Vector<Bala> bala=new Vector<Bala>();
	Player player=null;
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

}
