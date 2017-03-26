package com.llisovichok.lessons.bombergame;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * TODO
 * Created by ALEKSANDER KUDIN on 13.02.2017.
 * Version 1.0
 */
public class Gui extends JPanel{

    JFrame f;

    double  width = 30.0;
    double  height = 20.0;

    int x =400/2;
    int y =400/2;

    public int[] xList = {x, (x+4),(x+10), (x+4), x, (x-4), (x-10), (x-4)};
    public int[] yList = {(y-10), (y-4), y, (y+4), (y+10), (y+4), y, (y-4)};

    public static void main(String[] args) {
    Gui gui = new Gui();
    gui.setGUI();

    }

    public void setGUI(){
        f = new JFrame();
        DrawRec dr = new DrawRec();
        f.add(dr);
        f.setSize(400,400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

       for (int k = 0; k < 100; k++){
           if (this.width == f.getWidth()) {
                this.width = 30.0;
                this.height = 20.0;
            }
            this.width++;
            this.height++;

               //this.xList[1] += 1;
               this.xList[2] += 1;
               //this.xList[3] += 1;
               //this.xList[5] -= 1;
               this.xList[6] -= 1;
               //this.xList[7] -= 1;

               this.yList[0] -= 1;
               //this.yList[1] -= 1;
               //this.yList[7] -= 1;
               //this.yList[3] += 1;
               this.yList[4] += 1;
               //this.yList[5] += 1;

            f.repaint();
            try{
                Thread.sleep(10);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private class DrawRec extends JPanel {
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(Color.BLACK);
            g2.fillRect(0, 0, this.getWidth(), this.getHeight());

            //int red = (int) Math.random() * 255;
            //int green = (int) Math.random() * 255;
            //int blue = (int) Math.random() * 255;

            /**Rectangle2D rec = new Rectangle2D.Double(this.getWidth() / 2 - width / 2, this.getHeight() / 2 - height / 2, width, height);
            g2.setPaint(Color.CYAN);
            g2.draw(rec);*/

            Polygon poly = new Polygon(xList, yList, 8);
            g2.setPaint(Color.RED);
            g2.fillPolygon(poly);
        }
    }
}

