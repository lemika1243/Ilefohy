package gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;

public class Flyer extends Fahavalo{
	int adjust=10;

	public Flyer(World wo, Ilefohy i, Player player,OrthographicCamera cam) {
		super(wo, i, player, cam);
		setDetectionRange(40f);
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
		handleDetection();
		if(playerDetected) {
			setTextures("Enemies/Flyingeye/Attack3.png");
			setFrame(6,0.25f);
			if(target.x<0) 
				setFlipHorizontally(true);
			else
				setFlipHorizontally(false);
			body.setLinearVelocity(target);
		}
	}
	

}
