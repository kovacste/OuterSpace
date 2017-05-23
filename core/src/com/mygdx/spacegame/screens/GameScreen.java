package com.mygdx.spacegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.spacegame.entities.Scene;

import java.util.Random;

/**
 * Created by Eldo on 2016.11.20..
 */

public class GameScreen extends ScreenAdapter{

    //TODO: implement random background
    //TODO: reserve place for ads on top

    private Stage stage;
    private Scene scene;

    public static boolean running = true;
    public static final float FIGHT_SPEED = -350f;
    public static final float TRAVEL_SPEED = -4000f;



    private Game game;
    private Texture background = new Texture(Gdx.files.internal("gamebg8pixel.png"));
    private Texture background2 = background;
    private float posY = 0;
    public static float speed = TRAVEL_SPEED;



    public GameScreen(Game game){
        this.game = game;
        //background = fetchBackground();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        scene = new Scene(stage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        stage.getBatch().begin();
        stage.getBatch().draw(background,0,posY,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage.getBatch().draw(background2,0,posY - Gdx.graphics.getHeight(),Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(running){
            scene.update(delta);

        }
        scene.render(delta, stage.getBatch());

        stage.act(delta);
        stage.getBatch().end();
        posY += speed * delta;
        if(posY <= 0){
            posY = Gdx.graphics.getHeight();
        }

    }

    @Override
    public void dispose() {

    }


    private void moveToMainMenu(){
        game.setScreen(new MenuScreen(game));
    }

    private void moveToEndScreen(){
        game.setScreen(new EndScreen(game));
    }

    //private Texture fetchBackground(){
      //  return  gameBackgrounds[new Random().nextInt(5) - 1];
   // }


}
