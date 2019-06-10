package SNAKE;

import javax.swing.*;

/**
 * Klasa - przycisk w menu glownym Opcje
 */


public class ButtonOption {
    private ImageIcon  option;

    /**
     * @return bieżacy buttonOption
     */
    public JButton getButtonOption() {
        return buttonOption;
    }

    private JButton  buttonOption;

    public ButtonOption() {
        option = new ImageIcon("option.png");
        buttonOption = new JButton();
        buttonOption.setIcon(new ImageIcon(String.valueOf(option)));
        buttonOption.setBounds(70, 370, 350, 115);
        buttonOption.setFocusable(false);
    }





}
