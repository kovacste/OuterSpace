package com.mygdx.spacegame;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by Eldo on 2016.11.27..
 */

public interface IUpdateRender {


    public abstract void update(float delta);

    public abstract void render(float delta, Batch batch);
}
