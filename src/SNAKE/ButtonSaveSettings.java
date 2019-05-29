package SNAKE;

import javax.swing.*;
import java.awt.*;

public class ButtonSaveSettings {

    private ImageIcon saveSetting;

    public JButton getButtonSaveSettings() {
        return buttonSaveSettings;
    }

    private JButton buttonSaveSettings;


    public ButtonSaveSettings() {
        saveSetting = new ImageIcon("saveSettings.png");
        buttonSaveSettings = new JButton();
        buttonSaveSettings.setIcon(new ImageIcon(String.valueOf(saveSetting)));
        buttonSaveSettings.setBounds(280, 690, 115, 43);
        buttonSaveSettings.setFocusable(false);
    }
}