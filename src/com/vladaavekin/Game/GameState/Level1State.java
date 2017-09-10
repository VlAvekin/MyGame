package com.vladaavekin.Game.GameState;

import com.vladaavekin.Game.TileMap.Background;
import com.vladaavekin.Game.TileMap.TileMap;

import java.awt.*;

public class Level1State extends GameState {

    private String tileNema = "/Tilesets/begraunt.png";
    private String mapName = "/Maps/level1-1.map";
    private String backgroundName = "/Backgrounds/Fon-1.png";

    private TileMap tileMap;
    private Background bg;

    public Level1State(GameStateManager gsm) {

        this.gsm = gsm;

        init();

    }


    @Override
    public void init() {

        tileMap = new TileMap(28);
        tileMap.loatTiles(tileNema);
        tileMap.loatMap(mapName);
        tileMap.setPosition(0,0);

        bg = new Background(backgroundName, 0.1);


    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // clear screen
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        bg.draw(g);

        // draw tilemap
        tileMap.draw(g);

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
