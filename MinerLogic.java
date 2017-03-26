package com.llisovichok.lessons.bombergame;

/**
 * Created by ALEKSANDER KUDIN on 07.02.2017.
 */
public interface MinerLogic {

    void loadBoard(Cell [][] cells);

    boolean shouldBang (int x, int y);

    boolean finish();
}
