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
    private ImageIcon startGame, option, bestScores;
    private JButton buttonStart, buttonOption, buttonBestScores;
    private Game game;
    private static int delayGame = 10;
    static Timer timerGame;
    static int count = 0;
    static boolean statusGame = true;
    private int flagGame = 0;
    private int extraTime = 0;
    private boolean buttonStartPressed=false;
    static int flagStop = 0;
    private ArrayList<Integer> result;
    private int[] table_result;

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
                    }
                    else
                    {
                        flagStop=0;
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

                        game.vkEsc();
                        if(game.isFlagBacground()) {
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
                    if(buttonStartPressed)
                    {
                        setBackground(new Color(48,171,60));
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
                setBackground(new Color(48,172,60));
                Game gameTime = new Game();
                count = gameTime.getGameTime();
                count += 50;
                flagGame = gameTime.getFlagGame();
                startTimer(count);
                buttonStartPressed=true;


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
                    for(int i = 0 ; i< 10; i++)
                    {
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

                table_result = new int[10] ;

                for(int i=0 ; i< table_result.length; i++)

                {
                    table_result[i]= result.get(i);
                }
                for(int el : table_result)
                {
                    System.out.println(el);
                }

                setBackground(Color.GRAY);

               JTextField wynikiGry = new JTextField("   5 NAJLEPSZYCH WYNIKÓW GRY: " );
                wynikiGry.setFont(new Font("czcionka", Font.BOLD, 30));
                wynikiGry.setForeground(Color.black);
                wynikiGry.setEditable(false);
                wynikiGry.setBounds(370, 50, 500, 120);
                add(wynikiGry);

               /* wynikiGry1 = new JTextField("1. WYNIK:  " + tablica_wynikow[0]);
                wynikiGry1.setFont(new Font("czcionka", Font.BOLD, 15));
                wynikiGry1.setForeground(Color.black);
                wynikiGry1.setEditable(false);
                wynikiGry1.setBounds(370, 170, 500, 20);
                add(wynikiGry1);

                wynikiGry2 = new JTextField("2. WYNIK:  " + tablica_wynikow[1]);
                wynikiGry2.setFont(new Font("czcionka", Font.BOLD, 15));
                wynikiGry2.setForeground(Color.black);
                wynikiGry2.setEditable(false);
                wynikiGry2.setBounds(370, 190, 500, 20);
                add(wynikiGry2);

                wynikiGry3 = new JTextField("3. WYNIK:  " + tablica_wynikow[2]);
                wynikiGry3.setFont(new Font("czcionka", Font.BOLD, 15));
                wynikiGry3.setForeground(Color.black);
                wynikiGry3.setEditable(false);
                wynikiGry3.setBounds(370, 210, 500, 20);
                add(wynikiGry3);


                wynikiGry4 = new JTextField("4. WYNIK:  " + tablica_wynikow[3]);
                wynikiGry4.setFont(new Font("czcionka", Font.BOLD, 15));
                wynikiGry4.setForeground(Color.black);
                wynikiGry4.setEditable(false);
                wynikiGry4.setBounds(370, 230, 500, 20);
                add(wynikiGry4);


                wynikiGry5 = new JTextField("5. WYNIK:  " + tablica_wynikow[4]);
                wynikiGry5.setFont(new Font("czcionka", Font.BOLD, 15));
                wynikiGry5.setForeground(Color.black);
                wynikiGry5.setEditable(false);
                wynikiGry5.setBounds(370, 250, 500, 20);
                add(wynikiGry5);



                setLayout(new BorderLayout());
                background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\Quiz_\\src\\bestresult.jpg"));

                add(background);


                background.setLayout(new FlowLayout());

                */

            }
        });

        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\game.jpg"));
        add(background);
        background.setVisible(true);
        background.setLayout(new FlowLayout());


    }


}


