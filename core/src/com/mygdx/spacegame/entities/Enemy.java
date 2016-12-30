package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacegame.IUpdateRender;

import java.util.Random;

/**
 * Created by Eldo on 2016.11.20..
 */

public class Enemy implements IUpdateRender{

    private Texture shipTexture;
    private Texture healthBarTexture;
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 destination = new Vector2();
    private Vector2 healthBarPosition = new Vector2();
    private Vector2 projectileStart = new Vector2();
    private boolean attacking = false;
    private double lastShotTime = 0;
    private float damage = 50;
    private float health = 300;
    private float currentHealth;
    private int level = 0;

    public Enemy(Vector2 position, Vector2 destination, int level){
        this.position.set(position);
        this.destination.set(destination);
        health *= level;
        this.level = level;
        calculateVelocity();
		healthBarTexture = new Texture(Gdx.files.internal("healthbartexture.png"));
		currentHealth = health;
        this.shipTexture = setCurrentLevelTexture();
    }

    public void update(float delta){
        position.mulAdd(velocity,delta);
        if(Math.abs(position.x - destination.x)<50){
            destination.set(new Random().nextInt(700), new Random().nextInt(1200));
            calculateVelocity();
            if(!attacking)attacking=true;
        }
        if(attacking){
            attack();
        }
        projectileStart.set(position.x + shipTexture.getWidth() / 2, position.y);
		healthBarPosition.set(position.x, position.y + 10 + shipTexture.getHeight());
    }

    private void attack() {
        double now = System.currentTimeMillis() / 1000;
        if(now - lastShotTime > 1){
            lastShotTime = System.currentTimeMillis() / 1000;
            WaveHandler.projectiles.add(new Projectile(projectileStart, damage, Projectile.ProjectileType.SIMPLE_RED, Projectile.ENEMY_TYPE,0));
        }
    }

    public void render(float delta, Batch batch){
		batch.draw(healthBarTexture, healthBarPosition.x, healthBarPosition.y, healthBarTexture.getWidth() * (health / currentHealth), healthBarTexture.getHeight());
        batch.draw(shipTexture,position.x,position.y);
    }

    private void calculateVelocity() {
        velocity = new Vector2((destination.x - position.x), (destination.y - position.y));
        velocity.nor();
        velocity.scl(100);
    }

    public Texture getShipTexture() {
        return shipTexture;
    }

    public void setShipTexture(Texture shipTexture) {
        this.shipTexture = shipTexture;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getDestination() {
        return destination;
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    public void applyDamage(float damage){
        this.health -= damage;
    }
    public float getHealth(){
        return  health;
    }
	
    public void dispose(){
		shipTexture.dispose();
		healthBarTexture.dispose();
    }

    private Texture setCurrentLevelTexture(){
        String path;
        switch (level){
            case 1:
                path = "level1.png";
                break;
            case 2:
                path = "level2.png";
                break;
            case 3:
                path = "level3.png";
                break;
            case 4:
                path = "level3.png";
                break;
            default:
                path = "level1.png";
                break;
        }
        return new Texture(Gdx.files.internal(path));
    }
}
