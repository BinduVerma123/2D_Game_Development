package com.example.game_dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Players {

    public final String TAG=Players.class.getName();

    Vector2 position;
    Viewport viewport;
    // TODO: Add counter for number of deaths
    int deaths;

    public Players(Viewport viewport) {
        this.viewport = viewport;
        // TODO: Set number of deaths to zero
        deaths = 0;
        init();
    }
    public void init()
    {
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.PLAYER_HEAD_HEIGHT);
    }
    public void update(float delta) {
        // TODO: Use Gdx.input.isKeyPressed() to move the player in the appropriate direction when an arrow key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_MOVEMENT_SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_MOVEMENT_SPEED;
        }
        // TODO: Compute accelerometer input = raw input / (gravity * sensitivity)
        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);

        // TODO: Use the accelerometer input to move the player
        position.x += -delta * accelerometerInput * Constants.PLAYER_MOVEMENT_SPEED;

        ensureInBounds();
    }

    private void ensureInBounds() {
        // TODO: Complete this function to ensure the player is within the viewport
        if (position.x - Constants.PLAYER_HEAD_RADIUS < 0) {
            position.x = Constants.PLAYER_HEAD_RADIUS;
        }
        if (position.x + Constants.PLAYER_HEAD_RADIUS > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - Constants.PLAYER_HEAD_RADIUS;
        }
    }
    public boolean hitByIcicle(Icicles icicles) {
        boolean isHit = false;
        for (Icicle icicle : icicles.icicleList) {
            if (icicle.position.dst(position) < Constants.PLAYER_HEAD_RADIUS) {
                isHit = true;
            }
        }
        if (isHit) {
            deaths += 1;
        }

        return isHit;
    }
    public void render(ShapeRenderer renderer) {


        renderer.setColor(Constants.PLAYER_COLOR);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y, Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_HEAD_SEGMENTS);

        Vector2 torsoTop = new Vector2(position.x, position.y - Constants.PLAYER_HEAD_RADIUS);
        Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 2 * Constants.PLAYER_HEAD_RADIUS);

        renderer.rectLine(torsoTop, torsoBottom, Constants.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x + Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(
                torsoTop.x, torsoTop.y,
                torsoTop.x - Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x + Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);

        renderer.rectLine(
                torsoBottom.x, torsoBottom.y,
                torsoBottom.x - Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
    }
}
