package com.example.game_dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.game_dev.Constants.Difficulty;

public class IciclesScreen implements Screen {
    public static final String TAG = IciclesScreen.class.getName();

    MyGdxGame game;
    ExtendViewport icilesViewport;
    ShapeRenderer renderer;
    Icicles icicles;
    Players players;
    // TODO: Add ScreenViewport for HUD
    ScreenViewport hudViewport;

    // TODO: Add SpriteBatch
    SpriteBatch batch;

    // TODO: Add BitmapFont
    BitmapFont font;
    // TODO: Add int to hold the top score
    int topScore;
    Difficulty difficulty;

    public IciclesScreen(MyGdxGame game, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.game = game;

    }

    @Override
    public void show() {

        icilesViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        //icicle=new Icicle(new Vector2(Constants.WORLD_SIZE/2,Constants.WORLD_SIZE/2));
        players = new Players(icilesViewport);
        icicles = new Icicles(icilesViewport, difficulty);
        // TODO: Initialize icicles
        // TODO: Initialize the HUD viewport
        hudViewport = new ScreenViewport();

        // TODO: Initialize the SpriteBatch
        batch = new SpriteBatch();

        // TODO: Initialize the BitmapFont
        font = new BitmapFont();

        // TODO: Give the font a linear TextureFilter
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // TODO: Set top score to zero
        topScore = 0;

    }

    @Override
    public void render(float delta) {
        players.update(delta);
        icicles.update(delta);
        // TODO: Check if the player was hit by an icicle. If so, reset the icicles.
        if (players.hitByIcicle(icicles)) {
            icicles.init();
        }

        icilesViewport.apply(true);
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(icilesViewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        icicles.render(renderer);
        players.render(renderer);
        renderer.end();
        // TODO: Set the top score to max(topScore, iciclesDodges)
        topScore = Math.max(topScore, icicles.iciclesDodged);

        // TODO: Apply the HUD viewport
        hudViewport.apply();

        // TODO: Set the SpriteBatch's projection matrix
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        // TODO: Begin the SpriteBatch
        batch.begin();

        // TODO: Draw the number of player deaths in the top left
        font.draw(batch, "Deaths: " + players.deaths,
                Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN);

        // TODO: Draw the score and top score in the top right
        font.draw(batch, "Score: " + icicles.iciclesDodged + "\nTop Score: " + topScore,
                hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);

        // TODO: End the SpriteBatch
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        icilesViewport.update(width, height, true);
        players.init();
        icicles.init();
        // TODO: Update HUD viewport
        hudViewport.update(width, height, true);

        // TODO: Set font scale to min(width, height) / reference screen size
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();
        batch.dispose();

    }

    @Override
    public void dispose() {
        //renderer.dispose();
        // TODO: Dispose of the SpriteBatch and font

        //font.dispose();

    }

    @Override
public boolean touchdown(int screenX,int screenY,int pointer,int button)
{
    game.showDifficultyScreen();
    return true;
}
  /*  @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO: Tell IciclesGame to show the difficulty screen
        game.showDifficultyScreen();
        return true;
    }*/
}
