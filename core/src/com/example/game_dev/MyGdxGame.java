package com.example.game_dev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.game_dev.Constants.Difficulty;


public class MyGdxGame extends Game {

	@Override
	public void create() {
		// TODO: Create IciclesScreen with a Difficulty
		showDifficultyScreen();
	}
	public void showIciclesScreen(Difficulty difficulty) {
		// TODO: Show the Icicles screen with the appropriate difficulty
		setScreen(new IciclesScreen(this, difficulty));
	}
	public void showDifficultyScreen() {
		// TODO: Show the difficulty screen
		setScreen(new DifficultyScreen(this));
	}
}
