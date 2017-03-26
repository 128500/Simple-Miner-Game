package com.llisovichok.lessons.bombergame;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 * Created by ALEKSANDER KUDIN on 13.02.2017.
 * Version 1.0
 */
public class DrawingBang<Graphics> {

     int[] xList;
     int[] yList;

    DrawingBang(final int[] xList, final int[] yList){
        this.xList = xList;
        this.yList = yList;
    }

    public synchronized void drawPoly(final Graphics g) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Graphics2D g2 = (Graphics2D) g;
                Polygon poly = new Polygon(xList, yList, 8);
                g2.setPaint(Color.YELLOW);
                g2.fillPolygon(poly);
            }
        });
    }
}
