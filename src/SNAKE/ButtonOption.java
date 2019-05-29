package SNAKE;

import javax.swing.*;

public class ButtonOption {
    private ImageIcon  option;

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
