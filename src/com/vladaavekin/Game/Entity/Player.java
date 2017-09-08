package com.vladaavekin.Game.Entity;

import com.vladaavekin.Game.TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    // player stuff
    private int health;
    private int maxHealth;
    private int file;
    private int maxFile;
    private boolean dead;
    private boolean flinchihg;
    private long flinchTimer;

    // fireball
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    //private ArrayList<FireBall> fireBalls;

    // scratch
    private boolean scratching;
    private int scratchDamage;
    private int scratchRange;

    // gliding
    private boolean glidint;

    // animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
      1
    };

    // animation action
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCTACHING = 6;

    public Player(TileMap tm) {

        super(tm);

        width = 28;
        height = 28;
        cwidth = 20;
        cheight = 20;

        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStard = -4.8;
        stopJumpSpeed = 0.3;

        facingRight = true;

        health = maxHealth = 5;
        file = maxFile = 2500;

        fireCost = 200;
        fireBallDamage = 5;
        //fireBalls = new ArrayList<FireBall;

        scratchDamage = 8;
        scratchRange = 40;

        // lod srpites

        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/pers.png"));

            for (int i = 0; i < 14; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {

                    bi[j] = spritesheet.getSubimage(j  * width, i * health, width, health);

                }

                sprites.add(bi);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getFile() {
        return file;
    }

    public int getMaxFile() {
        return maxFile;
    }


    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public void setScratching(boolean scratching) {
        this.scratching = scratching;
    }

    public void setGlidint(boolean glidint) {
        this.glidint = glidint;
    }

    public void getNextPosition(){

    }

    public void update() {

        // update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        // set animation
        if (scratching) {

            if (currentAction != SCTACHING){
                currentAction = SCTACHING;
                animation.setFrames(sprites.get(SCTACHING));
                animation.setDelay(50);
                width = 60;
                
            }

        }

    }

}
