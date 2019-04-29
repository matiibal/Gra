package SNAKE;

import sun.reflect.annotation.ExceptionProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private int[] snakeLenghtX = new int[750];
    private int[] snakeLenghtY = new int[750];
    private ImageIcon rightHead, leftHead, upHead, downHead, body;

    private Timer timer;
    private int delay = 110;
    private int gameTime = 10;
    private int countGame = 10;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private int lenghtDefaultSnake = 3;
    private int scoreApple, scoreBanana, scoreOrange;


    boolean cointains(int[] tab, int element) {
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] == element) {
                return true;
            }

        }
        return false;
    }

    private int[] foodXPosition = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
            475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] foodYPosition = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
            350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};


    private ImageIcon foodApple, foodBanana, foodOrange;

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    private int score = 0;
    private int moves = 0;


    public Game() {
        setFocusable(false);


        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public int getGameTime() {
        return gameTime;
    }

    public int getCountGame() {
        return countGame;
    }

    public int getMoves() {
        return moves;
    }

    public void paint(Graphics obj) {


        if (moves == 0) {
            snakeLenghtX[2] = 50;
            snakeLenghtX[1] = 75;
            snakeLenghtX[0] = 100;


            snakeLenghtY[2] = 100;
            snakeLenghtY[1] = 100;
            snakeLenghtY[0] = 100;

        }

        obj.setColor(Color.blue);
        obj.drawRect(24, 74, 851, 577);
        obj.setColor(Color.black);
        obj.fillRect(25, 75, 850, 650);


        obj.setColor(Color.blue);
        obj.drawRect(24, 10, 851, 50);
        obj.setColor(Color.black);
        obj.fillRect(24, 10, 850, 50);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("X " + scoreApple, 240, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("X " + scoreBanana, 390, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("X " + scoreOrange, 540, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("SCORE: " + score, 700, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("SNAKE", 50, 45);

        rightHead = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\right.png");
        rightHead.paintIcon(this, obj, snakeLenghtX[0], snakeLenghtY[0]);

        for (int i = 0; i < lenghtDefaultSnake; i++) {
            if (i == 0 && right) {
                rightHead = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\right.png");
                rightHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
            } else if (i == 0 && left) {
                leftHead = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\left.png");
                leftHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
            } else if (i == 0 && up) {
                upHead = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\up.png");
                upHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
            } else if (i == 0 && down) {
                downHead = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\down.png");
                downHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
            } else if (i != 0) {
                body = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\body.png");
                body.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
            }

        }
        foodApple = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\food.png");
        foodBanana = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\foodBanana.png");
        foodOrange = new ImageIcon("C:\\Users\\mateu\\IdeaProjects\\SNAKE\\src\\foodOrange.png");

        foodApple.paintIcon(this, obj, 200, 25);
        foodBanana.paintIcon(this, obj, 350, 25);
        foodOrange.paintIcon(this, obj, 500, 25);


        if (cointains(snakeLenghtY, foodYPosition[ypos]) && cointains(snakeLenghtX, foodXPosition[xpos])) {
            // System.out.println("Zła pozycja");
        }


        if (foodXPosition[xpos] == snakeLenghtX[0] && foodYPosition[ypos] == snakeLenghtY[0]) {

            if (score % 15 == 0 && score != 0) {
                score += 33;

                scoreBanana++;
                System.out.println("Banany= " + scoreBanana);

            } else if (score % 18 == 0 && score != 0) {
                score += 66;

                scoreOrange++;
                System.out.println("Pomarancze= " + scoreOrange);


            } else {
                score += 3;
                lenghtDefaultSnake++;
                scoreApple++;
                System.out.println("Jabłka = " + scoreApple);
            }


            //System.out.println(cointains(snakeLenghtX,foodXPosition[xpos]));

            //boolean goodPosition = false;
            // goodPosition = cointains(snakeLenghtX, foodXPosition[xpos]);

            //for(int a:snakeLenghtX)
            //  {
            //  if(a!=0) System.out.println(a);
            //  }
            //  System.out.println(foodXPosition[xpos]);

            xpos = random.nextInt(34);
            ypos = random.nextInt(23);


        }

        if (score % 15 == 0 && score != 0) {
            foodBanana.paintIcon(this, obj, foodXPosition[xpos], foodYPosition[ypos]);

        } else if (score % 18 == 0 && score != 0) {
            foodOrange.paintIcon(this, obj, foodXPosition[xpos], foodYPosition[ypos]);
        } else {
            foodApple.paintIcon(this, obj, foodXPosition[xpos], foodYPosition[ypos]);
        }

        for (int b = 1; b < lenghtDefaultSnake; b++) {
            if (snakeLenghtY[b] == snakeLenghtY[0] && snakeLenghtX[b] == snakeLenghtX[0]) {

                right = false;
                left = false;
                up = false;
                down = false;
                timer.stop();
                countGame = 0;


                obj.setColor(Color.white);
                obj.setFont(new Font("MyFont", Font.BOLD, 50));
                obj.drawString("GAME OVER", 300, 300);

                obj.setColor(Color.white);
                obj.setFont(new Font("MyFont", Font.BOLD, 20));
                obj.drawString("Press ENTER to start again", 325, 350);
            }

        }
        obj.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int r = lenghtDefaultSnake - 1; r >= 0; r--) {
                snakeLenghtY[r + 1] = snakeLenghtY[r];
            }
            for (int r = lenghtDefaultSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeLenghtX[r] = snakeLenghtX[r] + 25;

                } else {
                    snakeLenghtX[r] = snakeLenghtX[r - 1];
                }
                if (snakeLenghtX[r] > 850) {
                    snakeLenghtX[r] = 25;
                }

                repaint();
            }
        }
        if (left) {
            for (int r = lenghtDefaultSnake - 1; r >= 0; r--) {
                snakeLenghtY[r + 1] = snakeLenghtY[r];
            }
            for (int r = lenghtDefaultSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeLenghtX[r] = snakeLenghtX[r] - 25;

                } else {
                    snakeLenghtX[r] = snakeLenghtX[r - 1];
                }
                if (snakeLenghtX[r] < 25) {
                    snakeLenghtX[r] = 850;
                }

                repaint();
            }
        }
        if (up) {
            for (int r = lenghtDefaultSnake - 1; r >= 0; r--) {
                snakeLenghtX[r + 1] = snakeLenghtX[r];
            }
            for (int r = lenghtDefaultSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeLenghtY[r] = snakeLenghtY[r] - 25;

                } else {
                    snakeLenghtY[r] = snakeLenghtY[r - 1];
                }
                if (snakeLenghtY[r] < 75) {
                    snakeLenghtY[r] = 700;
                }

                repaint();
            }
        }


        if (down) {
            for (int r = lenghtDefaultSnake - 1; r >= 0; r--) {
                snakeLenghtX[r + 1] = snakeLenghtX[r];
            }
            for (int r = lenghtDefaultSnake; r >= 0; r--) {
                if (r == 0) {
                    snakeLenghtY[r] = snakeLenghtY[r] + 25;

                } else {
                    snakeLenghtY[r] = snakeLenghtY[r - 1];
                }
                if (snakeLenghtY[r] > 700) {
                    snakeLenghtY[r] = 75;
                }

                repaint();
            }
        }
    }


    public void vkRight() {

        moves++;
        right = true;
        if (!left) {
            right = true;
        } else {
            right = false;
            left = true;
        }
        up = false;
        down = false;
    }

    public void vkLeft() {

        moves++;
        left = true;
        if (!right) {
            left = true;
        } else {
            left = false;
            right = true;
        }
        up = false;
        down = false;
    }

    public void vkUp() {

        moves++;
        up = true;
        if (!down) {
            up = true;
        } else {
            up = false;
            down = true;
        }
        left = false;
        right = false;
    }

    public void vkDown() {

        moves++;
        down = true;
        if (!up) {
            down = true;
        } else {

            up = true;
            down = false;
        }
        left = false;
        right = false;

    }

    public void vkEnter() {
        if (!timer.isRunning()) {
            moves = 0;
            score = 0;
            scoreOrange = 0;
            scoreBanana = 0;
            scoreApple = 0;
            lenghtDefaultSnake = 3;
            timer.start();
        }
        repaint();

    }
}
