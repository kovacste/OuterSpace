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

    ImageButton towerOneButton;
    ImageButton towerTwoButton;
    ImageButton towerThreeButton;
    ImageButton towerFourButton;
	ImageButton buyButton;
	ImageButton cancelButton;

    enum SelectedTower{
        TOWER_ONE, TOWER_TWO, TOWER_THREE, TOWER_FOUR, CANNON, NOTHING
    }

    SelectedTower select = SelectedTower.NOTHING;

    public Shop(Stage stage){
        this.stage = stage;
   
		/*towerOneButton = new ImageButton();
		towerTwoButton = new ImageButton();
		towerThreeButton = new ImageButton();
		towerFourButton = new ImageButton();
		buyButton = new ImageButton();
		cancelButton = new ImageButton();*/
		
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
		buyButton.addListener(new ActorGestureListener(){
			@Override
			public void tap(InputEvent event, float  x, float y, int count, int button){
				super.tap(event, x, y, count, button);
				//if something is selected for purchase
				//if player have enough money 
				//replace old tower with the selected tower
				//-money
				//close shop
				select = SelectedTower.NOTHING;
			}
		});
		cancelButton.addListener(new ActorGestureListener(){
			@Override
			public void tap(InputEvent event, float x, float y, int count, int button){
				super.tap(event, x, y, count, button);
				//throw shop selection
				//throw tower selection
				//close shop
				select = SelectedTower.NOTHING;
			}
		});
		
		//set button positions on towers
	    towerOneButton.setPosition(0,0);   
		towerTwoButton.setPosition(0,0); 
		towerThreeButton.setPosition(0,0); 
		towerFourButton.setPosition(0,0); 
		
		//find a way to hide and show buttons when shop opened / closed
		buyButton.setPosition(0,0); 
		cancelButton.setPosition(0,0); 

		stage.addActor(towerOneButton);
		stage.addActor(towerTwoButton);
		stage.addActor(towerThreeButton);
		stage.addActor(towerFourButton);
		stage.addActor(buyButton);
		stage.addActor(cancelButton);
		
    }


    @Override
    public void update(float delta) {
        //getting update
        //if pressed set select to getSelectedTower
        //if not nothing set open shop true and render shop accordingly
		stage.act(delta);
    }

    @Override
    public void render ( float delta, Batch batch){
        if (select != SelectedTower.NOTHING) {
            batch.draw(shopWindowTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
		stage.draw();
    }
}

