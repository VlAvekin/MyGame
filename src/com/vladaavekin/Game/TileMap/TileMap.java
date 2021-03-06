package com.vladaavekin.Game.TileMap;

import com.vladaavekin.Game.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    // position
    private double x;
    private double y;

    //
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween;

    // map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // tileset
    private BufferedImage tileset;
    private int numTilesAcrossWidth;
    private int numTilesAcrossHeight;
    private Tile[][] tiles;

    // drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize){
        this.tileSize = tileSize;
        numRowsToDraw = ( GamePanel.HEIGHT * GamePanel.SCALE) / tileSize + 2;
        numColsToDraw = ( GamePanel.WIDTH  * GamePanel.SCALE) / tileSize + 2;

        tween = 0.07;
    }


    public void loatTiles(String s){

        try {
            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            numTilesAcrossWidth = tileset.getWidth() / tileSize;
            numTilesAcrossHeight = tileset.getHeight() / tileSize;


            tiles = new Tile[numTilesAcrossWidth][numTilesAcrossHeight];


            BufferedImage subimage;

            for (int i = 0; i < numTilesAcrossWidth; i++) {

                for (int j = 0; j < numTilesAcrossHeight; j++) {

                    subimage = tileset.getSubimage( i * tileSize, j * tileSize, tileSize , tileSize);

                    tiles[j][i] = new Tile(subimage, Tile.NORMAL);

                }


//                subimage = tileset.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
//
//                tiles[1][i] = new Tile(subimage, Tile.BLOCKEN);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loatMap(String s){

        try {

            InputStream in = getClass().getResourceAsStream(s);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());

            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            String delims = "\\s+";

            for (int row = 0; row < numRows; row++) {

                String line = br.readLine();
                String[] tokens = line.split(delims);

                for (int col = 0; col < numCols; col++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  int getNumTilesAcross()  {
        return  numCols * numRows;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getx() {
        return (int)x;
    }

    public int gety() {
        return (int)y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col) {

        int rc = map[row][col];
        int r = rc / numTilesAcrossWidth;
        int c = rc % numTilesAcrossHeight;

        return tiles[r][c].getType();

    }

    public void setPosition(double x, double y) {

        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int) - this.x / tileSize;
        rowOffset = (int) - this.y / tileSize;

    }

    private void fixBounds(){

        if (x < xmin) x = xmin;
        if (y < ymin) y = xmin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = xmax;

    }

    public void draw(Graphics2D g) {

        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

            if (row >= numRows) break;

            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

                if(col >= numCols) break;

                if(map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numTilesAcrossWidth;
                int c = rc % numTilesAcrossHeight;

                g.drawImage(tiles[r][c].getImage(),
                        (int)x + col * tileSize,
                        (int)y + row * tileSize,
                        null);



            }

        }

    }

}
