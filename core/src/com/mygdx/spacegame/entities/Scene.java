package com.mygdx.spacegame.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.spacegame.Hud;
import com.mygdx.spacegame.IUpdateRender;

/**
 * Created by Eldo on 2016.11.26..
 */

public class Scene implements IUpdateRender {

    //TODO: implement a hud class and stuff it here by gathering informations of player stage and wavehandler

    //collecting everything that involves in game play to handle them together
    //this called in the screen class

    Player player;           //player instance player - cannon - tower - shop
    WaveHandler waveHandler; //handling the waves enemy - projectile
    Hud hud;
    Stage stage;

    public static int score = 0;
    public static int wave = 1;

    public Scene(Stage stage){
        this.stage = stage;
        player = new Player(stage);
        waveHandler = new WaveHandler();
        hud = new Hud(stage);
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
