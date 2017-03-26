package com.llisovichok.lessons.bombergame;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * TODO
 * Created by ALEKSANDER KUDIN on 07.02.2017.
 * Version 1.0
 */
public class CellGUI  implements Cell<Graphics>{

    protected boolean bomb;
    protected boolean opened;
    protected boolean blown;
    protected int hint;

    public CellGUI(final boolean bomb, final boolean opened, final boolean blown){
        this.bomb = bomb;
        this.opened = opened;
        this.blown = blown;
    }

    public boolean isBomb() {
        return (bomb) ? true : false;
    }

    public boolean isOpened() {
        return (opened) ? true : false;
    }

    public boolean isBlown(){return (blown) ? true : false;}

    public void setBomb(boolean ok) {
        if(ok) {
            this.bomb = true;
        }
    }

    public void setOpened(boolean ok) {
        if(ok) {
            this.opened = true;
        }
    }

    public  void setBlown(boolean ok){
        if (ok) this.blown = true;
    }

    public void increaseHintCount() {
        this.hint++;
    }

    public void draw(Graphics paint, boolean real, int x ,int y) {

        Graphics2D g2 = (Graphics2D) paint;
        if(real){
            if(this.isBomb()) {
                Rectangle2D rec = new Rectangle2D.Float(x, y, 50.0F,50.0F);
                g2.setPaint(Color.WHITE);
                g2.fill(rec);
                g2.setPaint(Color.BLACK);
                g2.draw(rec);
                Ellipse2D el = new Ellipse2D.Float((float) (x + 5), (float) (y + 5), 40.0F, 40.0F);
                g2.setPaint(Color.RED);
                g2.fill(el);
            }
            else {
                Rectangle2D rec = new Rectangle2D.Float(x, y, 50.0F,50.0F);
                g2.setPaint(Color.WHITE);
                g2.fill(rec);
                g2.setPaint(Color.BLACK);
                g2.draw(rec);
                if(this.hint != 0 && !this.isBomb()) {
                    g2.setPaint(Color.BLUE);
                    g2.setFont(new Font("Sanserif", Font.BOLD, 32));
                    g2.drawString(Integer.toString(this.hint),x + 15, y + 35);
                }
            }
        }
        else {
            Rectangle2D rec = new Rectangle2D.Float(x, y, 50.0F,50.0F);
            g2.setPaint(Color.BLACK);
            g2.fill(rec);
            g2.setPaint(Color.WHITE);
            g2.draw(rec);
        }
    }
}
