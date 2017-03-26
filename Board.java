package com.llisovichok.lessons.bombergame;

import java.awt.*;

/**
 * TODO
 * Created by ALEKSANDER KUDIN on 07.02.2017.
 * Version 1.0
 */
public interface Board {
    void drawBoard(Cell[][] cells);

    void drawCell(int x, int y);

    void drawBang(int x, int y);

    void drawCongratulate();

}
