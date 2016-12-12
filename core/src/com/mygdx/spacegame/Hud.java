package com.mygdx.spacegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.spacegame.entities.Player;

/**
 * Created by Eldo on 2016.12.04..
 */

public class Hud implements IUpdateRender{
    
    Stage stage;
    //Texture healthBar;
    //Texture healthBarFrame;
    Label scoreLabel;
    Label waveLabel;
    Label currencyLabel;
    BitmapFont font;
    
    private int score = 0;
    private int wave = 0;
    private int health = 0;
    
    public Hud(Stage stage){
        this.stage = stage;
        //healthBar = new Texture(Gdx.files.internal("healthbar.png"));
        //healthBarFrame = new Texture(Gdx.files.internal("healthbarframe.png"));
        font = new BitmapFont();
        font.getData().setScale(2f);
        scoreLabel = new Label("Score: " + String.format("%03d",score), new Label.LabelStyle(font, new Color(1,1,1,1)));
        waveLabel = new Label("Wave: " + String.format("%02d", wave), new Label.LabelStyle(font, new Color(1,1,1,1)));
        currencyLabel = new Label("Currency: " + String.format("%04d", Player.currency), new Label.LabelStyle(font, new Color(1,1,1,1)));
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(scoreLabel).expandX().padTop(140);
        table.add(waveLabel).expandX().padTop(140);
        table.add(currencyLabel).expandX().padTop(140);
        stage.addActor(table);
    }


    @Override
    public void update(float delta) {
        scoreLabel.setText("Score: " +String.format("%03d", score));
        waveLabel.setText("Wave: " +String.format("%02d", wave));
        currencyLabel.setText("Currency: " + String.format("%04d", Player.currency));
        stage.act(delta);
    }

    @Override
    public void render(float delta, Batch batch) {
        batch.end();
        stage.draw();
        batch.begin();
        //batch.draw(healthBar, 200,200, health / 10000 * healthBar.getWidth(), healthBar.getHeight());
       // batch.draw(healthBarFrame, 200, 200);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
