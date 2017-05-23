package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    public static final Texture lvl1Tower = new Texture(Gdx.files.internal("lvl1tower.png"));
    private Vector2 position = new Vector2();
    private float damage = 0;
    private Texture texture;
    private Stage stage;
    private float towerAngle = 0;
    private double lastShot = 0;
    private Projectile projectile;
    private boolean upgradeable = false;
    private ShapeRenderer shapeRenderer;

    public double getCurrentAttackRate() {
        return currentAttackRate;
    }

    public float getDamage() {
        return damage;
    }

    private double currentAttackRate;
    public com.mygdx.spacegame.entities.Projectile.ProjectileType currentType = Projectile.ProjectileType.ROCKET_SMALL;
    private boolean selected = false;


    public Tower(Vector2 position, float damage, Texture texture, Stage stage){
        this.position.set(position);
        this.damage = damage;
        this.texture = texture;
        this.stage = stage;
        projectile = new Projectile(position, currentType, Projectile.PLAYER_TYPE);
        currentAttackRate = projectile.getAttackRate(projectile.getType());
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void update(float delta) {
        if(Gdx.input.justTouched()){
            shot();
        }
    }

    private void shot() {
        double now = System.currentTimeMillis();
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
            Projectile projectile = new Projectile(position, currentType, Projectile.PLAYER_TYPE);
            projectile.setAngle(angle);
            projectile.setVelocity(velocity);
            WaveHandler.projectiles.add(projectile);
            lastShot = System.currentTimeMillis();
        }
    }

    @Override
    public void render(float delta, Batch batch) {
        batch.end();
        if(upgradeable){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(new Color(1,0,0,1));
            shapeRenderer.rect(position.x, position.y, texture.getWidth(), texture.getHeight());
            shapeRenderer.end();
        }
        batch.begin();
        batch.draw(new TextureRegion(texture), position.x, position.y,texture.getWidth() / 2, texture.getHeight() / 3, texture.getWidth(), texture.getHeight(),
                1,1,towerAngle);
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public void setCurrentAttackRate(double currentAttackRate) {
        this.currentAttackRate = currentAttackRate;
    }

    public void highlight(){
        upgradeable = !upgradeable;
    }

    public void upgrade(){
        if(upgradeable){
            damage *= 1.3f;
        }
    }

    public Vector2 getPosition() {
        return position;
    }
}
