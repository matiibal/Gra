package SNAKE;

import com.sun.org.apache.bcel.internal.generic.FADD;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Frame extends JFrame {
    private JLabel background;
    private ImageIcon startGame, option, bestScores, backMenu;
    private JButton buttonStart, buttonOption, buttonBestScores, buttonBackMenu;
    private Game game;
    private static int delayGame = 10;
    static Timer timerGame;
    static int count = 0;
    static boolean statusGame = true;
    private int flagGame = 0;
    private int extraTime = 0;
    private boolean buttonStartPressed = false;
    static int flagStop = 0;
    private ArrayList<Integer> result;
    private int[] table_result;
    private JLabel result1, result2, result3, result4, result5, result6, result7, result8, result9, result10; //najlepsze wyniki

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
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    game.vkRight();


                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                    game.vkLeft();

                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {

                    game.vkUp();

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                    game.vkDown();

                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    game.vkEnter();

                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    game.vkSpace();
                    if (flagStop == 0) {
                        flagStop = 1;
                    } else {
                        flagStop = 0;
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

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

                background.setVisible(false);
                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);
                setBackground(new Color(48, 172, 60));
                Game gameTime = new Game();
                count = gameTime.getGameTime();
                count += 50;
                flagGame = gameTime.getFlagGame();
                startTimer(count);
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
                background.setVisible(false);

                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);
                setBackground(Color.GRAY);


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
                result3.setBounds(383, 158, 500, 20);
                add(result3);
                result4 = new JLabel(String.valueOf(table_result[3]));
                result4.setFont(new Font("czcionka", Font.BOLD, 22));
                result4.setForeground(Color.black);
                result4.setBounds(493, 158, 500, 20);
                add(result4);
                result5 = new JLabel(String.valueOf(table_result[4]));
                result5.setFont(new Font("czcionka", Font.BOLD, 22));
                result5.setForeground(Color.black);
                result5.setBounds(603, 158, 500, 20);
                add(result5);
                result6 = new JLabel(String.valueOf(table_result[5]));
                result6.setFont(new Font("czcionka", Font.BOLD, 22));
                result6.setForeground(Color.black);
                result6.setBounds(603, 268, 500, 20);
                add(result6);
                result7 = new JLabel(String.valueOf(table_result[6]));
                result7.setFont(new Font("czcionka", Font.BOLD, 22));
                result7.setForeground(Color.black);
                result7.setBounds(603, 368, 500, 20);
                add(result7);
                result8 = new JLabel(String.valueOf(table_result[7]));
                result8.setFont(new Font("czcionka", Font.BOLD, 22));
                result8.setForeground(Color.black);
                result8.setBounds(603, 478, 500, 20);
                add(result8);
                result9 = new JLabel(String.valueOf(table_result[8]));
                result9.setFont(new Font("czcionka", Font.BOLD, 22));
                result9.setForeground(Color.black);
                result9.setBounds(713, 478, 500, 20);
                add(result9);
                result10 = new JLabel(String.valueOf(table_result[9]));
                result10.setFont(new Font("czcionka", Font.BOLD, 22));
                result10.setForeground(Color.black);
                result10.setBounds(818, 478, 500, 20);
                add(result10);

                backMenu = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\backMenu.png");
                buttonBackMenu = new JButton();
                buttonBackMenu.setIcon(new ImageIcon(String.valueOf(backMenu)));
                buttonBackMenu.setBounds(10,690,115,43);
                add(buttonBackMenu);
                buttonBackMenu.addActionListener(new ActionListener() {
                    @Override
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


}


