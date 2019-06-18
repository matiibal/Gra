package SNAKE;

import javax.swing.*;

/**
 * Klasa - przycisk najlepsze wyniki
 */

public class ButtonBestScores {
    private ImageIcon bestScores;




    public JButton getButtonBestScores() {
        return buttonBestScores;
    }

    private JButton buttonBestScores;

    public ButtonBestScores()
    {
        bestScores = new ImageIcon("bestScores.png");
        buttonBestScores = new JButton();
        buttonBestScores.setIcon(new ImageIcon(String.valueOf(bestScores)));
        buttonBestScores.setBounds(70, 490, 350, 115);
        buttonBestScores.setFocusable(false);

    }
}
