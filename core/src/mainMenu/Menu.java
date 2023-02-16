package mainMenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.*;

public class Menu extends Game{
	int height, width;
	ButtonStyle style = new ButtonStyle();
	public Menu(int H, int W) 
	{
		Button playButton = new Button(style);
		playButton.setName("PLAY");
		Button quitButton = new Button(style);
		quitButton.setName("QUIT");
	}
	@Override
	public void create() {}
}
