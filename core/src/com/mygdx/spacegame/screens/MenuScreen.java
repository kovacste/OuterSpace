package com.mygdx.spacegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.spacegame.utilities.ConfigSaveLoad;

import java.util.Random;

/**
 * Created by Eldo on 2016.11.20..
 */

public class MenuScreen extends ScreenAdapter {

    //TODO: positioning, buttons, fonts

    Stage stage;
    Game game;
    Texture background;
    ParticleEffect touchEffect;
    Array<ParticleEffect> effects = new Array<ParticleEffect>();
    String[] welcomeMsgs = new String[]{"Hey!", "Good Day!", "Hiya!", "Welcome!", "Time to shoot!", "Have a nice day!", "Ahoy!", "Hi!", "Greetings!"};

    ImageButton playButton;
    ImageButton shopButton;
    ImageButton optionsButton;
    ImageButton quitButton;

    Label highScore;
    Label currentWave;
    Label welcomeMsg;



    int highScoreInt = 10;
    int currentWaveInt = 1;

    public MenuScreen(Game game){
        this.game = game;
        background = new Texture(Gdx.files.internal("gamebg" + (new Random().nextInt(12) + 1) + ".png"));
        ConfigSaveLoad.load();
        highScoreInt = ConfigSaveLoad.getHighScore();
        currentWaveInt = ConfigSaveLoad.getCurrentWave();
    }

    @Override
    public void render(float delta) {
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(ParticleEffect effect: effects){
            effect.update(delta);
            effect.draw(stage.getBatch());
        }
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();

        if(Gdx.input.justTouched()){
            Vector3 touchPoint = new Vector3();
            stage.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),0));
            effects.add(new ParticleEffect(touchEffect));
            effects.get(effects.size - 1).start();
            effects.get(effects.size - 1).setPosition(touchPoint.x, touchPoint.y);
            background = new Texture("gamebg" + (new Random().nextInt(12) + 1) + ".png");
        }
    }

    @Override
    public void show() {

        stage = new Stage(new StretchViewport(480, 800));
        Gdx.input.setInputProcessor(stage);

        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbutton.png")))),
                                     new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbuttontouched.png")))));
        playButton.setPosition(110,200, Align.center);

        quitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbutton.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbuttontouched.png")))));
        quitButton.setPosition(110,50, Align.center);

        shopButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbutton.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbuttontouched.png")))));
        shopButton.setPosition(110,150, Align.center);

        optionsButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbutton.png")))),
                new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playbuttontouched.png")))));
        optionsButton.setPosition(110,100, Align.center);

        stage.addActor(playButton);
        stage.addActor(quitButton);
        stage.addActor(shopButton);
        stage.addActor(optionsButton);

        playButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                game.setScreen(new GameScreen(game));
            }
});

        quitButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                quitGame();
            }
        });

        optionsButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                game.setScreen(new OptionsScreen(game));
            }
        });

        shopButton.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                game.setScreen(new ShopScreen(game));
            }
        });

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1.5f);
        highScore = new Label("High Score:  " + String.format("%03d", highScoreInt), new Label.LabelStyle(font, new Color(1,1,1,1)));
        currentWave = new Label("Wave: " + String.format("%02d", currentWaveInt), new Label.LabelStyle(font, new Color(1,1,1,1)));
        welcomeMsg = new Label(welcomeMsgs[new Random().nextInt(welcomeMsgs.length)],new Label.LabelStyle(font, new Color(1,1,1,1)));

        table.add(highScore).expandX().padTop(10);
        table.add(currentWave).expandX().padTop(10);
        table.row();
        table.add(welcomeMsg).expandX().padTop(20);
        stage.addActor(table);

        touchEffect = new ParticleEffect();
        touchEffect.load(Gdx.files.internal("effects/toucheffect.txt"), Gdx.files.internal("effects"));
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    private void startGame(){
        game.setScreen(new GameScreen(game));
    }

    private void quitGame(){
        Gdx.app.exit();
    }


}
