package ScreenMenu;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.ilefohy.game.Ilefohy;


public class GameOver extends ScreenMenu{
	public GameOver(Ilefohy g)
	{
		super(g);
		gameOver=new Label("YOU DIED!!!!!", skin);
	}
}