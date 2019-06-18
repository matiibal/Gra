package SNAKE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Gra Snake, realizacja gry tzn. jej funkcjonalnosci, itd.
 * @author Mateusz Bałazy
 *
 */


public class Game extends JPanel implements ActionListener {
    private int[] snakeLenghtX = new int[750];
    private int[] snakeLenghtY = new int[750];
    private ImageIcon rightHead, leftHead, upHead, downHead, body;
    private ArrayList<Integer> resultList;
    private Timer timer;
    private int delay = 110;
    private int gameTime = 30;
    private int countGame = 10;
    private int defaultCount = 30;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private int lenghtDefaultSnake = 3;
    private int scoreApple, scoreBanana, scoreOrange;
    private boolean pressRight = false, pressLeft = false, pressUp = false, pressDown = false;
    private int appearanceFlag = 1;
    private int levelFlag = 1;
    private Image foodB, foodA, foodO, headL, headU, headR, headD, bodyS;
    private boolean flagRight = false, flagLeft = false, flagUp = false, flagDown = false;





   private int isGameMode() {
        return gameMode;
    }

    private int gameMode = 2;


    public boolean isFlagBacground() {
        return flagBacground;
    }


    public void setFlagBacground(boolean flagBacground) {
        this.flagBacground = flagBacground;
    }

    private boolean flagBacground;


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

    private int flagGame = 0;

    public int getFlagGame() {
        return flagGame;
    }

    /**
     * Klawiatura - klawisz Esc - powrot do menu glownego
     */
    public void vkEsc() {


        if (!timer.isRunning() && Menu.flagStop == 0) {
            //zapis wynikow
            saveResult();
            flagBacground = true;
            setVisible(false);
            Menu.statusGame = true;
            moves = 0;
            score = 0;
            scoreOrange = 0;
            scoreBanana = 0;
            scoreApple = 0;
            lenghtDefaultSnake = 3;
            timer.start();

            //Frame.timerGame.start();

        }
        repaint();

    }
    /**
     * Klawiatura - klawisz Enter - gra od nowa
     */
    public void vkEnter() {
        if (!timer.isRunning() && Menu.flagStop == 0) {
            saveResult();

            Menu.statusGame = true;
            moves = 0;
            score = 0;
            scoreOrange = 0;
            scoreBanana = 0;
            scoreApple = 0;
            lenghtDefaultSnake = 3;


            timer.start();

            //Frame.timerGame.start();
            if (gameMode == 2) {
                if (levelFlag == 1) {
                    Menu.startTimer(defaultCount + 30);
                } else if (levelFlag == 2) {
                   Menu.startTimer(defaultCount + 15);

                } else if (levelFlag == 3) {
                    Menu.startTimer(defaultCount);

                }
            }
        }
        repaint();

    }

    /**
     * sprawdza w jakim miejscu pojawia sie owoc, niepozwala na pojawienie sie owocu na "wężu"
     */
    public void checkFruit() {
        //niepozwolenie na pojawianiu sie jablek na wezu
        for (int i = 1; i < lenghtDefaultSnake; i++) {
            if (foodXPosition[xpos] == snakeLenghtX[i] && foodYPosition[ypos] == snakeLenghtY[i]) {
                xpos = random.nextInt(34);
                ypos = random.nextInt(23);
            }
        }
    }

    /**
     * zjedzenie owocu, dodawanie odpowiednich punktów do wyniku
     */
    public void eatFruit() {
        //jesli zje owoc tzn. glowa bedzie na pozycji owocu
        if (foodXPosition[xpos] == snakeLenghtX[0] && foodYPosition[ypos] == snakeLenghtY[0]) {

            if (score % 15 == 0 && score != 0) {

                if (gameMode == 2) {
                    Menu.count += 5;
                    score += 66;
                    if (scoreOrange % 20 == 0 && scoreOrange != 0) {
                        score += 660;
                        Menu.count += 30;
                    }
                } else if (gameMode == 1) {

                    if (levelFlag == 1) score += 66;
                    if (levelFlag == 2) score += 33;
                    if (levelFlag == 3) score += 21;
                }
                scoreBanana++;


            } else if (score % 18 == 0 && score != 0) {

                if (gameMode == 2) {
                    Menu.count += 3;
                    score += 33;
                    if (scoreBanana % 20 == 0 && scoreBanana != 0) {
                        score += 330;
                        Menu.count += 30;
                    }
                } else if (gameMode == 1) {
                    if (levelFlag == 1) score += 33;
                    else if (levelFlag == 2) score += 18;
                    else if (levelFlag == 3) score += 9;
                }
                scoreOrange++;


            } else {

                if (gameMode == 2) {
                    Menu.count += 1;
                    score += 3;
                    if (scoreApple % 20 == 0 && scoreApple != 0) {
                        score += 150;
                        Menu.count += 15;
                    }
                } else if (gameMode == 1) {
                    score += 3;
                }

                lenghtDefaultSnake++;
                scoreApple++;

            }


            xpos = random.nextInt(34);
            ypos = random.nextInt(23);


        }


    }

    /**
     * sprawdza czy nastapila kolizja
     * @return true
     */
    public boolean checkCollision() {
        for (int b = 1; b < lenghtDefaultSnake; b++) {

            int z;
            //jezeli k=ktorys z elementow poza glowa bedzie jej rowny to zakoncz gre
            if (snakeLenghtY[b] == snakeLenghtY[0] && snakeLenghtX[b] == snakeLenghtX[0] || !Menu.statusGame) {


                Menu.flagStop = 0;
                right = false;
                left = false;
                up = false;
                down = false;
                timer.stop();

                if (gameMode == 2) {
                    Menu.timerGame.stop();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * ruch weza w prawo
     */
      public void rightMove() {


               if (lenghtDefaultSnake - 1 + 1 >= 0)
               System.arraycopy(snakeLenghtY, 0, snakeLenghtY, 1, lenghtDefaultSnake - 1 + 1);

           for (int r = lenghtDefaultSnake; r >= 0; r--) {

               if (r == 0) {
                   snakeLenghtX[r] = snakeLenghtX[r] + 25;

               }
               else {
                   snakeLenghtX[r] = snakeLenghtX[r - 1];
               }


               if (snakeLenghtX[r] > 850) {
                   snakeLenghtX[r] = 25;
               }

               pressRight = false;

           }
       }

/**
 * ruch weza w lewo
 */
       public void leftMove() {
           if (lenghtDefaultSnake - 1 + 1 >= 0)
               System.arraycopy(snakeLenghtY, 0, snakeLenghtY, 1, lenghtDefaultSnake - 1 + 1);
           for (int r = lenghtDefaultSnake; r >= 0; r--) {
               if (r == 0) {
                   snakeLenghtX[r] = snakeLenghtX[r] - 25;

               } else {
                   snakeLenghtX[r] = snakeLenghtX[r - 1];
               }
               if (snakeLenghtX[r] < 25) {
                   snakeLenghtX[r] = 850;
               }
               pressLeft = false;

           }
       }

/**
 * ruch weza w gore
 */
       public void upMove() {

           if (lenghtDefaultSnake - 1 + 1 >= 0)
               System.arraycopy(snakeLenghtX, 0, snakeLenghtX, 1, lenghtDefaultSnake - 1 + 1);
           for (int r = lenghtDefaultSnake; r >= 0; r--) {
               if (r == 0) {
                   snakeLenghtY[r] = snakeLenghtY[r] - 25;

               } else {
                   snakeLenghtY[r] = snakeLenghtY[r - 1];
               }
               if (snakeLenghtY[r] < 75) {
                   snakeLenghtY[r] = 700;
               }
           }

       }

    /**
     * ruch weza w dol
     */
       public  void downMove() {
           if (lenghtDefaultSnake - 1 + 1 >= 0)
               System.arraycopy(snakeLenghtX, 0, snakeLenghtX, 1, lenghtDefaultSnake - 1 + 1);
           for (int r = lenghtDefaultSnake; r >= 0; r--) {
               if (r == 0) {
                   snakeLenghtY[r] = snakeLenghtY[r] + 25;

               } else {
                   snakeLenghtY[r] = snakeLenghtY[r - 1];
               }
               if (snakeLenghtY[r] > 700) {
                   snakeLenghtY[r] = 75;
               }
           }


       }

    /**
     * zapis wynikow do pliku
     */
    public void saveResult()
       {
           try {
               FileWriter save = new FileWriter("result.txt", true);
               save.append(String.valueOf(score)).append("\r\n");
               //   System.lineSeparator();
               save.close();
           } catch (IOException ex) {
               System.out.println("File errror");
           }



       }

    /**
     * parametry gry na starcie
      */
    public void startGame()
       {
           if (levelFlag == 2 && gameMode == 2) score = 261;
           if (levelFlag == 3 && gameMode == 2) score = 621;

           snakeLenghtX[2] = 50;
           snakeLenghtX[1] = 75;
           snakeLenghtX[0] = 100;

           snakeLenghtY[2] = 100;
           snakeLenghtY[1] = 100;
           snakeLenghtY[0] = 100;

           rightHead = new ImageIcon("right.png");

           headR = rightHead.getImage();

       }

    /**
     * Klawiatura - ruch w prawo
     */
    public void  vkRight() {

        flagRight=true;
        pressRight = true;
        if (!left ) {

            right = true;
            repaint();
            up = false;
            down = false;
            left = false;


        }
        moves++;
        flagRight=false;

    }
    /**
     * Klawiatura - ruch w lewo
     */
    public void vkLeft() {
        pressLeft = true;
        if (!flagRight  && moves>0) {


            if (!right) {

                left = true;
                repaint();
                up = false;
                down = false;
                right = false;

            }
            moves++;
        }

    }

    /**
     * Klawiatura - ruch w gore
     */
    public synchronized void vkUp() {
        pressUp = true;



            if (!down) {

                up = true;
                left = false;
                right = false;
                down = false;

            }
            moves++;
        }


    /**
     * Klawiatura - ruch w dol
     */
    public synchronized void vkDown() {
        pressDown = true;


            if (!up) {

                down = true;
                repaint();
                up = false;
                left = false;
                right = false;

            }
            moves++;
        }

    public Game() {
        setFocusable(false);
        setBackground(new Color(48, 172, 60));
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    /**
     * @return gameTime - czas gry( 60,45,30)
     **/

    public int getGameTime() {
        return gameTime;
    }

    /**
     * metoda rysujaca pole gry
     * @param obj rysuje na JPanelu elementy gry
     */
    public void paint(Graphics obj) {
        repaint();
        countGame = Menu.count;
        gameMode = Menu.modeFlag;
        levelFlag = Menu.levelFlag;


        if (moves == 0) {
            startGame();
            obj.drawImage(headR, snakeLenghtX[0], snakeLenghtY[0], this);

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
        obj.drawString("X " + scoreApple, 350, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("X " + scoreBanana, 490, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("X " + scoreOrange, 640, 45);

        obj.setColor(Color.white);
        obj.setFont(new Font("Arial", Font.BOLD, 20));
        obj.drawString("SCORE: " + score, 720, 45);


        //jesli tryb czasowy
        if (gameMode == 2) {
            obj.setColor(Color.white);
            obj.setFont(new Font("Arial", Font.BOLD, 20));
            obj.drawString("Game over in: " + countGame + " seconds", 40, 45);
        } else if (gameMode == 1) { //jesli tryb czasowy
            obj.setColor(Color.white);
            obj.setFont(new Font("Arial", Font.BOLD, 20));
            obj.drawString("SNAKE FREE MODE ", 40, 45);
        }


        appearanceFlag = Menu.apperanceFlag;
        //wyglad gry, trzy tryby
        if (appearanceFlag == 1) {

            rightHead = new ImageIcon("right.png");

            headR = rightHead.getImage();
            obj.drawImage(headR, snakeLenghtX[0], snakeLenghtY[0], this);


            for (int i = 0; i < lenghtDefaultSnake; i++) {

                if (i == 0 && right) {

                    rightHead = new ImageIcon("right.png");
                    headR = rightHead.getImage();

                    obj.drawImage(headR, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();

                }  else if (i == 0 && left) {

                    leftHead = new ImageIcon("left.png");
                    headL = leftHead.getImage();

                    obj.drawImage(headL, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();

                }  else if (i == 0 && up) {
                    upHead = new ImageIcon("up.png");
                    headU = upHead.getImage();

                    obj.drawImage(headU, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();

                } else if (i == 0 && down) {
                    downHead = new ImageIcon("down.png");

                    headD = downHead.getImage();
                    obj.drawImage(headD, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();
                }  if (i != 0) {
                    body = new ImageIcon("body.png");


                    bodyS = body.getImage();
                    obj.drawImage(bodyS, snakeLenghtX[i], snakeLenghtY[i], this);
                }

            }
        } else if (appearanceFlag == 2) {
            rightHead = new ImageIcon("right2.png");
            rightHead.paintIcon(this, obj, snakeLenghtX[0], snakeLenghtY[0]);
            for (int i = 0; i < lenghtDefaultSnake; i++) {
                if (i == 0 && right && !left && !up && !down) {

                    rightHead = new ImageIcon("right2.png");
                    rightHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                } else if (i == 0 && left && !right && !up && !down) {

                    leftHead = new ImageIcon("left2.png");
                    leftHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                } else if (i == 0 && up && !down && !left && !right) {

                    upHead = new ImageIcon("up2.png");
                    upHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                } else if (i == 0 && down && !up && !left && !right) {
                    downHead = new ImageIcon("down2.png");
                    downHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                } else if (i != 0) {
                    body = new ImageIcon("body2.png");
                    body.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                }

            }

        } else if (appearanceFlag == 3) {
            {
                rightHead = new ImageIcon("right3.png");
                rightHead.paintIcon(this, obj, snakeLenghtX[0], snakeLenghtY[0]);

                for (int i = 0; i < lenghtDefaultSnake; i++) {
                    if (i == 0 && right) {
                       // moves++;
                        rightHead = new ImageIcon("right3.png");
                        rightHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                    } else if (i == 0 && left) {
                        leftHead = new ImageIcon("left3.png");
                        leftHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                    } else if (i == 0 && up) {
                        upHead = new ImageIcon("up3.png");
                        upHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                    } else if (i == 0 && down) {
                        downHead = new ImageIcon("down3.png");
                        downHead.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                    } else if (i != 0) {
                        body = new ImageIcon("body3.png");
                        body.paintIcon(this, obj, snakeLenghtX[i], snakeLenghtY[i]);
                    }

                }
            }
        }


        foodApple = new ImageIcon("food.png");
        foodBanana = new ImageIcon("foodBanana.png");
        foodOrange = new ImageIcon("foodOrange.png");


        //ramka
        foodApple.paintIcon(this, obj, 310, 25);
        foodBanana.paintIcon(this, obj, 450, 25);
        foodOrange.paintIcon(this, obj, 600, 25);


        checkFruit();
        eatFruit();


        if (score % 15 == 0 && score != 0) {
            foodB = foodBanana.getImage();
            obj.drawImage(foodB, foodXPosition[xpos], foodYPosition[ypos], this);

        } else if (score % 18 == 0 && score != 0) {
            foodO = foodOrange.getImage();
            obj.drawImage(foodO, foodXPosition[xpos], foodYPosition[ypos], this);
        } else {
            foodA = foodApple.getImage();
            obj.drawImage(foodA, foodXPosition[xpos], foodYPosition[ypos], this);
        }

        boolean endGame = checkCollision();



        if (endGame) {
            obj.setColor(Color.white);
            obj.setFont(new Font("MyFont", Font.BOLD, 50));
            obj.drawString("GAME OVER", 300, 300);

            obj.setColor(Color.white);
            obj.setFont(new Font("MyFont", Font.BOLD, 20));
            obj.drawString("Press ENTER to start again", 325, 350);
            obj.drawString("or ESC to back menu", 345, 380);


            Toolkit.getDefaultToolkit().sync();
            obj.dispose();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        //ruch weza po nacisnieciu odpowiednich klawiszy
        if (right) {
            repaint();
            rightMove();
            repaint();
            }
         else if (left) {
            repaint();
            leftMove();
            repaint();
            }
        else if (up) {
            repaint();
           upMove();
            repaint();
            }
        else if (down) {
            repaint();

            downMove();
            repaint();
            }

    }



    /**
     * Klawiatura - klawisz spacja - gra wstrzymana
     */
  public  void vkSpace() {
        // System.out.println("FLAG STOP" + Frame.flagStop);
        if (Menu.flagStop == 0) {
            timer.stop();
            if (gameMode == 2) {
                Menu.timerGame.stop();
            }
        } else {
            timer.restart();
            if (gameMode == 2) {
                Menu.timerGame.restart();
            }
        }
    }


}

