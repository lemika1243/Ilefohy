package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ilefohy.game.Ilefohy;

public class Stage1 implements Screen {
	SpriteBatch ilefohy;
	Vector2 ilefohyPos;
	Ilefohy game;
	Texture imgIlefohy;
	public Stage1(Ilefohy g) {
		setImgIlefohy(new Texture(Gdx.files.internal("Papango.png")));
		game = g; 
		ilefohy = new SpriteBatch();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ilefohy.begin();
		ilefohy.draw(getImgIlefohy(), 0, 0);
		ilefohy.end();
	}

	public Ilefohy getPrincipalGame() {
		return game;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		getIlefohyImg().dispose();
	}
	public SpriteBatch getIlefohyImg() {
		return ilefohy;
	}
	
	public void getIlefohyImg(SpriteBatch Ilefo) {
		ilefohy = Ilefo;
	}
	
	public Vector2 getIlefohyPos() {
		return ilefohyPos;
	}
	
	public void setIlefohyPos(int x, int y) {
		ilefohyPos = new Vector2(x, y);
	}
	public Texture getImgIlefohy() {
		return imgIlefohy;
	}

	public void setImgIlefohy(Texture text) {
		imgIlefohy = text;
	}
	
	public void getIlefohyImg(Texture Ilefo) {
		imgIlefohy = Ilefo;
	}
}
