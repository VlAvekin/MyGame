package com.vladaavekin.Game.Entity;

import com.vladaavekin.Game.GamePanel;
import com.vladaavekin.Game.TileMap.Tile;
import com.vladaavekin.Game.TileMap.TileMap;

import java.awt.*;

public class MapObject {

    // tile stuff
    protected TileMap tileMap;
    protected  int tileSize;
    protected double xmap;
    protected double ymap;

    // position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    // dimensions
    protected int width;
    protected int height;

    // collision box
    protected int cwidth;
    protected int cheight;

    // collision
    protected int currRow;
    protected int currCol;
    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;

    // animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAttion;
    protected boolean facingRight;

    // movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumpng;
    protected boolean falling;

    // movement attributes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStard;
    protected double stopJumpSpeed;

    // constructor
    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public boolean intersects(MapObject o) {

        Rectangle r1 = getRectangle();

        Rectangle r2 = o.getRectangle();

        return r1.intersects(r2);

    }

    public Rectangle getRectangle() {
        return new Rectangle((int) - cwidth, (int)y - cheight, cwidth, cheight);
    }

    public void calculateCorners(double x, double y) {

        int leftTile = (int)(x - cwidth / 2) / tileSize;
        int rightTile = (int)(x - cwidth / 2 - 1) / tileSize;
        int topTile = (int)(y - cheight / 2) / tileSize;
        int bottomTile = (int)(y - cheight / 2 - 1) / tileSize;

        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);
        int br = tileMap.getType(bottomTile, rightTile);

        topLeft = tl == Tile.BLOCKEN;
        topRight = tr == Tile.BLOCKEN;
        bottomLeft = bl == Tile.BLOCKEN;
        bottomRight = br == Tile.BLOCKEN;


    }

    public void checkTileMapCollision(){

        currCol = (int)x / tileSize;
        currRow = (int)y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        calculateCorners(x, ydest);

        if (dy < 0) {
            if (topRight || topLeft) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;

            } else {
                ytemp += dy;
            }
        }

        if (dy > 0) {
            if (bottomRight || bottomLeft) {
                dy = 0;
                falling = false;
                ytemp = (currRow + 1) * tileSize - cheight / 2;

            } else {
                ytemp += dy;
            }
        }

        calculateCorners(xdest , y);

        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                xtemp = currCol * tileSize + cheight / 2;
            } else {
                xtemp += dx;
            }
        }

        if (dx > 0) {
            if (topRight || bottomRight){
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cheight / 2;
            } else {
                xtemp += dx;
            }
        }

        if (!falling){
            calculateCorners(x, ydest + 1);

            if (!bottomLeft && !bottomRight) {
                falling = true;
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCwidth() {
        return cwidth;
    }

    public int getCheight() {
        return cheight;
    }

    public void setPosition(final double x, final double y) {

        this.x = x;
        this.y = y;

    }

    public void setVector(final double dx, final double dy) {

        this.dx = dx;
        this.dy = dy;

    }

    public void setMapPosition() {

        xmap = tileMap.getx();
        ymap = tileMap.gety();

    }


    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJumpng(boolean jumpng) {
        this.jumpng = jumpng;
    }

    public boolean onScreen() {

        return x + xmap + width < 0 ||
                x + xmap - width > GamePanel.WIDTH ||
                y + ymap + height < 0 ||
                y + ymap - height > GamePanel.HEIGHT;

    }



}
