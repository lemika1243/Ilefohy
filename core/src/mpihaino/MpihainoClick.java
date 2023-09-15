package mpihaino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ScreenMenu.GameOver;
import ScreenMenu.ScreenMenu;
import stages.Stage1;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MpihainoClick extends ClickListener{
	ScreenMenu menu;
	public MpihainoClick(ScreenMenu m) {
		menu=m;
	}
	public void clicked(InputEvent event, float x, float y) {
		TextButton clickedButton = (TextButton) event.getListenerActor();
		if (clickedButton.getText().toString().equals("PLAY")||clickedButton.getText().toString().equals("REPLAY")) {
			menu.getGames().setScreen(new Stage1(menu));
		}else {
	        Gdx.app.exit();
		}
	}
}
