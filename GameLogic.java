package com.llisovichok.lessons.bombergame;

/**
 * Created by ALEKSANDER KUDIN on 13.02.2017.
 */
public class GameLogic implements  MinerLogic {

    BoardGUI boardGUI;
    int notBomb;
    int opened;
    int blows;

    public GameLogic(BoardGUI boardGUI){
        this.boardGUI = boardGUI;
        notBomb = calculateNotBomb(this.boardGUI);
    }

    public void loadBoard(Cell[][] cells) {
    }

    public boolean shouldBang(int x, int y) {
        int row = (int)Math.floor(x/boardGUI.PADDING);
        int column = (int)Math.floor(y/boardGUI.PADDING);
        return (this.boardGUI.cells[row][column].isBomb()) ? true : false;
    }

    public boolean finish() {
        return (notBomb == opened) ? true : false;
    }

    public boolean gameOver(){
        return (blows == 2) ? true : false;
    }

    public int calculateNotBomb(BoardGUI bGUI){

        int countNotBomb = 0;
        for (int i = 0; i != bGUI.cells.length; i++){
            for (int k = 0; k != bGUI.cells[0].length; k++){
                if (!bGUI.cells[i][k].isBomb()) countNotBomb++;
            }
        }
        return countNotBomb;
    }
}
