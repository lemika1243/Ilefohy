package ScreenMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ilefohy.game.Ilefohy;

import mpihaino.MpihainoClick;
import mpihaino.MpihainoFanalaHidy;

public class ScreenMenu implements Screen{
	String style;
	Skin skin;
	Stage stage; Ilefohy games;
	int width, height;
	Button play;
	Button quit;
	Label gameOver;
	
	public ScreenMenu(Ilefohy g)
	{
		this.width = g.getWidth(); this.height = g.getHeight(); this.games = g;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		style = new String("small");
		skin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
		gameOver=new Label("Welcome to Ilefohy", skin);
		play = new TextButton("PLAY", skin, "default"); 
		quit = new TextButton("QUIT", skin, "default");
		quit.setPosition(width/2-40, height/2-50); quit.setSize(200, 40); quit.setColor(Color.RED);
		play.setPosition(width/2-40, height/2); play.setSize(200, 40);play.setColor(Color.RED);
		play.addListener(new MpihainoClick(this));
		quit.addListener(new MpihainoClick(this));
		stage.addActor(gameOver);
		stage.addActor(play); stage.addActor(quit);
	}
	@Override
	public void show() {
	}

	public void render(float delta) {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
        stage.draw();
    }

	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.clear();
	}
	
	public Button getPlayButton() {return play;}
	public Button getQuitButton() {return quit;}
	public Stage getStage() {return stage;}
	public Ilefohy getGames() {return games;}
}