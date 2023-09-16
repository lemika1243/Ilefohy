package gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraEffect extends OrthographicCamera {

	Player player;
	
	public CameraEffect(Player p) {
		super(500, 100);
		player=p;
	}

}
