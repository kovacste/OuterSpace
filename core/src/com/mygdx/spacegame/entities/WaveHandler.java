package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.spacegame.IUpdateRender;
import com.mygdx.spacegame.SpaceGame;
import com.mygdx.spacegame.screens.GameScreen;
import com.mygdx.spacegame.utilities.ConfigSaveLoad;

import java.util.ArrayList;

/**
 * Created by Eldo on 2016.11.26..
 */

public class WaveHandler implements IUpdateRender{

    private Rectangle rect = new Rectangle();
    private Rectangle otherRect = new Rectangle();
    private ParticleEffect laserImpactEffect;
    private ParticleEffect rocketImpactEffect;
    private Array<ParticleEffect> effects = new Array<ParticleEffect>();
    private Array<int[]> waves = new Array<int[]>();
    public Array<Enemy> enemies = new Array<Enemy>();
    public static Array<Projectile> projectiles = new Array<Projectile>();
    int currentWave = 1;
    private BitmapFont font = new BitmapFont();


    public enum STATE {
        WAVE, WAVE_START, WAVE_END, DEAD
    }

    private STATE currentState;
    private double startTime;



    private int[] wave1 = new int[]{
            //type Vector2 position  Vector2 destination
              1, 0,   2100, 630, 900,
              1, 0,   1900, 540, 220,
              1, 90,  1700, 450, 800,
              1, 90,  1500, 360, 730,
              1, 720, 2100, 90,  340,
              1, 720, 1900, 180, 420,
              1, 630, 1700, 270, 250,
              1, 630, 1500, 400, 600
    };

    private int[] wave2 = new int[]{
            //type Vector2 position  Vector2 destination
            2, 0,   2100, 630, 900,
            2, 0,   1900, 540, 220,
            2, 90,  1700, 450, 800,
            2, 90,  1500, 360, 730,
            2, 90,  1700, 450, 800,
            2, 720, 2100, 90,  340,
            2, 720, 1900, 180, 420,
            2, 630, 1700, 270, 250,
            2, 720, 1900, 180, 420,
            2, 630, 1500, 400, 600
    };

    private int[] wave3 = new int[]{
            //type Vector2 position  Vector2 destination
            3, 0,   2100, 630, 900,
            3, 0,   1900, 540, 220,
            3, 90,  1700, 450, 800,
            3, 90,  1500, 360, 730,
            3, 720, 2100, 90,  340,
            3, 720, 1900, 180, 420,
            3, 630, 1700, 270, 250,
            3, 630, 1500, 400, 600,

            3, 0, (int) (2100 * 1.2), 630, 900,
            3, 0, (int) (1900 * 1.2), 540, 220,
            3, 90, (int) (1700 * 1.2), 450, 800,
            3, 90, (int) (1500 * 1.2), 360, 730,
            3, 720, (int) (2100 * 1.2), 90,  340,
            3, 720, (int) (1900 * 1.2), 180, 420,
            3, 630, (int) (1700 * 1.2), 270, 250,
            3, 630, (int) (1500 * 1.2), 400, 600
    };

    private int[] wave6 = new int[]{
            //type Vector2 position  Vector2 destination
            3, 0,   2100, 630, 900,
            3, 0,   1900, 540, 220,
            3, 90,  1700, 450, 800,
            3, 90,  1500, 360, 730,
            3, 720, 2100, 90,  340,
            3, 720, 1900, 180, 420,
            3, 630, 1700, 270, 250,
            3, 630, 1500, 400, 600,

            3, 0, (int) (2100 * 1.2), 630, 900,
            3, 0, (int) (1900 * 1.2), 540, 220,
            3, 90, (int) (1700 * 1.2), 450, 800,
            3, 90, (int) (1500 * 1.2), 360, 730,
            3, 720, (int) (2100 * 1.2), 90,  340,
            3, 720, (int) (1900 * 1.2), 180, 420,
            3, 630, (int) (1700 * 1.2), 270, 250,
            3, 630, (int) (1500 * 1.2), 400, 600
    };

    private int[] wave4 = new int[]{
            //type Vector2 position  Vector2 destination
            3, 0,   2100, 630, 900,
            3, 0,   1900, 540, 220,
            3, 90,  1700, 450, 800,
            3, 90,  1500, 360, 730,
            3, 720, 2100, 90,  340,
            3, 720, 1900, 180, 420,
            3, 630, 1700, 270, 250,
            3, 630, 1500, 400, 600,

            3, 0, (int) (2100 * 1.2), 630, 900,
            3, 0, (int) (1900 * 1.2), 540, 220,
            3, 90, (int) (1700 * 1.2), 450, 800,
            3, 90, (int) (1500 * 1.2), 360, 730,
            3, 720, (int) (2100 * 1.2), 90,  340,
            3, 720, (int) (1900 * 1.2), 180, 420,
            3, 630, (int) (1700 * 1.2), 270, 250,
            3, 630, (int) (1500 * 1.2), 400, 600
    };

    private int[] wave5 = new int[]{
            //type Vector2 position  Vector2 destination
            3, 0,   2100, 630, 900,
            3, 0,   1900, 540, 220,
            3, 90,  1700, 450, 800,
            3, 90,  1500, 360, 730,
            3, 720, 2100, 90,  340,
            3, 720, 1900, 180, 420,
            3, 630, 1700, 270, 250,
            3, 630, 1500, 400, 600,

            3, 0, (int) (2100 * 1.2), 630, 900,
            3, 0, (int) (1900 * 1.2), 540, 220,
            3, 90, (int) (1700 * 1.2), 450, 800,
            3, 90, (int) (1500 * 1.2), 360, 730,
            3, 720, (int) (2100 * 1.2), 90,  340,
            3, 720, (int) (1900 * 1.2), 180, 420,
            3, 630, (int) (1700 * 1.2), 270, 250,
            3, 630, (int) (1500 * 1.2), 400, 600
    };


    public WaveHandler(){

        currentWave = ConfigSaveLoad.getCurrentWave();
        currentState = STATE.WAVE_START;
        GameScreen.speed = GameScreen.FIGHT_SPEED;
        startTime = System.currentTimeMillis();
        font.getData().setScale(3);

        for(int i = 0; i< wave1.length; i++){
            wave1[i]*=1f;
        }

        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);
        waves.add(wave4);
        waves.add(wave5);
        waves.add(wave6);

        parseCurrentLevel(currentWave);
        laserImpactEffect = new ParticleEffect();
        laserImpactEffect.load(Gdx.files.internal("effects/laserimpacteffect.txt"), Gdx.files.internal("effects"));
        rocketImpactEffect = new ParticleEffect();
        rocketImpactEffect.load(Gdx.files.internal("effects/rocketimpacteffect.txt"), Gdx.files.internal("effects"));
        rocketImpactEffect.scaleEffect(0.5f);
        SpaceGame.handler.showAds(true);
    }

    private void parseCurrentLevel(int currentWave) {
        Vector2 position = new Vector2();
        Vector2 destination = new Vector2();
        int type = 0;
        int[] array = waves.get(currentWave);
        for(int i = 0; i < array.length;){
            type = array[i++];
            position.set(array[i++], array[i++]);
            destination.set(array[i++], array[i++]);
            enemies.add(new Enemy(position,destination,type));
        }
    }

    public void update(float delta){

        switch (currentState){

            case WAVE_START:
                break;
            case WAVE_END:
                break;
            case WAVE:

                updateEnemies(delta);
                updateProjectiles(delta);
                updateParticles(delta);
                collisionTest();
                waveEndTest();
                removeOffScreenProjectiles();
                removeFinishedParticles();

                break;
            case DEAD:

                break;
        }

    }


    private void waveStart(Batch batch){
        font.draw(batch, "Wave: " + (currentWave + 1), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0, Align.center, false);
    }

    private void waveEnd(Batch batch){
        font.draw(batch, "Wave finished!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0, Align.center, false);
    }

    public void render(float delta, Batch batch){
        double now = System.currentTimeMillis();
        switch (currentState){
            case WAVE_START:
                if(now - startTime > 5000){
                    GameScreen.speed = GameScreen.FIGHT_SPEED;
                    currentState = STATE.WAVE;
                }
                waveStart(batch);
                break;
            case WAVE_END:
                if(now - startTime > 5000){
                    currentState = STATE.WAVE_START;
                    startTime = System.currentTimeMillis();
                    GameScreen.speed = GameScreen.TRAVEL_SPEED;
                }
                waveEnd(batch);
                break;
            case WAVE:

                renderEnemies(delta, batch);
                renderProjectiles(delta, batch);
                renderParticles(delta, batch);

                break;
            case DEAD:


                break;
        }

}

    private void collisionTest(){
        for (Projectile projectile: projectiles) {
            rect = new Rectangle(projectile.getPosition().x,projectile.getPosition().y,projectile.getTexture().getWidth(),projectile.getTexture().getHeight());
            if(projectile.getProjType() == Projectile.PLAYER_TYPE){
                for(Enemy enemy: enemies){
                    otherRect = new Rectangle(enemy.getPosition().x + 20, enemy.getPosition().y + 20, enemy.getShipTexture().getWidth() - 20,enemy.getShipTexture().getHeight() - 20);
                    if(rect.overlaps(otherRect)){
                        projectiles.removeValue(projectile,true);
                        enemy.applyDamage(projectile.getDamage());
                        if(enemy.getHealth() <= 0) {
                            enemies.removeValue(enemy, true);
                        }
                        //enemy killed points and money to player
                        Scene.score += 1;
                        Player.currency += 10 * currentWave;
                        impactEffect(projectile.getType(), projectile.getPosition());
                    }
                }
            }

            if(projectile.getProjType() == Projectile.ENEMY_TYPE){
                if(rect.overlaps(Player.rect)){
                    projectiles.removeValue(projectile,true);
                    //player health --
                    impactEffect(projectile.getType(), projectile.getPosition());
                }
            }
        }
    }

    private void impactEffect(Projectile.ProjectileType type, Vector2 position){
        ParticleEffect effect;
        switch (type){
            case SIMPLE_RED:
                effect = new ParticleEffect(laserImpactEffect);
                break;
            case ROCKET_SMALL:
                effect = new ParticleEffect(rocketImpactEffect);
                break;
            default:
                effect = new ParticleEffect(laserImpactEffect);
        }

        effects.add(effect);
        effects.get(effects.size - 1).start();
        effects.get(effects.size - 1).setPosition(position.x, position.y);
    }

    private void updateEnemies(float delta){
        for(Enemy enemy: enemies){
            enemy.update(delta);
        }
    }

    private void updateProjectiles(float delta){
        for(Projectile projectile: projectiles){
            projectile.update(delta);
        }
    }

    private void updateParticles(float delta){
        for(ParticleEffect effect: effects){
            effect.update(delta);
        }
    }

    private void waveEndTest(){
        if(enemies.size == 0){
            nextWave();
            Scene.wave++;
            currentState = STATE.WAVE_END;
            startTime = System.currentTimeMillis();
            GameScreen.speed = GameScreen.TRAVEL_SPEED;
        }
    }

    private void removeOffScreenProjectiles(){
        for(Projectile projectile: projectiles){
            if (projectile.getPosition().x < - 10 || projectile.getPosition().x > 1500 || projectile.getPosition().y < -10  || projectile.getPosition().y > 1500){
                projectiles.removeValue(projectile, true);
            }
        }
    }

    private void removeFinishedParticles(){
        for(ParticleEffect effect: effects){
            if(effect.isComplete()){
                effects.removeValue(effect, true);
            }
        }
    }
    private void nextWave() {
        currentWave++;
        parseCurrentLevel(currentWave);
    }

    private void renderEnemies(float delta, Batch batch){
        for(Enemy enemy: enemies){
            enemy.render(delta, batch);
        }
    }

    private void renderProjectiles(float delta, Batch batch){
        for (Projectile projectile: projectiles){
            projectile.render(delta, batch);
        }
    }

    private void renderParticles(float delta, Batch batch){
        for(ParticleEffect effect: effects){
            effect.draw(batch);
        }
    }
}
