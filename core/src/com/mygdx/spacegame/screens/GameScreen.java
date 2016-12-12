package com.mygdx.spacegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.spacegame.entities.Scene;

import java.util.Random;

/**
 * Created by Eldo on 2016.11.20..
 */

public class GameScreen extends ScreenAdapter{

    //TODO: implement random background
    //TODO: reserve place for ads on top

    Stage stage;
    Scene scene;

    public static boolean running = true;




    Game game;
    Texture background = new Texture(Gdx.files.internal("gamebg8pixel.png"));
    Texture background2 = background;

    float posY = 0;


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



        stage.getBatch().end();
        posY += 0.5f;
        if(posY >= Gdx.graphics.getHeight()){
            posY = 0;
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
