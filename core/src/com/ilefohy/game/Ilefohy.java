package com.ilefohy.game;

import com.badlogic.gdx.Game;

import onlaunch.AnimationHandler;

public class Ilefohy extends Game {
	//About the Window
	int width, height;
	
	
	//For the animation
	AnimationHandler forPapango;
	
	//Constructor
	public Ilefohy(int W, int H) throws Exception{
		setWidth(W);
		setHeight(H);
	}
		
	@Override
	public void create (){
		setForPapango(new AnimationHandler(this));
		this.setScreen(getForPapango());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		
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