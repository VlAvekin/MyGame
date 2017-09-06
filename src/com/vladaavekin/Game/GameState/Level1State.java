package com.vladaavekin.Game.GameState;

import com.vladaavekin.Game.GamePanel;
import com.vladaavekin.Game.TileMap.TileMap;

import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMep;

    private String tileNema = "/Tilesets/begraunt.png";
    private String mapName = "/Maps/level1-1.map";

    public Level1State(GameStateManager gsm) {

        this.gsm = gsm;

        init();

    }


    @Override
    public void init() {

        tileMep = new TileMap(28);
        tileMep.loatTiles(tileNema);
        tileMep.loatMap(mapName);
        tileMep.setPosition(0,0);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw tilemap
        tileMep.draw(g);

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
