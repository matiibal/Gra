package SNAKE;

import javax.swing.*;

/**
 * Klasa - przycisk start - rozpoczecie gry
 */

public class ButtonStart {
    private ImageIcon startGame;
    private JButton buttonStart;


    public JButton getButtonStart() {
        return buttonStart;
    }



    public ButtonStart() {
        startGame = new ImageIcon("startgame.png");
        buttonStart = new JButton();
        buttonStart.setIcon(new ImageIcon(String.valueOf(startGame)));
        buttonStart.setBounds(70, 250, 350, 115);
        buttonStart.setFocusable(false);
    }

}
