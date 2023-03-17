package ScreenMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ilefohy.game.Ilefohy;

import stages.Stage1;

public class ScreenMenu implements Screen{
	String style = new String("small");
	Skin skin = new Skin(Gdx.files.external("Desktop/Tetikasa/Ilefohy/assets/skins/default/skin/uiskin.json"));
	Stage stage = new Stage(new ScreenViewport()); Ilefohy games;
	int width, height;
	Button play = new TextButton("PLAY", skin, "default"); Button quit = new TextButton("QUIT", skin, "default");
	
	public ScreenMenu(Ilefohy g) 
	{
		this.width = g.getWidth(); this.height = g.getHeight(); this.games = g;
		quit.setPosition(width/2-40, height/2-50); quit.setSize(200, 40); quit.setColor(Color.RED);
		play.setPosition(width/2-40, height/2); play.setSize(200, 40);play.setColor(Color.RED);
	}
	@Override
	public void show() {
	
	}

	public void render(float delta) {
		initButton();
		Gdx.input.setInputProcessor(stage);stage.draw();
		
	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	public void initButton()
	{
		//QUITTING THE GAME
		quit.addListener(new ClickListener(){
		    public void clicked(InputEvent event, float x, float y){
		        Gdx.app.exit();
		    }
		});
		play.addListener(new ClickListener(){
		    public void clicked(InputEvent event, float x, float y){
		        playing();
		    }
		});
		stage.addActor(play); stage.addActor(quit);
	}
	
	public void playing() {
		this.games.setScreen(new Stage1(games));
		play.setVisible(false);
	}
}