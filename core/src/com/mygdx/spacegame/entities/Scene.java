package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.spacegame.Hud;
import com.mygdx.spacegame.IUpdateRender;

/**
 * Created by Eldo on 2016.11.26..
 */

public class Scene implements IUpdateRender {

    private Player player;
    private WaveHandler waveHandler;
    private Hud hud;
    private Stage stage;

    public Texture shopIcon = new Texture(Gdx.files.internal("shopicon.png"));
    public ImageButton shopButton;


    public static int score = 0;
    public static int wave = 1;

    public Scene(Stage stage){
        this.stage = stage;
        player = new Player(stage);
        waveHandler = new WaveHandler();
        hud = new Hud(stage);
        shopButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(shopIcon)));
        shopButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                player.highlightTowers();
            }
        });
        shopButton.setPosition(500, 800);
        stage.addActor(shopButton);
    }

    public void update(float delta){
        player.update(delta);
        waveHandler.update(delta);
        hud.setHealth(player.getHealth());
        hud.setScore(score);
        hud.setWave(wave);
        hud.update(delta);
        wave = waveHandler.currentWave;
    }

    public void render(float delta, Batch batch){
        player.render(delta, batch);
        waveHandler.render(delta, batch);
        hud.render(delta, batch);
    }
}
