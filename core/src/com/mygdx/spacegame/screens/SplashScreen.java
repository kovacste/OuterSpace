package com.mygdx.spacegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.spacegame.AnimationFactory;


/**
 * Created by Eldo on 2016.11.20..
 */

public class SplashScreen extends ScreenAdapter {

    Game game;

    SpriteBatch batch;
    Texture splashTexture;
    Animation splashAnim;
    boolean playSplash = true;

    Texture logoTexture;
    Animation logoAnimation;
    boolean playLogo = false;

    float stateTime;

    public SplashScreen(Game game){
        this.game = game;
    }

    public void moveToMainMenu(){
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += delta;

        if(playSplash){
            if(splashAnim.isAnimationFinished(stateTime)){
                playSplash = false;
                playLogo = true;
                stateTime = 0;
            }
             batch.draw(splashAnim.getKeyFrame(stateTime), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if(playLogo){
            if(logoAnimation.isAnimationFinished(stateTime)){
                playLogo = false;
            }
            batch.draw(logoAnimation.getKeyFrame(stateTime), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if(!playLogo && !playSplash){
            moveToMainMenu();
        }
        batch.end();
    }

    @Override
    public void show() {
        stateTime = 0;
        batch = new SpriteBatch();

        logoTexture = new Texture(Gdx.files.internal("logotexture.png"));
        splashTexture = new Texture(Gdx.files.internal("splashtexture.png"));

        logoAnimation = AnimationFactory.createAnimation(0.05f, logoTexture, 7, 8);
        logoAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        splashAnim = AnimationFactory.createAnimation(0.05f, splashTexture, 7, 8);
        splashAnim.setPlayMode(Animation.PlayMode.NORMAL);
    }
}
