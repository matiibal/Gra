package SNAKE;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame {
    private JLabel background;
    private ImageIcon startGame, option, bestScores;
    private JButton buttonStart, buttonOption, buttonBestScores;
    private Game game;
    private static int delayGame = 10;
    static Timer timerGame;
    static int count = 0;
    static boolean statusGame=true;
    private int movesSnake = 0;

    public int getCount() {
        return count;
    }

    public static void startTimer(int countPassed) {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    statusGame=false;
                    timerGame.stop();

                } else {
                    count--;
                    System.out.println(count + " sekund");


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
                Game gameTime = new Game();
                count = gameTime.getGameTime();
                count += 50;
                startTimer(count);
                background.setVisible(false);

                buttonOption.setVisible(false);
                buttonBestScores.setVisible(false);
                buttonStart.setVisible(false);
                setBackground(Color.GRAY);

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
                setBackground(Color.GRAY);

            }
        });

        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\game.jpg"));
        add(background);
        background.setVisible(true);
        background.setLayout(new FlowLayout());


    }


}


