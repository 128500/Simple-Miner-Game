package com.llisovichok.lessons.bombergame;

import javax.swing.*;
import java.awt.*;

/**
 **The main idea was taken at <a href = "http://job4j.ru/courses/java_way_from_student_to_master.html"> job4j.ru/courses/java_way_from_student_to_master</a>
 * The implementation of the methods and most part of the code was written by author
 * Created by ALEKSANDER KUDIN on 07.02.2017.
 */
public class BoardGUI extends JPanel implements Board {

    GameLogic gl;
    BoardGenerator bg;
    Cell[][] cells;

    JPanel congratPanel;

    /* Dimensions of the main frame*/
    int height;
    int width;

    /* Percentage amount of bombs on the board*/
    int bombPercentageAmount;

    /* The size of the cell's sides*/
    static final int PADDING = 50;

    BoardGUI(final int rows, final int columns, final int bombPercentageAmount){

        this.bombPercentageAmount = bombPercentageAmount;

        bg = new BoardGenerator();
        this.cells = bg.generate(rows, columns, this.bombPercentageAmount);

        gl = new GameLogic(this);

        this.height = (cells.length * PADDING) + 73; //73 - pixels are needed for correct visualisation of the main frame
        this.width = (cells[0].length * PADDING)+ 9; //9 - pixels are needed for correct visualisation of the main frame

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        this.addMouseListener(new Action(this));
    }

    /**
     * Draws cells depending on what they contain:
     * a bomb, a hint or a plain cell
     * @param graphics graphics to paint
     */
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if (this.cells != null){
            for (int i = 0; i != cells.length; i++) {
                for (int k = 0; k != cells[0].length; k++) {
                    if(this.cells[i][k].isOpened()){
                        this.cells[i][k].draw(graphics, true, (i * PADDING), (k * PADDING));
                        if (this.gl.shouldBang((i*PADDING+1),(k*PADDING+1)) && !this.cells[i][k].isBlown()) {
                             this.drawBang(i*PADDING + PADDING/2,k*PADDING + PADDING/2);
                        }
                    }
                    else {
                        this.cells[i][k].draw(graphics, false, (i * PADDING), (k * PADDING));
                    }
                }
            }
        }
    }

    /**
     * Repaints board
     * @param cells cells to repaint
     */
    public void drawBoard(Cell[][] cells) {
        this.cells = cells;
        this.repaint();
    }

    /**
     * Draws the cell at the area the mouse was clicked
     * @param x 'x' coordinate at witch the mouse was clicked
     * @param y 'y' coordinate at witch the mouse was clicked
     */
    public void drawCell(int x, int y) {

        this.findCell(x, y).setOpened(true);
        this.gl.opened++;
        this.repaint();
    }

    /**
     * Draws a bang if the cell contains the bomb
     * @param x 'x' coordinate at witch the mouse was clicked
     * @param y 'y' coordinate at witch the mouse was clicked
     */
    public void drawBang(final int x, final int y) {

        int[] xList = new int[8];
        int[] yList = new int[8];

       xList[0] = x;
       xList[1] = x+8;
       xList[2] = x+40;
       xList[3] = x+8;
       xList[4] = x;
       xList[5] = x-8;
       xList[6] = x-40;
       xList[7] = x-8;

       yList[0] = y-40;
       yList[1] = y-8;
       yList[2] = y;
       yList[3] = y+8;
       yList[4] = y+40;
       yList[5] = y+8;
       yList[6] = y;
       yList[7] = y-8;

       DrawingBang drawingBang = new DrawingBang(xList, yList);
       drawingBang.drawPoly(this.getGraphics());

       this.findCell(x,y).setBlown(true);
    }

    /**
     * Draws the congratulate sign
     */
    public void drawCongratulate() {

       JTextField congratulate = new JTextField("CONGRATULATIONS!");
       congratulate.setFont(new Font("sanserif", Font.BOLD, 32));
       congratulate.setForeground(Color.CYAN);
       congratulate.setBackground(Color.GRAY);
       congratulate.setFocusable(false);
       congratPanel = new JPanel();
       congratPanel.setBackground(Color.GRAY);
       congratPanel.add(congratulate, BorderLayout.CENTER);
       congratPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
       this.setVisible(false);
       Main.gameFrame.getContentPane().add(congratPanel);
       Main.gameFrame.setSize(400, 400);
       congratPanel.revalidate();
       Main.gameFrame.repaint();

    }

    /**
     * Draws the 'gama over' sign
     */
    void drawGameOver(){
        JTextField congratulate = new JTextField("GAME OVER!");
        congratulate.setFont(new Font("sanserif", Font.BOLD, 32));
        congratulate.setForeground(Color.RED);
        congratulate.setBackground(Color.GRAY);
        congratulate.setFocusable(false);
        congratPanel = new JPanel();
        congratPanel.setBackground(Color.GRAY);
        congratPanel.add(congratulate, BorderLayout.CENTER);
        congratPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        congratPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.setVisible(false);
        Main.gameFrame.getContentPane().add(congratPanel);
        Main.gameFrame.setSize(400, 400);
        congratPanel.revalidate();
        Main.gameFrame.repaint();
    }

    /**
     * Finds the cell at witch the mouse was clicked
     * @param x 'x' coordinate at witch the mouse was clicked
     * @param y 'y' coordinate at witch the mouse was clicked
     * @return the cell that was found
     */
    Cell findCell(final int x, final int y){
        int row = (int)Math.floor(x/PADDING);
        int column = (int)Math.floor(y/PADDING);
        return this.cells[row][column];
    }
}
