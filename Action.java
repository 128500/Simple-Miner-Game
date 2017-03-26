package com.llisovichok.lessons.bombergame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 **The main idea was taken at <a href = "http://job4j.ru/courses/java_way_from_student_to_master.html"> job4j.ru/courses/java_way_from_student_to_master</a>
 * The implementation of the methods and most part of the code was written by author
 * Created by ALEKSANDER KUDIN on 11.02.2017.
 */
public class Action implements MouseListener {

    BoardGUI boardGUI;

    public Action(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(!boardGUI.findCell(x,y).isOpened()) {
            boardGUI.drawCell(x, y);
            if (boardGUI.gl.shouldBang(x, y)) {
                boardGUI.drawBang(x, y);
                boardGUI.gl.blows++;
                if(boardGUI.gl.gameOver()) boardGUI.drawGameOver();
            } else if (boardGUI.gl.finish()) boardGUI.drawCongratulate();

        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
