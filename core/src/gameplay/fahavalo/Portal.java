package gameplay.fahavalo;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;

import gameplay.GameObject;
import gameplay.Player;

public class Portal extends GameObject{

	Player player;
	Vector<Fahavalo> fahavalo=new Vector<Fahavalo>();
	int countFahavalo=5;
	float timeBeforeEnemyAppear=0f;
	public Portal(World wo, Ilefohy i, OrthographicCamera cam,Player p) {
		super(wo, i, cam);
		player=p;
		setShape(11, 11);
		setPosition(400, 30);
		defineMe(BodyType.StaticBody);
	}
	
	//GET AND SET FUNCTIONS
	public Vector<Fahavalo> getFahavalo(){
		return fahavalo;
	}
	//END
	
	
	public void handled() {
		if(fahavalo.size()<countFahavalo) {
			timeBeforeEnemyAppear+=Gdx.graphics.getDeltaTime();
			if(timeBeforeEnemyAppear>=4f) {
				Flyer temp=new Flyer(getWorld(), getGame(), player, getCamera());
				temp.setPosition(getBody().getPosition().x-10f, getBody().getPosition().x);
		    	System.out.println(getBody().getPosition().x);
				fahavalo.add(temp);
				timeBeforeEnemyAppear=0f;
			}
		}
	}

	@Override
	public void move(Vector<Integer> keys) {
		
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

}
