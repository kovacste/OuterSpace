package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.spacegame.IUpdateRender;

/**
 * Created by Eldo on 2016.11.20..
 */

public class Tower implements IUpdateRender{

    //TODO: implement tower class similar to cannon
    //TODO: different kind of tower define them
    //TODO: prepare them to be able to switch towers
    //TODO: AI or player controlled?


    public static final Texture lvl1Tower = new Texture(Gdx.files.internal("lvl1tower.png"));
    private Vector2 position = new Vector2();
    private float damage = 0;
    private Texture texture;
    Stage stage;
    float towerAngle = 0;
    double lastShot = 0;
    double currentAttackRate = Projectile.smallRocketAttackRate;
    public com.mygdx.spacegame.entities.Projectile.ProjectileType currentType =Projectile.ProjectileType.ROCKET_SMALL;


    public Tower(Vector2 position, float damage, Texture texture, Stage stage){
        this.position.set(position);
        this.damage = damage;
        this.texture = texture;
        this.stage = stage;
    }

    @Override
    public void update(float delta) {
        if(Gdx.input.justTouched()){
            shot();
        }
    }

    private void shot() {
        double now = System.currentTimeMillis()/1000;
        if(now - lastShot >= currentAttackRate) {

            float angle = 0;
            Vector3 destination = new Vector3();

            stage.getCamera().unproject(destination.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            Vector2 dest = new Vector2(destination.x, destination.y);
            Vector2 velocity = new Vector2(dest.x - position.x, dest.y - position.y);
            angle = velocity.angle() - 90;
            towerAngle = velocity.angle() - 90;
            velocity.nor();

            velocity.scl(1000);
            Projectile projectile = new Projectile(position, damage, currentType, Projectile.PLAYER_TYPE, angle);
            projectile.setVelocity(velocity);
            WaveHandler.projectiles.add(projectile);

            lastShot = System.currentTimeMillis()/1000;
        }
    }

    @Override
    public void render(float delta, Batch batch) {
        batch.draw(new TextureRegion(texture), position.x, position.y,texture.getWidth() / 2, texture.getHeight() / 3, texture.getWidth(), texture.getHeight(),
                1,1,towerAngle);
    }
}
