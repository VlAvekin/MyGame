package com.vladaavekin.Game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("MyGame");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }

}
