package SNAKE;

import javax.swing.*;
import java.awt.*;

public class LevelButtons {

    public ButtonGroup getGroupLevel() {
        return groupLevel;
    }

    public JRadioButton getLevelEasy() {
        return levelEasy;
    }

    public JRadioButton getLevelMedium() {
        return levelMedium;
    }

    public JRadioButton getLevelHard() {
        return levelHard;
    }

    private ButtonGroup groupLevel;
    private ButtonGroup groupMode;

    public ButtonGroup getGroupAppearance() {
        return groupAppearance;
    }

    public JRadioButton getLook1() {
        return look1;
    }

    public JRadioButton getLook2() {
        return look2;
    }

    public JRadioButton getLook3() {
        return look3;
    }

    private ButtonGroup groupAppearance;
    private JRadioButton levelEasy;
    private JRadioButton levelMedium;
    private JRadioButton levelHard, look1,look2,look3;

    public ButtonGroup getGroupMode() {
        return groupMode;
    }

    public JRadioButton getFreeMode() {
        return freeMode;
    }

    public JRadioButton getTimeMode() {
        return timeMode;
    }

    private JRadioButton freeMode;
    private JRadioButton timeMode;

    public LevelButtons()
    {
        groupLevel = new ButtonGroup();
        levelEasy = new JRadioButton("EASY", true);
        levelMedium = new JRadioButton("MEDIUM", false);
        levelHard = new JRadioButton("HARD", false);

        groupLevel.add(levelEasy);
        groupLevel.add(levelMedium);
        groupLevel.add(levelHard);


        levelEasy.setBounds(180, 300, 110, 50);
        levelEasy.setForeground(Color.white);
        levelEasy.setFont(new Font("Arial", Font.BOLD, 25));
        levelEasy.setBackground(new Color(34, 177, 76));
        levelEasy.setFocusable(false);
        levelMedium.setBounds(290, 300, 130, 50);
        levelMedium.setForeground(Color.white);
        levelMedium.setFont(new Font("Arial", Font.BOLD, 25));
        levelMedium.setBackground(new Color(34, 177, 76));
        levelMedium.setFocusable(false);
        levelHard.setBounds(440, 300, 110, 50);
        levelHard.setForeground(Color.white);
        levelHard.setFont(new Font("Arial", Font.BOLD, 25));
        levelHard.setBackground(new Color(34, 177, 76));
        levelHard.setFocusable(false);



        groupMode = new ButtonGroup();
        freeMode = new JRadioButton("FREE MODE", true);
        timeMode = new JRadioButton("TIME MODE", false);


        groupMode.add(freeMode);
        groupMode.add(timeMode);

        freeMode.setBounds(180, 360, 180, 50);
        freeMode.setForeground(Color.white);
        freeMode.setFont(new Font("Arial", Font.BOLD, 25));
        freeMode.setBackground(new Color(34, 177, 76));
        freeMode.setFocusable(false);
        timeMode.setBounds(360, 360, 180, 50);
        timeMode.setForeground(Color.white);
        timeMode.setFont(new Font("Arial", Font.BOLD, 25));
        timeMode.setBackground(new Color(34, 177, 76));
        timeMode.setFocusable(false);


        groupAppearance = new ButtonGroup();

        look1 = new JRadioButton("1", false);
        look2 = new JRadioButton("2", true);
        look3 = new JRadioButton("3", false);

        groupAppearance.add(look1);
        groupAppearance.add(look2);
        groupAppearance.add(look3);

        look1.setBounds(90, 630, 110, 20);
        look1.setForeground(Color.white);
        look1.setFont(new Font("Arial", Font.BOLD, 25));
        look1.setBackground(new Color(34, 177, 76));
        look1.setFocusable(false);
        look2.setBounds(230, 630, 130, 20);
        look2.setForeground(Color.white);
        look2.setFont(new Font("Arial", Font.BOLD, 25));
        look2.setBackground(new Color(34, 177, 76));
        look2.setFocusable(false);
        look3.setBounds(370, 630, 110, 20);
        look3.setForeground(Color.white);
        look3.setFont(new Font("Arial", Font.BOLD, 25));
        look3.setBackground(new Color(34, 177, 76));
        look3.setFocusable(false);



    }
}
