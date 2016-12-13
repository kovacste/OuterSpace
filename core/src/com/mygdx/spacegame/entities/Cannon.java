package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.spacegame.IUpdateRender;


public class Cannon implements IUpdateRender{

    private static Vector2 cannonPosition = new Vector2(360,70);
    private static Vector2 cannonEndPosition = new Vector2();
    private float angle = 0;
    private float towerAngle = 0;

    Stage stage;
    public static Texture cannonTexture;
    public static float cannonDamage;


    public  Cannon(Stage stage){
        cannonTexture = new Texture(Gdx.files.internal("lvl1cannon.png"));
        cannonDamage = 50;
        cannonPosition.x -= cannonTexture.getWidth() / 2;
        cannonEndPosition = new Vector2(cannonPosition.x + cannonTexture.getWidth() / 2, cannonPosition.y + cannonTexture.getHeight());
        this.stage = stage;
    }

    @Override
    public void update(float delta) {
        if(Gdx.input.justTouched()){
            shot();
        }
    }

    @Override
    public void render(float delta, Batch batch) {
        batch.draw(new TextureRegion(cannonTexture), cannonPosition.x, cannonPosition.y,cannonTexture.getWidth() / 2, cannonTexture.getHeight() / 3,
                cannonTexture.getWidth(),cannonTexture.getHeight(),1,1,towerAngle);
    }

    private void shot(){
        Vector3 destination = new Vector3();

        stage.getCamera().unproject(destination.set(Gdx.input.getX(),Gdx.input.getY(),0));

        Vector2 dest = new Vector2(destination.x, destination.y);
        Vector2 velocity = new Vector2(dest.x - cannonEndPosition.x, dest.y - cannonEndPosition.y);
        angle = velocity.angle() - 90;
        towerAngle = angle;
        velocity.nor();
        velocity.scl(1000);
        Projectile projectile = new Projectile(cannonEndPosition, cannonDamage, Projectile.ProjectileType.SIMPLE_RED,Projectile.PLAYER_TYPE,angle);
        projectile.setVelocity(velocity);
        WaveHandler.projectiles.add(projectile);
    }
}
