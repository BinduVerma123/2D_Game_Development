package com.example.game_dev;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Icicles {

    public static final String TAG =Icicles.class.getName();
    // TODO: Add an array of icicles and a viewport
    //Array<Icicle> icicleList;
    Viewport viewport;
    // TODO: Add a Difficulty
    Constants.Difficulty difficulty;
    // TODO: Add counter for how many icicles have been dodged
    int iciclesDodged;
    DelayedRemovalArray<Icicle> icicleList;

    public Icicles(Viewport viewport, Constants.Difficulty difficulty) {
       // this.icicleList = icicleList;
        this.difficulty = difficulty;
        this.viewport = viewport;
        init();
    }

    public void init()
    {
       // icicleList = new Array<Icicle>(false, 100);
        icicleList = new DelayedRemovalArray<Icicle>(false, 100);
        iciclesDodged = 0;


    }

    public void update(float delta) {
        // TODO: Use the difficulty's spawn rate
        if (MathUtils.random() < delta * difficulty.spawnRate) {
            Vector2 newIciclePosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            Icicle newIcicle = new Icicle(newIciclePosition);
            icicleList.add(newIcicle);
        }

        for (Icicle icicle : icicleList) {
            icicle.update(delta);
        }
        icicleList.begin();

        // TODO: Remove any icicle completely off the bottom of the screen
        for (int i = 0; i < icicleList.size; i++) {
            if (icicleList.get(i).position.y < -Constants.ICICLE_HEIGHT) {
                iciclesDodged += 1;

                icicleList.removeIndex(i);

            }
        }
        // TODO: End removal session
        icicleList.end();

    }
    public void render(ShapeRenderer renderer) {
        // TODO: Set ShapeRenderer Color
        renderer.setColor(Constants.ICICLE_COLOR);

        // TODO: Render each icicle
        for (Icicle icicle : icicleList) {
            icicle.render(renderer);
        }
    }
}
