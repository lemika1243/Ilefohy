package gameplay;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class MyCamera extends OrthographicCamera{
	Player player;
	public MyCamera(Player p,float w,float h) {
		super(w,h);
		player=p;
	}
	public void update() {
		
	}
}
