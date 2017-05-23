package com.mygdx.spacegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacegame.IUpdateRender;

import java.util.Random;

import static com.mygdx.spacegame.entities.Projectile.ProjectileType.*;


public class Projectile implements IUpdateRender {

    public static final int ENEMY_TYPE = 0;
    public static final int PLAYER_TYPE = 1;

    public Vector2 getPosition() {
        return position;
    }

    public float getDamage() {
        return damage;
    }

    public enum ProjectileType{
        SIMPLE_RED, SIMPLE_BLUE, SIMPLE_YELLOW, SIMPLE_GREEN, DOUBLE_GUN, //simply projectiles
        ROCKET_SMALL, ROCKET_MEDIUM, ROCKET_LARGE,            //rockets
        BALL_RED, BALL_BLUE, BALL_YELLOW, BALL_GREEN, RAIL_RED , YELLOW_BEAM         //ball like projectiles
    }

    private static ProjectileType[] types = new ProjectileType[]{SIMPLE_BLUE, DOUBLE_GUN, SIMPLE_GREEN,SIMPLE_RED,SIMPLE_YELLOW,ROCKET_SMALL,ROCKET_MEDIUM,ROCKET_LARGE,BALL_RED,BALL_BLUE,BALL_YELLOW,BALL_GREEN};

    //Simple projectile red
    public static final Vector2 simpleProjVelocity = new Vector2(0,-700f);
    public static final Texture simpleProjTexture = new Texture(Gdx.files.internal("projectile.png"));
    public static final float simpleProjDamage = 100.0f;
    public static final double simpleProjAttackRate = 200f;

    public static final Vector2 smallRocketVelocity = new Vector2(0, -500f);
    public static final Texture smallRocketTexture = new Texture(Gdx.files.internal("smallrocket.png"));
    public static final float smallRocketDamage = 200.0f;
    public static final double smallRocketAttackRate = 800f;

    public static final Vector2 railRedVelocity = new Vector2(0,0);
    public static final Texture railRedTexture = new Texture(Gdx.files.internal("rail.png"));
    public static final float railRedDamage = 1000;
    public static final double railRedAttackRate = 2000f;

    public static final Vector2 yellowBeamVelocity = new Vector2(0, -700);
    public static final Texture yellowBeamTexture = new Texture(Gdx.files.internal("beam.png"));
    public static final float yellowBeamDamage = 100.5f;
    public static final double yellowBeamAttackRate = 300f;

    public static final Vector2 doubleGunProjVelocity = new Vector2(0, -700);
    public static final Texture doubleGunProjTexture = new Texture(Gdx.files.internal("doublegunproj.png"));
    public static final float doubleGunProjDamage = 100.5f;
    public static final double doubleGunAttackRate = 500f;


    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private float damage = 1;
    private Texture projectileTexture;
    private ProjectileType type;
    private int projType;
    private float angle = 0;
    private double attackRate = 0;


    public Projectile(Vector2 position, ProjectileType type, int projType){
        this.position.set(position);
        this.velocity.set(velocity);
        this.type = type;
        this.projType = projType;
        createProjectile();
    }


    @Override
    public void update(float delta) {
        position.mulAdd(velocity, delta);
    }

    @Override
    public void render(float delta, Batch batch) {

        batch.draw(new TextureRegion(projectileTexture), position.x, position.y,projectileTexture.getWidth() / 2, projectileTexture.getHeight() / 2,
                projectileTexture.getWidth(),projectileTexture.getHeight(),1,1,angle);
    }

    private void createProjectile(){
        switch (type){
            case SIMPLE_RED:
                this.projectileTexture = simpleProjTexture;
                this.velocity.set(simpleProjVelocity);
                this.damage *= simpleProjDamage;
                this.attackRate = simpleProjAttackRate;
                break;
            case ROCKET_SMALL:
                this.projectileTexture = smallRocketTexture;
                this.velocity.set(smallRocketVelocity);
                this.damage *= smallRocketDamage;
                this.attackRate = smallRocketAttackRate;
                break;
            case RAIL_RED:
                this.projectileTexture = railRedTexture;
                this.velocity.set(railRedVelocity);
                this.damage *= railRedDamage;
                this.attackRate = railRedAttackRate;
                break;
            case YELLOW_BEAM:
                this.projectileTexture = yellowBeamTexture;
                this.velocity.set(yellowBeamVelocity);
                this.damage *= yellowBeamDamage;
                this.attackRate = yellowBeamAttackRate;
                break;
            case DOUBLE_GUN:
                this.projectileTexture = doubleGunProjTexture;
                this.velocity.set(doubleGunProjVelocity);
                this.damage *= doubleGunProjDamage;
                this.attackRate = doubleGunAttackRate;
                break;
            default:
                break;
        }
    }

    public ProjectileType getRandomProjectile(){
        return types[new Random().nextInt(types.length) - 1];
    }

    public int getProjType(){
        return projType;
    }

    public void setVelocity(Vector2 velocity){
        this.velocity.set(velocity);
    }

    public Texture getTexture(){
        return projectileTexture;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public double getAttackRate(ProjectileType type){
        switch (type){
            case SIMPLE_RED:
                return simpleProjAttackRate;
            default:
                return simpleProjAttackRate;
        }
    }

    public ProjectileType getType(){
        return type;
    }
}
