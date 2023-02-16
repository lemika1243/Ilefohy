package com.ilefohy.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import onlaunch.AnimationHandler;

public class Ilefohy extends Game {
	//About the Window
	int width, height;
	SpriteBatch batch;
	Texture enterpriseimg;
	
	//For the animation
	AnimationHandler forPapango;
	
	//Constructor
	public Ilefohy(int W, int H) throws Exception{
		setWidth(W);
		setHeight(H);
		
	}
		
	@Override
	public void create (){
		batch = new SpriteBatch();
		enterpriseimg = new Texture(Gdx.files.internal("Papango.png"));
		setForPapango(new AnimationHandler(batch));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		
		try {
			getForPapango().fade();
		}catch(Exception e) {e.printStackTrace();}
		
		batch.draw(enterpriseimg, getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	//Getters
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public AnimationHandler getForPapango() {
		return forPapango;
	}
	
	//Setters
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setForPapango(AnimationHandler forPapango) {
		this.forPapango = forPapango;
	}
}
