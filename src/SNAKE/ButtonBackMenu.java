package SNAKE;

import javax.swing.*;


/**
 * Klasa przycisk - powrot do menu głownego
 */

public class ButtonBackMenu {

    private ImageIcon backMenu;

    /**
     * @return bieżący buttonBackMenu
     */
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
