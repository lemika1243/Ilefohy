package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ilefohy.game.Ilefohy;

import ScreenMenu.ScreenMenu;
import gameplay.CameraEffect;
import gameplay.GameObject;
import gameplay.Player;
import mpihaino.MpihainoFanalaHidy;

public class Stage1 implements Screen {
	ScreenMenu menu;
	Ilefohy game;
	Player player;
	MpihainoFanalaHidy fanala;
	CameraEffect camera;
	SpriteBatch batch;
	public Stage1(ScreenMenu m) {
		menu=m;
		game = menu.getGames(); 
		player=new Player(0, 0,100,140);
		player.setSpeed(5);
		fanala=new MpihainoFanalaHidy();
		Gdx.input.setInputProcessor(fanala);
		camera=new CameraEffect(player);
		batch=new SpriteBatch();
	}

@Override
	public void show() {
		menu.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.setProjectionMatrix(camera.combined);
//		camera.position.set(player.getX()+player.getOriginX(), player.getY()+player.getOriginY(), 0);
//		camera.update();
		player.drawMe(batch,delta);
		player.move(fanala.getIndexOfMovement());
		player.mitifitra(fanala.getMouseKey());
	}

	public Ilefohy getPrincipalGame() {
		return game;
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
	}
}
