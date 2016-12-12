package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.spacegame.IUpdateRender;

/**
 * Created by Eldo on 2016.11.20..
 */

public class Player implements IUpdateRender {

    //TODO: assign towers to player render and update them
    //TODO: create player texture
    //TODO: game play game end ...


    public static Rectangle rect;
    Cannon cannon;
    Stage stage;

    //left to right
    public static Tower towerOne;
    public static Tower towerTwo;
    public static Tower towerThree;
    public static Tower towerFour;

    boolean towerOneSet = true;
    boolean towerTwoSet = true;
    boolean towerThreeSet = true;
    boolean towerFourSet = true;

    Texture playerTexture;

    public int health = 10000;
    public static int currency = 0;

    public Player(Stage stage){
        playerTexture = new Texture(Gdx.files.internal("playertexture.png"));
        cannon = new Cannon(stage);
        this.stage = stage;
        rect = new Rectangle(0,0,720,200);
        towerOne = new Tower(new Vector2(20,30),50, Tower.lvl1Tower, stage);
        towerTwo = new Tower(new Vector2(120,30),50, Tower.lvl1Tower, stage);
        towerThree = new Tower(new Vector2(550,30),50, Tower.lvl1Tower, stage);
        towerFour = new Tower(new Vector2(650,30),50, Tower.lvl1Tower, stage);
        towerThree.currentType = Projectile.ProjectileType.SIMPLE_RED;
        towerThree.currentAttackRate = Projectile.simpleProjAttackRate;
    }

    public void update(float delta){
        cannon.update(delta);
        towerOne.update(delta);
        towerTwo.update(delta);
        towerThree.update(delta);
        towerFour.update(delta);
    }

    public void render(float delta, Batch batch){
        batch.draw(playerTexture,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cannon.render(delta, batch);
        towerOne.render(delta, batch);
        towerTwo.render(delta, batch);
        towerThree.render(delta, batch);
        towerFour.render(delta, batch);
    }

    public void setTower(int towerNum, Tower tower){
        switch(towerNum){
            case 1:
                towerOne = tower;
                break;
            case 2:
                towerTwo = tower;
                break;
            case 3:
                towerThree = tower;
                break;
            case 4:
                towerFour = tower;
                break;
            default:
                Gdx.app.log("Failure:", "Towers");
        }
    }

    public Rectangle getBounds(){
        return rect;
    }

    public int getHealth(){
        return health;
    }


}
