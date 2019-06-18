package SNAKE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 * Okno menu głownego
 */

public class Menu extends JFrame implements ActionListener {
    private JLabel background;
    private ArrayList<Integer> settingFile;
    private ArrayList<Integer> saveFile;
    private ButtonGroup groupLevel, groupMode, groupAppearance;
    private JRadioButton levelEasy, levelMedium, levelHard;
    private JRadioButton freeMode, timeMode;
    private JRadioButton look1, look2, look3;
    private JButton buttonStart, buttonOption, buttonBestScores, buttonBackMenu, buttonSaveSettings;
    private Game game;
    private static int delayGame = 10;
    static Timer timerGame, timerKey;
    static int count = 0;
    static boolean statusGame = true;
    private int flagGame = 0;
    private boolean buttonStartPressed = false;
    static int flagStop = 0;
    private ArrayList<Integer> result;
    private int[] table_result;
    private JLabel[] bestResult; //najlepsze wyniki
    public static int apperanceFlag = 1;
    public static int modeFlag = 1;
    public static int levelFlag = 1;
    private ArrayList<Integer> bufor = new ArrayList<>();
    private boolean flagaZmiany = true;
    private final Set<Character> pressed = new HashSet<Character>();
    private boolean flagRight = false, flagLeft = false, flagUp = false, flagDown = false;
    private boolean moge = true;


    /**
     * stworzenie pliku do zapisu
     *
     * @throws FileNotFoundException rzuć wyjątek gdy plik nie istnieje
     */
    public static void createFile() throws FileNotFoundException {  //tworzenie pliku
        PrintWriter settings = new PrintWriter("setting.txt");
        settings.println(1);
        settings.println(1);
        settings.println(1);
        settings.close();

    }

    /**
     * odczytanie zapisu ustawień
     */
    public void readSetting() {
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
    }

    /**
     * zapis do pliku ustawień gry
     */
    public void saveSetting() {
        try {
            //zapisanie danych
            FileWriter saveSettings = new FileWriter("setting.txt");
            saveSettings.append(String.valueOf(levelFlag)).append("\r\n");
            saveSettings.append(String.valueOf(modeFlag)).append("\r\n");
            saveSettings.append(String.valueOf(apperanceFlag)).append("\r\n");
            saveSettings.close();
        } catch (IOException ex) {
            System.out.println("File errror");
        }
    }

    /**
     * @return count, licznik timera w trybie gry TIMEMODE
     */
    public int getCount() {
        return count;
    }

    /**
     * dzialanie timersa
     *
     * @param countPassed czas poczatkowy rozgrywki w trybie czasowym
     */
    public static void startTimer(int countPassed) {
        ActionListener action = e -> {
            if (count == 0) {
                statusGame = false;
                timerGame.stop();

            } else {
                count--;

            }
        };
        timerGame = new Timer(100 * delayGame, action);
        timerGame.setInitialDelay(0);
        timerGame.start();
        count = countPassed;

    }

    public Menu() {     //konstruktor
        game = new Game();
        timerKey = new Timer(10, this);


        class Key extends Thread {
            int licznik = 0;

            public Key() {
                addKeyListener(new KeyListener() {
                    @Override

                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int keyCode = e.getKeyCode();


                        switch (keyCode) {

                            case KeyEvent.VK_RIGHT: {
                                {
                                    licznik++;
                                    if (licznik == 1) {
                                        game.vkRight();
                                    }
                                }
                                break;
                            }
                            case KeyEvent.VK_LEFT: {
                                {
                                    licznik++;
                                    if (licznik == 1) {
                                        game.vkLeft();
                                    }
                                    break;
                                }
                            }
                            case KeyEvent.VK_DOWN: {
                                licznik++;
                                if (licznik == 1) {
                                    game.vkDown();
                                }
                                break;
                            }
                            case KeyEvent.VK_UP: {
                                licznik++;
                                if (licznik == 1) {
                                    game.vkUp();
                                }
                                break;
                            }

                        }

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        int keyCode = e.getKeyCode();


                        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
                            licznik = 0;
                        }


                    }

                });

            }
        }
        Key key = new Key();
        addKeyListener(new KeyAdapter() {   //obsługa klawiszy
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_ENTER) {

                    game.vkEnter();
                    pressed.clear();

                } else if (keyCode == KeyEvent.VK_SPACE) {

                    game.vkSpace();
                    if (flagStop == 0) {
                        flagStop = 1;
                    } else {
                        flagStop = 0;
                    }

                } else if (keyCode == KeyEvent.VK_ESCAPE) {

                    game.vkEsc();
                    if (game.isFlagBacground()) {
                        buttonStart.setVisible(true);
                        buttonBestScores.setVisible(true);
                        buttonOption.setVisible(true);
                        setLayout(new BorderLayout());
                        background = new JLabel(new ImageIcon("game.jpg"));
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


        ButtonStart buttons = new ButtonStart();
        buttonStart = buttons.getButtonStart(); //przycisk Start
        add(buttonStart);
        buttonStart.addActionListener(e -> {
            repaint();
            background.setVisible(false);
            buttonOption.setVisible(false);
            buttonBestScores.setVisible(false);
            buttonStart.setVisible(false);
            setBackground(new Color(48, 172, 60));
            Game gameTime = new Game();


            count = gameTime.getGameTime();
            flagGame = gameTime.getFlagGame();

            readSetting();
            System.out.println(settingFile);
            //odczyt z pliku do flag
            levelFlag = settingFile.get(0);
            modeFlag = settingFile.get(1);
            apperanceFlag = settingFile.get(2);


            //mode 2, czas początkowy
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

        });

        ButtonOption button = new ButtonOption();
        buttonOption = button.getButtonOption(); //przycisk option
        add(buttonOption);

        //dodanie sluchacza
        buttonOption.addActionListener(e -> {
            //odczyt z pliku opcji
            readSetting();
            System.out.println(settingFile);

            //odczyt do flag
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
            //zakladka option
            LevelButtons levelButtons = new LevelButtons();
            groupLevel = levelButtons.getGroupLevel();
            levelEasy = levelButtons.getLevelEasy();
            levelMedium = levelButtons.getLevelMedium();
            levelHard = levelButtons.getLevelHard();

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

            groupMode = levelButtons.getGroupMode();
            freeMode = levelButtons.getFreeMode();
            timeMode = levelButtons.getTimeMode();
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

            groupAppearance = levelButtons.getGroupAppearance();

            look1 = levelButtons.getLook1();
            look2 = levelButtons.getLook2();
            look3 = levelButtons.getLook3();

            add(look1);
            add(look2);
            add(look3);

            //odczytanie z pliku i zaznacz radioButtony zgodnie z odczytem z pliku
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
            buttonSaveSettings.addActionListener(e12 -> {
                //zapisanie danych
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


                saveSetting();

            });


            ButtonBackMenu buttonB = new ButtonBackMenu();
            buttonBackMenu = buttonB.getButtonBackMenu();

            add(buttonBackMenu);

            buttonBackMenu.addActionListener(e13 -> {

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
                background = new JLabel(new ImageIcon("game.jpg"));
                add(background);
                background.setVisible(true);
                background.setLayout(new FlowLayout());
                game.setFlagBacground(false);

                if (buttonStartPressed) {
                    setBackground(new Color(48, 171, 60));
                }

            });


            setLayout(new BorderLayout());
            background = new JLabel(new ImageIcon("gameOption.jpg"));
            add(background);
            background.setVisible(true);
            background.setLayout(new FlowLayout());


        });

        ButtonBestScores buttonBS = new ButtonBestScores();
        buttonBestScores = buttonBS.getButtonBestScores();

        add(buttonBestScores);


        buttonBestScores.addActionListener(e -> {
            background.setVisible(false);
            buttonOption.setVisible(false);
            buttonBestScores.setVisible(false);
            buttonStart.setVisible(false);


            setBackground(Color.GRAY);
            SaveResult saveResult = new SaveResult();

            bestResult = new JLabel[10];
            SaveResult saveR = new SaveResult();

            bestResult = saveR.getBestResult();
            //wyswietlenie wynikow
            for (int i = 0; i < 10; i++) {
                add(bestResult[i]);
            }


            ButtonBackMenu backMenu = new ButtonBackMenu();
            buttonBackMenu = backMenu.getButtonBackMenu();
            add(buttonBackMenu);
            buttonBackMenu.setFocusable(false);
            buttonBackMenu.addActionListener(e14 -> {

                background.setVisible(false);
                buttonBackMenu.setVisible(false);
                for (int i = 0; i < 10; i++) {
                    bestResult[i].setVisible(false);
                }

                buttonStart.setVisible(true);
                buttonBestScores.setVisible(true);
                buttonOption.setVisible(true);
                setLayout(new BorderLayout());
                background = new JLabel(new ImageIcon("game.jpg"));
                add(background);
                background.setVisible(true);
                background.setLayout(new FlowLayout());
                game.setFlagBacground(false);

                if (buttonStartPressed) {
                    setBackground(new Color(48, 171, 60));
                }

            });


            setLayout(new BorderLayout());
            background = new JLabel(new ImageIcon("Scores.png"));

            add(background);


            background.setLayout(new FlowLayout());


        });

        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("game.jpg"));
        add(background);
        background.setVisible(true);
        background.setLayout(new FlowLayout());


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


