package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.spacegame.IUpdateRender;
import com.mygdx.spacegame.utilities.ConfigSaveLoad;

import java.util.ArrayList;

/**
 * Created by Eldo on 2016.11.26..
 */

public class WaveHandler implements IUpdateRender{

    Rectangle rect = new Rectangle();
    Rectangle otherRect = new Rectangle();

    ParticleEffect laserImpactEffect;
    ParticleEffect rocketImpactEffect;

    Array<ParticleEffect> effects = new Array<ParticleEffect>();
    int currentWave = 1;


    int[] wave1 = new int[]{
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

    int[] wave2 = new int[]{
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

    int[] wave3 = new int[]{
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

    Array<int[]> waves = new Array<int[]>();

    public Array<Enemy> enemies = new Array<Enemy>();

    public static Array<Projectile> projectiles = new Array<Projectile>();

    public WaveHandler(){
        currentWave = ConfigSaveLoad.getCurrentWave();
        for(int i = 0; i< wave1.length; i++){
            wave1[i]*=1f;
        }
        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);
        parseCurrentLevel(currentWave);
        laserImpactEffect = new ParticleEffect();
        laserImpactEffect.load(Gdx.files.internal("effects/laserimpacteffect.txt"), Gdx.files.internal("effects"));
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
            Gdx.app.log("LEVEL:", String.valueOf(type));
        }
    }

    public void update(float delta){
        for(Enemy enemy: enemies){
            enemy.update(delta);
        }
        for(Projectile projectile: projectiles){
            projectile.update(delta);
        }
        collisionTest();
        if(enemies.size == 0){
            nextWave();
            Scene.wave++;
        }
    }

    private void nextWave() {
        currentWave++;
        parseCurrentLevel(currentWave);
    }

    public void render(float delta, Batch batch){
        for(Enemy enemy: enemies){
            enemy.render(delta, batch);
        }
        for (Projectile projectile: projectiles){
            projectile.render(delta, batch);
        }
        for(ParticleEffect effect: effects){
            effect.update(delta);
            effect.draw(batch);
        }
    }

    private void collisionTest(){
        for (Projectile projectile: projectiles) {
            rect = new Rectangle(projectile.position.x,projectile.position.y,projectile.getTexture().getWidth(),projectile.getTexture().getHeight());
            if(projectile.getProjType() == Projectile.PLAYER_TYPE){
                for(Enemy enemy: enemies){
                    otherRect = new Rectangle(enemy.getPosition().x + 20, enemy.getPosition().y + 20, enemy.getShipTexture().getWidth() - 20,enemy.getShipTexture().getHeight() - 20);
                    if(rect.overlaps(otherRect)){
                        projectiles.removeValue(projectile,true);
                        enemy.applyDamage(projectile.damage);
                        if(enemy.getHealth() <= 0) {
                            enemies.removeValue(enemy, true);
                        }
                        //enemy killed points and money to player
                        Scene.score += 1;
                        Player.currency += 10 * currentWave;
                        impactEffect(projectile.type, projectile.position);
                    }
                }
            }

            if(projectile.getProjType() == Projectile.ENEMY_TYPE){
                if(rect.overlaps(Player.rect)){
                    projectiles.removeValue(projectile,true);
                    //player health --
                    impactEffect(projectile.type, projectile.position);
                }
            }
        }
    }

    public void impactEffect(Projectile.ProjectileType type, Vector2 position){
        ParticleEffect effect;
        switch (type){
            case SIMPLE_RED:
                effect = new ParticleEffect(laserImpactEffect);
                break;
            default:
                effect = new ParticleEffect(laserImpactEffect);
        }

        effects.add(effect);
        effects.get(effects.size - 1).start();
        effects.get(effects.size - 1).setPosition(position.x, position.y);
    }

}
