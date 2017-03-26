package com.llisovichok.lessons.bombergame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The main idea was taken at <a href = "http://job4j.ru/courses/java_way_from_student_to_master.html"> job4j.ru/courses/java_way_from_student_to_master</a>
 * The implementation of the methods and most part of the code was written by author
 * Created by ALEKSANDER KUDIN on 08.02.2017.
 */
public class BoardGenerator implements GenerateBoard {
    /**
     * Generates a game board with cells on it. The amount of cells
     * depends on amount of rows and columns that a user chooses on
     * a starting window.
     * @param rows - amount of rows of the game board
     * @param columns - amount of columns of the game board
     * @param bombPercentageAmount - bombs percentage amount; depends of the game's mode
     * @return created game board as a two-dimensional array containing cells in it
     */
    public Cell[][] generate(final int rows, final int columns, final int bombPercentageAmount) {

        /* Creates the board with plain cells */
        Map<Integer, CellGUI> cellList = new HashMap<Integer, CellGUI>();
        for (int i = 0; i < rows*columns; i++){
            cellList.put(i, new CellGUI(false, false, false));
        }

        /* Calculates the amount of bombs according to the amount of the cells*/
        int bombAmount = (rows*columns*bombPercentageAmount/100);

        ArrayList<Integer> bombList = new ArrayList<Integer>();

        /* Generates some random numbers to put the bombs*/
        for(int j = 0; j < bombAmount; j++){
            int temp = (int)(Math.random()*rows*columns);
            if(bombList.contains(temp)){
                if(temp == 0) temp  += 1;
                else temp -= 1;
            }
            bombList.add(temp);
        }
        /* Puts the bombs into the cells*/
        for(Integer integer : bombList){
            cellList.get(integer).setBomb(true);
        }

        Cell[][] cells = new Cell[rows][columns];

        /* Puts the cells from the list to the two-dimensional array*/
        int count = 0;
        for (int i = 0; i != cells.length; i++){
            for(int k = 0; k != cells[0].length; k++){
                cells[i][k] = cellList.get(count);
                count++;
            }
        }

        /* Puts hints around the bombs by increasing the cells' param 'HintCount'*/
        for (int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                if(cells[i][j].isBomb()) {
                    if ((j - 1) >= 0) cells[i][j - 1].increaseHintCount();

                    if ((i + 1) < cells.length && (j - 1) >= 0) cells[i + 1][j - 1].increaseHintCount();

                    if ((i + 1) < cells.length) cells[i + 1][j].increaseHintCount();

                    if ((i + 1) < cells.length && (j +1) < cells[0].length) cells[i + 1][j + 1].increaseHintCount();

                    if ((j +1) < cells[0].length) cells[i][j + 1].increaseHintCount();

                    if ((i -1) >= 0 && (j +1) < cells[0].length) cells[i - 1][j + 1].increaseHintCount();

                    if ((i -1) >= 0) cells[i - 1][j].increaseHintCount();

                    if ((i - 1) >= 0 && (j - 1) >= 0) cells[i - 1][j - 1].increaseHintCount();
                }
            }
        }
        return  cells;
    }
}
