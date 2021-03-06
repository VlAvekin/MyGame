package com.vladaavekin.Game;

import com.vladaavekin.Game.TileMap.Background;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("MyGame");

        window.setDefaultLookAndFeelDecorated(true);

        window.setContentPane(new GamePanel());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(false);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);


    }

}
