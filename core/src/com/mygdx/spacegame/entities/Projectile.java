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

    public static enum ProjectileType{
        SIMPLE_RED, SIMPLE_BLUE, SIMPLE_YELLOW, SIMPLE_GREEN, //simply projectiles
        ROCKET_SMALL, ROCKET_MEDIUM, ROCKET_LARGE,            //rockets
        BALL_RED, BALL_BLUE, BALL_YELLOW, BALL_GREEN, RAIL_RED , YELLOW_BEAM         //ball like projectiles
    }

    private static ProjectileType[] types = new ProjectileType[]{SIMPLE_BLUE, SIMPLE_GREEN,SIMPLE_RED,SIMPLE_YELLOW,ROCKET_SMALL,ROCKET_MEDIUM,ROCKET_LARGE,BALL_RED,BALL_BLUE,BALL_YELLOW,BALL_GREEN};

    //Simple projectile red
    public static final Vector2 simpleProjVelocity = new Vector2(0,-700f);
    public static final Texture simpleProjTexture = new Texture(Gdx.files.internal("projectile.png"));
    public static final float simpleProjDamage = 1.0f;
    public static final double simpleProjAttackRate = 200f;

    public static final Vector2 smallRocketVelocity = new Vector2(0, -500f);
    public static final Texture smallRocketTexture = new Texture(Gdx.files.internal("smallrocket.png"));
    public static final float smallRocketDamage = 2.0f;
    public static final double smallRocketAttackRate = 800f;

    public static final Vector2 railRedVelocity = new Vector2(0,0);
    public static final Texture railRedTexture = new Texture(Gdx.files.internal("rail.png"));
    public static final float railRedDamage = 10;
    public static final double railRedAttackRate = 2000f;

    public static final Vector2 yellowBeamVelocity = new Vector2(0, -700);
    public static final Texture yellowBeamTexture = new Texture(Gdx.files.internal("beam.png"));
    public static final float yellowBeamDamage = 1.5f;
    public static final double yellowBeamAttackRate = 300f;



    Vector2 position = new Vector2();
    Vector2 velocity = new Vector2();
    float damage;
    Texture projectileTexture;
    ProjectileType type;
    int projType;
    float angle = 0;
    public double attackRate = 0;


    public Projectile(Vector2 position, float damage, ProjectileType type, int projType, float angle){
        this.position.set(position);
        this.velocity.set(velocity);
        this.damage = damage;
        this.type = type;
        this.projType = projType;
        this.angle = angle;
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
            case YELLOW_BEAM:
                this.projectileTexture = yellowBeamTexture;
                this.velocity.set(yellowBeamVelocity);
                this.damage *= yellowBeamDamage;
                this.attackRate = yellowBeamAttackRate;
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
}
