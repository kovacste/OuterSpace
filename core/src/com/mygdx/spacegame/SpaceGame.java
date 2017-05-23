package com.mygdx.spacegame;

import com.badlogic.gdx.Game;
import com.mygdx.spacegame.screens.SplashScreen;

public class SpaceGame extends Game {

	public static AdHandler handler;

	public SpaceGame(AdHandler handler){
		SpaceGame.handler = handler;
	}
	@Override
	public void create() {
		this.setScreen(new SplashScreen(this));
	}
}
