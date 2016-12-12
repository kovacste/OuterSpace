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
import com.mygdx.spacegame.IUpdateRender;
import com.mygdx.spacegame.screens.GameScreen;

/**
 * Created by Eldo on 2016.11.29..
 */

public class Shop implements IUpdateRender{

    Stage stage;
    private Texture shopWindowTexture = new Texture("shopwindowtexture.png");
    private Texture shopIcon = new Texture("shopicon.png");
    private boolean shopOpen = false;

    ImageButton shopIconButton;

    ImageButton towerOneButton;
    ImageButton towerTwoButton;
    ImageButton towerThreeButton;
    ImageButton towerFourButton;



    enum SelectedTower{
        TOWER_ONE, TOWER_TWO, TOWER_THREE, TOWER_FOUR, CANNON, NOTHING
    }

    SelectedTower select = SelectedTower.NOTHING;

    public Shop(Stage stage){
        this.stage = stage;
        shopIconButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(shopIcon)));

        shopIconButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                if(select != SelectedTower.NOTHING){
                    shopOpen = true;
                    GameScreen.running = false;
                }
            }
        });

        towerOneButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                select = SelectedTower.TOWER_ONE;
            }
        });
        towerTwoButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                select = SelectedTower.TOWER_TWO;
            }
        });
        towerThreeButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                select = SelectedTower.TOWER_THREE;
            }
        });
        towerFourButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                select = SelectedTower.TOWER_FOUR;
            }
        });

    }

    public void replaceTower(){

    }

    @Override
    public void update(float delta) {
        //getting update
        //if pressed set select to getSelectedTower
        //if not nothing set open shop true and render shop accordingly
    }

    @Override
    public void render ( float delta, Batch batch){
        if (shopOpen) {
            batch.draw(shopWindowTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

}


