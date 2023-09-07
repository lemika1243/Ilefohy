package stages;

import javax.swing.Renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ilefohy.game.Ilefohy;

import ScreenMenu.ScreenMenu;
import gameplay.GameObject;
import gameplay.Player;
import mpihaino.MpihainoFanalaHidy;

public class Stage1 implements Screen {
	ScreenMenu menu;
	Ilefohy game;
	Player player;
	MpihainoFanalaHidy fanala;
	OrthographicCamera camera;
	TmxMapLoader tmxLoader;
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer ortho;
	Viewport gameport;
	public Stage1(ScreenMenu m) {
		menu=m;
		game = menu.getGames(); 
		player=new Player(0, 0,100,140);
		player.setSpeed(5);
		fanala=new MpihainoFanalaHidy();
		Gdx.input.setInputProcessor(fanala);
		camera=new OrthographicCamera();
		gameport=new ScreenViewport(camera);
		tmxLoader=new TmxMapLoader();
		tiledMap=tmxLoader.load("level1.tmx");
		ortho=new OrthogonalTiledMapRenderer(tiledMap);
		camera.position.set(gameport.getScreenWidth()/2,gameport.getScreenHeight()/2, 0);
	}

@Override
	public void show() {
		menu.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		player.drawMe(delta);
		player.move(fanala.getIndexOfMovement());
		player.mitifitra(fanala.getMouseKey());
		player.getModel().setProjectionMatrix(camera.combined);
		camera.update();
		camera.position.set(player.getX(), player.getY(), 0);
		ortho.setView(camera);
		ortho.render();
	}

	public Ilefohy getPrincipalGame() {
		return game;
	}
	
	@Override
	public void resize(int width, int height) {
		gameport.update(width, height);
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
