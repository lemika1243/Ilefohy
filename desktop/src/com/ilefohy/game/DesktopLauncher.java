package com.ilefohy.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.ilefohy.game.Ilefohy;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Ilefohy");
		
		int Width = 800;
		int Height = 600;
		
		config.setWindowedMode(Width, Height);
		
		try {
			Ilefohy MyWindow = new Ilefohy(Width, Height);
			Lwjgl3Application Principal_Window = new Lwjgl3Application(MyWindow, config);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
