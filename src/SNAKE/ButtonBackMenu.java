package SNAKE;

import javax.swing.*;

public class ButtonBackMenu {

    private ImageIcon backMenu;

    public JButton getButtonBackMenu() {
        return buttonBackMenu;
    }

    private JButton buttonBackMenu;
    public ButtonBackMenu()
    {

        backMenu = new ImageIcon("backMenu.png");
        buttonBackMenu = new JButton();
        buttonBackMenu.setIcon(new ImageIcon(String.valueOf(backMenu)));
        buttonBackMenu.setBounds(130, 690, 115, 43);
        buttonBackMenu.setFocusable(false);
    }
}
