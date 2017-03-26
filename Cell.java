package com.llisovichok.lessons.bombergame;

/**
 * TODO
 * Created by ALEKSANDER KUDIN on 07.02.2017.
 * Version 1.0
 */
public interface Cell<T> {

    boolean isBomb();

    boolean isOpened();

    boolean isBlown();

    void setBomb(boolean ok);

    void setOpened(boolean ok);

    void setBlown(boolean ok);

    void increaseHintCount();

    void draw(T paint, boolean real, int x, int y);
}
