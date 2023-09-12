package gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;

public class Flyer extends Fahavalo{
	int adjust=10;

	public Flyer(World wo, Ilefohy i, Player player,OrthographicCamera cam) {
		super(wo, i, player, cam);
		setShape(2, 2);
		setSpeed(2);
		width=40;height=40;
		setTextures("Enemies/Flyingeye/Attack3.png");
		setFrame(6,0.25f);
	}
	
	//GET AND SET FUNCTIONS
	public int getAdjustImage() {
		return adjust;
	}
	//END
	
	
	public void move() {
		setTextures("Enemies/Flyingeye/Attack3.png");
		setFrame(6,0.25f);
		float xtarget=player.getPosition().x-getPosition().x;
		float ytarget=player.getPosition().y-getPosition().y;
		Vector2 target=new Vector2(speed*xtarget/4,speed*ytarget/4+4);
		if(target.x<0) 
			setFlipHorizontally(true);
		else
			setFlipHorizontally(false);
		body.setLinearVelocity(target);
	}
	

}
