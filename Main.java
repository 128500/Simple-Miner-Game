package com.llisovichok.lessons.bombergame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ALEKSANDER KUDIN on 08.02.2017.
 */
public class Main {

    static StartingPanel startingPanel;
    static GameFrame gameFrame;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){public void run(){

            gameFrame = new GameFrame();
            startingPanel = new StartingPanel(gameFrame);
            gameFrame.add(startingPanel, BorderLayout.CENTER);
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);

        }});

    }
}
