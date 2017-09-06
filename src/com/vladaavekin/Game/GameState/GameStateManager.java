package com.vladaavekin.Game.GameState;


import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class GameStateManager extends GameState {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int LEVEL2STATE = 2;

    public GameStateManager(){

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));

        gameStates.add(new Level1State(this));
    }

    public void setState(int state) {

        currentState = state;
        gameStates.get(currentState).init();

    }

    public void init(){

    }

    public void update() {

        gameStates.get(currentState).update();

    }

    @Override
    public void draw(Graphics2D g) {

        gameStates.get(currentState).draw(g);

    }


    public void keyPressed(int key) {

        gameStates.get(currentState).keyPressed(key);

    }

    public void keyReleased(int key) {

        gameStates.get(currentState).keyReleased(key);

    }

}
