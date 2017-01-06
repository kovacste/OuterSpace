package com.mygdx.spacegame.utilities;

/**
 * Created by Eldo on 2016.11.25..
 */

public class ConfigSaveLoad {

    private static int highScore = 10;
    private static int currentWave = 1;
    private static boolean loaded = false;

    public static int getHighScore(){
        if(loaded){
            return ConfigSaveLoad.highScore;
        } else {
            load();
            return ConfigSaveLoad.highScore;
        }
    }

    public static int getCurrentWave(){
        if(loaded){
            return ConfigSaveLoad.currentWave;
        } else{
            load();
            return ConfigSaveLoad.currentWave;
        }
    }

    public static void setHighScore(int highScore){
        ConfigSaveLoad.highScore = highScore;
    }
    public static void setCurrentWave(int currentWave){
        ConfigSaveLoad.currentWave = currentWave;
    }

    public static void load(){
        //implement load
        highScore = 0;
        currentWave = 0;
        loaded = true;
    }

    public static void save(){
        //implement save
    }
}
