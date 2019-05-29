package SNAKE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
    private JLabel bestResult[]; //najlepsze wyniki
    public static int apperanceFlag = 1;
    public static int modeFlag = 1;
    public static int levelFlag = 1;
    private boolean keyFlag = true;
    private boolean keyFlagU = true;
    private boolean  keyFlagD = true;
    private boolean keyFlagR = true;
    private boolean keyFlagL = true;

    public static void createFile() throws FileNotFoundException {  //tworzenie pliku
        PrintWriter settings = new PrintWriter("setting.txt");
        settings.println(1);
        settings.println(1);
        settings.println(1);
        settings.close();

    }


    public int getCount() {
        return count;
    }

    public static void startTimer(int countPassed) {   //metoda timer do trybu Timer
        ActionListener action = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    statusGame = false;
                    timerGame.stop();

                } else {
                    count--;

                }
            }

        };
        timerGame = new Timer(100 * delayGame, action);
        timerGame.setInitialDelay(0);
        timerGame.start();
        count = countPassed;

    }

    public Frame() {     //konstruktor
        game = new Game();
        timerKey = new Timer(10, this);




        addKeyListener(new KeyAdapter() {   //obsługa klawiszy
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_RIGHT) {


                        if(keyFlag) {
                            game.vkRight();
                            keyFlag=false;
                        }


                }
               else if (keyCode == KeyEvent.VK_LEFT ){

                    if(keyFlag) {
                        game.vkLeft();
                        keyFlag=false;
                    }


                }
                else if (keyCode == KeyEvent.VK_UP ){

                    if(keyFlag) {
                        game.vkUp();
                        keyFlag=false;
                    }


                }
                else if (keyCode == KeyEvent.VK_DOWN ) {
                    if(keyFlag) {
                        game.vkDown();
                        keyFlag=false;
                    }


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

                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_RIGHT) {
                    keyFlag=true;
                }
                else if(keyCode == KeyEvent.VK_LEFT)
                {
                    keyFlag=true;
                }
                else if(keyCode == KeyEvent.VK_UP)
                {
                    keyFlag=true;
                }
                else if(keyCode == KeyEvent.VK_DOWN)
                {
                    keyFlag=true;
                }



            }
        });
        setBounds(400, 100, 900, 800);

        setTitle("Snake");
        setResizable(false);
        setVisible(true);
        // setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ButtonStart buttons = new ButtonStart();
        buttonStart = buttons.getButtonStart(); //przycisk Start
        add(buttonStart);
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
                levelFlag = settingFile.get(0);
                modeFlag = settingFile.get(1);
                apperanceFlag = settingFile.get(2);


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

        ButtonOption button = new ButtonOption();
        buttonOption = button.getButtonOption(); //przycisk option
        add(buttonOption);

        buttonOption.addActionListener(new ActionListener() {  //dodanie sluchacza
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


                ButtonSaveSettings buttonS = new ButtonSaveSettings();
                buttonSaveSettings = buttonS.getButtonSaveSettings();
                add(buttonSaveSettings);
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


                ButtonBackMenu buttonB = new ButtonBackMenu();
                buttonBackMenu = buttonB.getButtonBackMenu();

                add(buttonBackMenu);

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

        ButtonBestScores buttonBS = new ButtonBestScores();
        buttonBestScores = buttonBS.getButtonBestScores();

        add(buttonBestScores);


        buttonBestScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background.setVisible(false);
                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);


                setBackground(Color.GRAY);
                SaveResult saveResult = new SaveResult();

                bestResult = new JLabel[10];
                SaveResult saveR = new SaveResult();

                bestResult = saveR.getBestResult();

                for(int i=0; i<10; i++)
                {
                    add(bestResult[i]);
                }


               ButtonBackMenu backMenu = new ButtonBackMenu();
                buttonBackMenu = backMenu.getButtonBackMenu();
                add(buttonBackMenu);
                buttonBackMenu.setFocusable(false);
                buttonBackMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        background.setVisible(false);
                        buttonBackMenu.setVisible(false);
                       for(int i=0; i<10;i++)
                       {
                           bestResult[i].setVisible(false);
                       }

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


