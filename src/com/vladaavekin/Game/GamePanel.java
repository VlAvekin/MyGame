package com.vladaavekin.Game;

import com.vladaavekin.Game.GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

    // dimension
    public static int SCALE = 2;
//    public static final int WIDTH = 640;
//    public static final int HEIGHT = 360;
    private static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int WIDTH = width / 2;
    public static final int HEIGHT = height / 2;

    private static final int wDop = (WIDTH - 640) * 2 + 5;
    private static final int hDop = (HEIGHT - 360) * 2;

    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    // image
    private BufferedImage image;
    private Graphics2D graphics2D;

    // game State Manager
    private GameStateManager gsm;

    public GamePanel(){

        super();

        Dimension dimension = new Dimension(WIDTH * SCALE,
                                            HEIGHT * SCALE);
        setPreferredSize(dimension);

        setFocusable(true);
        requestFocus();

    }

    public void addNotify() {
        super.addNotify();

        if (thread == null){

            thread = new Thread(this);
            addKeyListener(this);
            thread.start();

        }

    }

    private void init(){

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        graphics2D = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();

    }

    private void update(){

        gsm.update();

    }

    private void draw(){

        gsm.draw(graphics2D);

    }

    private void drawToScreen(){ // рисовать на экран

        Graphics graphics = getGraphics();
        graphics.drawImage(image,0, 0, WIDTH * SCALE + wDop, HEIGHT * SCALE + hDop, null);
        graphics.dispose();

    }

    @Override
    public void run() {

        init();

        long start;    //
        long elapsed;  // истекшее
        long wait;     //Подождите

        while (running){

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1_000_000;

            if (wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public void keyTyped(KeyEvent key) {



    }

    @Override
    public void keyPressed(KeyEvent key) {

        gsm.keyPressed(key.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent key) {

        gsm.keyReleased(key.getKeyCode());

    }
}
