package com.llisovichok.lessons.bombergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ALEKSANDER KUDIN on 17.02.2017.
 */
class StartingPanel extends JPanel {

    private BoardGUI boardGUI;
    private GameFrame gameFrame;
    private JButton easy, middle, hard;
    private ButtonGroup chooseFieldButtons;

    /**
     * Creates GUI of starting frame with buttons on it
     * for choosing the game's mode and the board's field
     * @param gameFrame the main frame of the program
     */
    StartingPanel(final GameFrame gameFrame) {

        this.gameFrame = gameFrame;

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.GRAY);

        JLabel signLabel = new JLabel("Choose mode:", JLabel.CENTER);
        signLabel.setFont(new Font("sanserif", Font.BOLD, 22));
        signLabel.setForeground(Color.RED);

        JLabel boardFieldLabel = new JLabel("Choose field:", JLabel.CENTER);
        boardFieldLabel.setFont(new Font("sanserif", Font.BOLD, 22));
        boardFieldLabel.setForeground(Color.RED);

        easy = createButton("Easy");
        easy.setToolTipText("10% of the field are bombs");
        easy.addActionListener(new ButtonsListener());

        middle = createButton("Middle");
        middle.setToolTipText("20% of the field are bombs");
        middle.addActionListener(new ButtonsListener());

        hard = createButton("Hard");
        hard.setToolTipText("30% of the field are bombs");
        hard.addActionListener(new ButtonsListener());

        buttonsPanel.add(signLabel);
        buttonsPanel.add(easy);
        buttonsPanel.add(middle);
        buttonsPanel.add(hard);
        buttonsPanel.add(boardFieldLabel);

        buttonsPanel.setLayout(new GridLayout(5, 0, 10, 10));

        chooseFieldButtons = new ButtonGroup();

        JRadioButton button10x10 = createJRB("10x10 field", false, "10x10");
        JRadioButton button8x8 = createJRB("8x8 field", false, "8x8");
        JRadioButton button6x6 = createJRB("6x6 field", true, "6x6");

        chooseFieldButtons.add(button6x6);
        chooseFieldButtons.add(button8x8);
        chooseFieldButtons.add(button10x10);

        JPanel chooseFieldPanel = new JPanel();
        chooseFieldPanel.setBackground(Color.GRAY);

        chooseFieldPanel.add(button6x6);
        chooseFieldPanel.add(button8x8);
        chooseFieldPanel.add(button10x10);

        this.setLayout(new BorderLayout());
        this.add(chooseFieldPanel, BorderLayout.SOUTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.setBackground(Color.GRAY);
    }

    /**
     * Creates a button
     * @param name the button's sign
     * @return the created button
     */
    private JButton createButton(final String name) {
        JButton temp = new JButton(name);
        temp.setFont(new Font("sanserif", Font.PLAIN, 26));
        temp.setBackground(Color.BLACK);
        temp.setForeground(Color.RED);
        temp.setPreferredSize(new Dimension(200, 80));
        return temp;
    }

    /**
     * Creates a radio button
     * @param label the button's label
     * @param selected shows if the button is selected
     * @param rowsAndColumns the action command
     * @return the created button
     */
    private JRadioButton createJRB(final String label, final boolean selected, final String rowsAndColumns) {
        JRadioButton jrb = new JRadioButton(label, selected);
        jrb.setForeground(Color.RED);
        jrb.setBackground(Color.BLACK);
        jrb.setActionCommand(rowsAndColumns);
        jrb.setFont(new Font("sanserif", Font.PLAIN, 18));
        return jrb;
    }

    /**
     * Creates the new board according to the user's choice
     */
    private class ButtonsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(easy)) {
                if (chooseFieldButtons.getSelection().getActionCommand().equals("10x10"))
                    boardGUI = new BoardGUI(10, 10, 10);
                else if (chooseFieldButtons.getSelection().getActionCommand().equals("8x8"))
                    boardGUI = new BoardGUI(8, 8, 10);
                else boardGUI = new BoardGUI(6, 6, 10);
            }
            if (e.getSource().equals(middle)) {
                if (chooseFieldButtons.getSelection().getActionCommand().equals("10x10"))
                    boardGUI = new BoardGUI(10, 10, 20);
                else if (chooseFieldButtons.getSelection().getActionCommand().equals("8x8"))
                    boardGUI = new BoardGUI(8, 8, 20);
                else boardGUI = new BoardGUI(6, 6, 20);
            }
            if (e.getSource().equals(hard)) {
                if (chooseFieldButtons.getSelection().getActionCommand().equals("10x10"))
                    boardGUI = new BoardGUI(10, 10, 30);
                else if (chooseFieldButtons.getSelection().getActionCommand().equals("8x8"))
                    boardGUI = new BoardGUI(8, 8, 30);
                else boardGUI = new BoardGUI(6, 6, 30);
            }
            gameFrame.getContentPane().removeAll();

            JButton restartButton = new JButton("Restart");
            restartButton.setBackground(Color.BLACK);
            restartButton.setForeground(Color.RED);
            restartButton.setFocusable(false);
            restartButton.addActionListener(new RestartAction());

            JButton newGameButton = new JButton("New game");
            newGameButton.setBackground(Color.BLACK);
            newGameButton.setForeground(Color.RED);
            newGameButton.setFocusable(false);
            newGameButton.addActionListener(new NewGameListener());

            JButton showAllButton = new JButton("Show All");
            showAllButton.setBackground(Color.BLACK);
            showAllButton.setForeground(Color.RED);
            showAllButton.setFocusable(false);
            showAllButton.addActionListener(new ShowAllAction());

            JPanel panel = new JPanel();
            panel.setBackground(Color.GRAY);
            panel.add(restartButton);
            panel.add(newGameButton);
            panel.add(showAllButton);
            panel.revalidate();

            gameFrame.getContentPane().add(BorderLayout.NORTH, panel);
            gameFrame.add(boardGUI);
            gameFrame.setSize(boardGUI.width, boardGUI.height);
            boardGUI.revalidate();
            gameFrame.repaint();
        }
    }

    /**
     * Restarts the current game with replacing of the bombs on the board
     */
    private class RestartAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boardGUI.gl.opened = 0; // resets the counter of the opened cells
            boardGUI.gl.blows = 0;  // resets the counter of the blown bombs
            boardGUI.cells = boardGUI.bg.generate(boardGUI.cells.length, boardGUI.cells[0].length, boardGUI.bombPercentageAmount);
            boardGUI.revalidate();
            boardGUI.repaint();
            boardGUI.setVisible(true);
            if (boardGUI.congratPanel != null) gameFrame.remove(boardGUI.congratPanel);
            gameFrame.getContentPane().add(boardGUI);
            gameFrame.setSize(boardGUI.width, boardGUI.height);
            gameFrame.repaint();
        }
    }

    /**
     * Returns to the starting window
     */
    private class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameFrame.getContentPane().removeAll();
            StartingPanel startingPanel = new StartingPanel(gameFrame);
            gameFrame.add(startingPanel, BorderLayout.CENTER);
            gameFrame.setSize(600, 600);
            startingPanel.revalidate();
            gameFrame.repaint();

        }
    }

    /**
     * Shows all the cells (for those who despaired0
     */
    private class ShowAllAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i != boardGUI.cells.length; i++) {
                for (int k = 0; k != boardGUI.cells[0].length; k++) {
                    boardGUI.cells[i][k].setOpened(true);
                }
            }
            boardGUI.revalidate();
            boardGUI.repaint();
            gameFrame.repaint();
        }
    }
}



