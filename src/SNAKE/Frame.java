package SNAKE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FilePermission;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Frame extends JFrame implements ActionListener {
    private JLabel background;
    private ArrayList<Integer> settingFile;
    private ArrayList<Integer> saveFile;
    private ButtonGroup groupLevel, groupMode, groupAppearance;
    private JRadioButton levelEasy, levelMedium, levelHard;
    private JRadioButton freeMode, timeMode;
    private JRadioButton look1, look2, look3;
    private ImageIcon startGame, option, bestScores, backMenu, saveSetting;
    private JButton buttonStart, buttonOption, buttonBestScores, buttonBackMenu, buttonSaveSettings;
    private Game game;
    private static int delayGame = 10;
    static Timer timerGame, timerKey;
    static int count = 0;
    static boolean statusGame = true;
    private int flagGame = 0;
    private int extraTime = 0;
    private boolean buttonStartPressed = false;
    static int flagStop = 0;
    private ArrayList<Integer> result;
    private int[] table_result;
    private JLabel result1, result2, result3, result4, result5, result6, result7, result8, result9, result10; //najlepsze wyniki
    public static int apperanceFlag = 1;
    public static int modeFlag = 1;
    public static int levelFlag = 1;
    private boolean keyFlag = true;


    public int getCount() {
        return count;
    }

    public static void startTimer(int countPassed) {
        ActionListener action = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    statusGame = false;
                    timerGame.stop();

                } else {
                    count--;

                    // System.out.println(count + " sekund");


                }
            }

        };
        timerGame = new Timer(100 * delayGame, action);
        timerGame.setInitialDelay(0);
        timerGame.start();
        count = countPassed;

    }

    public Frame() {
        game = new Game();
        timerKey = new Timer(10, this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_RIGHT ) {

                     game.vkRight();



                }
               else if (keyCode == KeyEvent.VK_LEFT ){

                    game.vkLeft();


                }
                else if (keyCode == KeyEvent.VK_UP ){

                        game.vkUp();



                }
                else if (keyCode == KeyEvent.VK_DOWN ) {

                        game.vkDown();



                }
                else if (keyCode == KeyEvent.VK_ENTER) {

                    game.vkEnter();


                }
               else if (keyCode == KeyEvent.VK_SPACE) {

                    game.vkSpace();
                    if (flagStop == 0) {
                        flagStop = 1;
                    } else {
                        flagStop = 0;
                    }

                }
              else  if (keyCode == KeyEvent.VK_ESCAPE) {

                    game.vkEsc();
                    if (game.isFlagBacground()) {
                        buttonStart.setVisible(true);
                        buttonBestScores.setVisible(true);
                        buttonOption.setVisible(true);
                        setLayout(new BorderLayout());
                        background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\game.jpg"));
                        add(background);
                        background.setVisible(true);
                        background.setLayout(new FlowLayout());
                        game.setFlagBacground(false);
                    }
                    if (buttonStartPressed) {
                        setBackground(new Color(48, 171, 60));
                    }


                }
            }

            @Override
            public void keyReleased(KeyEvent e) {





            }
        });
        setBounds(400, 100, 900, 800);

        setTitle("Snake");
        setResizable(false);
        setVisible(true);
        // setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        startGame = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\startgame.png");
        buttonStart = new JButton();
        buttonStart.setIcon(new ImageIcon(String.valueOf(startGame)));
        buttonStart.setBounds(70, 250, 350, 115);
        add(buttonStart);
        buttonStart.setFocusable(false);

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                background.setVisible(false);
                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);
                setBackground(new Color(48, 172, 60));
                Game gameTime = new Game();
                count = gameTime.getGameTime();
                flagGame = gameTime.getFlagGame();


                try {

                    Scanner setting = new Scanner(Paths.get("setting.txt"));
                    settingFile = new ArrayList();

                    while (setting.hasNext()) {

                        int option = setting.nextInt();
                        settingFile.add(option);

                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                    settingFile.add(1);
                    settingFile.add(1);
                    settingFile.add(1);

                }
                System.out.println(settingFile);


                modeFlag = settingFile.get(1);
                apperanceFlag = settingFile.get(2);
                levelFlag = settingFile.get(0);

                if (modeFlag == 2) {
                    if (levelFlag == 1) {
                        startTimer(count + 30);
                    } else if (levelFlag == 2) {
                        startTimer(count + 15);
                    } else if (levelFlag == 3) {
                        startTimer(count);
                    }
                }
                buttonStartPressed = true;


                game.setVisible(true);
                add(game);

            }
        });

        option = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\option.png");
        buttonOption = new JButton();
        buttonOption.setIcon(new ImageIcon(String.valueOf(option)));
        buttonOption.setBounds(70, 370, 350, 115);
        add(buttonOption);
        buttonOption.setFocusable(false);

        buttonOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {

                    Scanner setting = new Scanner(Paths.get("setting.txt"));
                    settingFile = new ArrayList();

                    while (setting.hasNext()) {

                        int option = setting.nextInt();
                        settingFile.add(option);

                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                    settingFile.add(1);
                    settingFile.add(1);
                    settingFile.add(1);

                }
                System.out.println(settingFile);


                modeFlag = settingFile.get(1);
                apperanceFlag = settingFile.get(2);
                levelFlag = settingFile.get(0);


                background.setVisible(false);

                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);


                JLabel level = new JLabel("LEVEL: ");
                level.setBounds(50, 300, 150, 50);
                level.setForeground(new Color(181, 230, 29));
                level.setFont(new Font("Arial", Font.BOLD, 30));

                add(level);

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
                add(levelEasy);

                add(levelHard);
                add(levelMedium);

                if (levelFlag == 1) {
                    levelEasy.setSelected(true);
                } else if (levelFlag == 2) {
                    levelMedium.setSelected(true);
                } else if (levelFlag == 3) {
                    levelHard.setSelected(true);
                }

                JLabel mode = new JLabel("MODE: ");
                mode.setBounds(50, 360, 150, 50);
                mode.setForeground(new Color(181, 230, 29));
                mode.setFont(new Font("Arial", Font.BOLD, 30));

                add(mode);

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
                add(freeMode);
                add(timeMode);

                if (modeFlag == 1) {
                    freeMode.setSelected(true);
                }
                if (modeFlag == 2) {
                    timeMode.setSelected(true);

                }
                JLabel appearance = new JLabel("APPEARANCE: ");
                appearance.setBounds(140, 420, 250, 50);
                appearance.setForeground(new Color(181, 230, 29));
                appearance.setFont(new Font("Arial", Font.BOLD, 30));
                add(appearance);

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
                add(look1);
                add(look2);
                add(look3);

                if (apperanceFlag == 1) {
                    look1.setSelected(true);
                }

                if (apperanceFlag == 2) {
                    look2.setSelected(true);
                }
                if (apperanceFlag == 3) {
                    look3.setSelected(true);
                }


                saveSetting = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\saveSettings.png");
                buttonSaveSettings = new JButton();
                buttonSaveSettings.setIcon(new ImageIcon(String.valueOf(saveSetting)));
                buttonSaveSettings.setBounds(280, 690, 115, 43);

                add(buttonSaveSettings);
                buttonSaveSettings.setFocusable(false);
                buttonSaveSettings.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Zapisane dane");
                        saveFile = new ArrayList<>();
                        if (levelEasy.isSelected()) {
                            saveFile.add(1);
                        }
                        if (levelMedium.isSelected()) {
                            saveFile.add(2);
                        }
                        if (levelHard.isSelected()) {
                            saveFile.add(3);
                        }
                        if (freeMode.isSelected()) {
                            saveFile.add(1);
                        } else if (timeMode.isSelected()) {
                            saveFile.add(2);
                        }

                        if (look1.isSelected()) {
                            saveFile.add(1);
                        }

                        if (look2.isSelected()) {
                            saveFile.add(2);
                        }

                        if (look3.isSelected()) {
                            saveFile.add(3);
                        }


                        System.out.println(saveFile);


                        modeFlag = saveFile.get(1);
                        apperanceFlag = saveFile.get(2);
                        levelFlag = saveFile.get(0);


                        try {
                            FileWriter saveSettings = new FileWriter("setting.txt");

                            saveSettings.append(levelFlag + "\r\n");
                            saveSettings.append(modeFlag + "\r\n");
                            saveSettings.append(apperanceFlag + "\r\n");
                            System.lineSeparator();


                            saveSettings.close();
                        } catch (IOException ex) {
                            System.out.println("File errror");
                        }

                    }
                });


                backMenu = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\backMenu.png");
                buttonBackMenu = new JButton();
                buttonBackMenu.setIcon(new ImageIcon(String.valueOf(backMenu)));
                buttonBackMenu.setBounds(130, 690, 115, 43);
                add(buttonBackMenu);
                buttonBackMenu.setFocusable(false);
                buttonBackMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        background.setVisible(false);
                        buttonBackMenu.setVisible(false);
                        look1.setVisible(false);
                        look2.setVisible(false);
                        look3.setVisible(false);
                        timeMode.setVisible(false);
                        freeMode.setVisible(false);
                        levelEasy.setVisible(false);
                        levelHard.setVisible(false);
                        levelMedium.setVisible(false);
                        appearance.setVisible(false);
                        mode.setVisible(false);
                        level.setVisible(false);
                        buttonSaveSettings.setVisible(false);

                        buttonStart.setVisible(true);
                        buttonBestScores.setVisible(true);
                        buttonOption.setVisible(true);
                        setLayout(new BorderLayout());
                        background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\game.jpg"));
                        add(background);
                        background.setVisible(true);
                        background.setLayout(new FlowLayout());
                        game.setFlagBacground(false);

                        if (buttonStartPressed) {
                            setBackground(new Color(48, 171, 60));
                        }

                    }
                });


                setLayout(new BorderLayout());
                background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\gameOption.jpg"));
                add(background);
                background.setVisible(true);
                background.setLayout(new FlowLayout());


            }
        });

        bestScores = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\bestScores.png");
        buttonBestScores = new JButton();
        buttonBestScores.setIcon(new ImageIcon(String.valueOf(bestScores)));
        buttonBestScores.setBounds(70, 490, 350, 115);
        add(buttonBestScores);
        buttonBestScores.setFocusable(false);

        buttonBestScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.setVisible(false);
                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);

                try {

                    Scanner odczyt = new Scanner(Paths.get("result.txt"));
                    result = new ArrayList();
                    for (int i = 0; i < 10; i++) {
                        result.add(0);
                    }


                    while (odczyt.hasNext()) {

                        int zmienna = odczyt.nextInt();

                        result.add(zmienna);
                        Collections.sort(result);
                        Collections.reverse(result);

                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                    result = new ArrayList();
                    for (int i = 0; i < 10; i++) {
                        result.add(0);
                    }
                }

                table_result = new int[10];

                for (int i = 0; i < table_result.length; i++) {
                    table_result[i] = result.get(i);
                }
                for (int el : table_result) {
                    System.out.println(el);
                }

                setBackground(Color.GRAY);


                result1 = new JLabel(String.valueOf(table_result[0]));
                result1.setFont(new Font("czcionka", Font.BOLD, 22));
                result1.setForeground(Color.black);
                result1.setBounds(163, 158, 500, 20);
                add(result1);
                result2 = new JLabel(String.valueOf(table_result[1]));
                result2.setFont(new Font("czcionka", Font.BOLD, 22));
                result2.setForeground(Color.black);
                result2.setBounds(273, 158, 500, 20);
                add(result2);
                result3 = new JLabel(String.valueOf(table_result[2]));
                result3.setFont(new Font("czcionka", Font.BOLD, 22));
                result3.setForeground(Color.black);
                result3.setBounds(378, 158, 500, 20);
                add(result3);
                result4 = new JLabel(String.valueOf(table_result[3]));
                result4.setFont(new Font("czcionka", Font.BOLD, 22));
                result4.setForeground(Color.black);
                result4.setBounds(483, 158, 500, 20);
                add(result4);
                result5 = new JLabel(String.valueOf(table_result[4]));
                result5.setFont(new Font("czcionka", Font.BOLD, 22));
                result5.setForeground(Color.black);
                result5.setBounds(593, 158, 500, 20);
                add(result5);
                result6 = new JLabel(String.valueOf(table_result[5]));
                result6.setFont(new Font("czcionka", Font.BOLD, 22));
                result6.setForeground(Color.black);
                result6.setBounds(593, 268, 500, 20);
                add(result6);
                result7 = new JLabel(String.valueOf(table_result[6]));
                result7.setFont(new Font("czcionka", Font.BOLD, 22));
                result7.setForeground(Color.black);
                result7.setBounds(593, 368, 500, 20);
                add(result7);
                result8 = new JLabel(String.valueOf(table_result[7]));
                result8.setFont(new Font("czcionka", Font.BOLD, 22));
                result8.setForeground(Color.black);
                result8.setBounds(593, 478, 500, 20);
                add(result8);
                result9 = new JLabel(String.valueOf(table_result[8]));
                result9.setFont(new Font("czcionka", Font.BOLD, 22));
                result9.setForeground(Color.black);
                result9.setBounds(703, 478, 500, 20);
                add(result9);
                result10 = new JLabel(String.valueOf(table_result[9]));
                result10.setFont(new Font("czcionka", Font.BOLD, 22));
                result10.setForeground(Color.black);
                result10.setBounds(808, 478, 500, 20);
                add(result10);

                backMenu = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\backMenu.png");
                buttonBackMenu = new JButton();
                buttonBackMenu.setIcon(new ImageIcon(String.valueOf(backMenu)));
                buttonBackMenu.setBounds(10, 690, 115, 43);
                add(buttonBackMenu);
                buttonBackMenu.setFocusable(false);
                buttonBackMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        background.setVisible(false);
                        buttonBackMenu.setVisible(false);
                        result1.setVisible(false);
                        result2.setVisible(false);
                        result3.setVisible(false);
                        result4.setVisible(false);
                        result5.setVisible(false);
                        result6.setVisible(false);
                        result7.setVisible(false);
                        result8.setVisible(false);
                        result9.setVisible(false);
                        result10.setVisible(false);

                        buttonStart.setVisible(true);
                        buttonBestScores.setVisible(true);
                        buttonOption.setVisible(true);
                        setLayout(new BorderLayout());
                        background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\game.jpg"));
                        add(background);
                        background.setVisible(true);
                        background.setLayout(new FlowLayout());
                        game.setFlagBacground(false);

                        if (buttonStartPressed) {
                            setBackground(new Color(48, 171, 60));
                        }

                    }
                });


                setLayout(new BorderLayout());
                background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\Scores.png"));

                add(background);


                background.setLayout(new FlowLayout());


            }
        });

        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\game.jpg"));
        add(background);
        background.setVisible(true);
        background.setLayout(new FlowLayout());


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


