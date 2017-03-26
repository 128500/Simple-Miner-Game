package com.llisovichok.lessons.bombergame;

import java.awt.*;

/**
 * TODO
 * Created by ALEKSANDER KUDIN on 07.02.2017.
 * Version 1.0
 */
public interface GenerateBoard {

    Cell<Graphics> [][] generate(int rows, int columns, int bombPercentAmount);
}
