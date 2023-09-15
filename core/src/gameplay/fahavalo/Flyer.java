package gameplay.fahavalo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;

import gameplay.Player;

public class Flyer extends Fahavalo{
	int adjust=8;

	public Flyer(World wo, Ilefohy i, Player player,OrthographicCamera cam) {
		super(wo, i, player, cam);
		setDetectionRange(20f);
		setShape(2, 2);
		setWidth(30);setHeight(30);
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
			if(target.x<0) 
				setFlipHorizontally(true);
			else
				setFlipHorizontally(false);
			getBody().setLinearVelocity(target);
		}
	}
	

}
