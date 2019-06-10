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
 * @author Mateusz Bałazy
 * Gra Snake, realizacja gry tzn. jej funkcjonalnosci, itd.
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
    private boolean flagKey = false, flag1=false, flag2=false, flag3=false, flag4=false;





   private int isGameMode() {
        return gameMode;
    }

    private int gameMode = 2;

    /**
     * sprawdza czy jest tlo domyslne
     * @return flagBacground
     */
    public boolean isFlagBacground() {
        return flagBacground;
    }

    /**
     * ustawienie domyslnego tla
     * @param flagBacground
     */
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


        if (!timer.isRunning() && Frame.flagStop == 0) {
            //zapis wynikow
            saveResult();
            flagBacground = true;
            setVisible(false);
            Frame.statusGame = true;
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
        if (!timer.isRunning() && Frame.flagStop == 0) {
            saveResult();

            Frame.statusGame = true;
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
                    Frame.startTimer(defaultCount + 30);
                } else if (levelFlag == 2) {
                    Frame.startTimer(defaultCount + 15);

                } else if (levelFlag == 3) {
                    Frame.startTimer(defaultCount);

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
                    Frame.count += 5;
                    score += 66;
                    if (scoreOrange % 20 == 0 && scoreOrange != 0) {
                        score += 660;
                        Frame.count += 30;
                    }
                } else if (gameMode == 1) {

                    if (levelFlag == 1) score += 66;
                    if (levelFlag == 2) score += 33;
                    if (levelFlag == 3) score += 21;
                }
                scoreBanana++;


            } else if (score % 18 == 0 && score != 0) {

                if (gameMode == 2) {
                    Frame.count += 3;
                    score += 33;
                    if (scoreBanana % 20 == 0 && scoreBanana != 0) {
                        score += 330;
                        Frame.count += 30;
                    }
                } else if (gameMode == 1) {
                    if (levelFlag == 1) score += 33;
                    else if (levelFlag == 2) score += 18;
                    else if (levelFlag == 3) score += 9;
                }
                scoreOrange++;


            } else {

                if (gameMode == 2) {
                    Frame.count += 1;
                    score += 3;
                    if (scoreApple % 20 == 0 && scoreApple != 0) {
                        score += 150;
                        Frame.count += 15;
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
            if (snakeLenghtY[b] == snakeLenghtY[0] && snakeLenghtX[b] == snakeLenghtX[0] || !Frame.statusGame) {


                Frame.flagStop = 0;
                right = false;
                left = false;
                up = false;
                down = false;
                timer.stop();

                if (gameMode == 2) {
                    Frame.timerGame.stop();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * ruch weza w prawo
     */
      public synchronized void rightMove() {


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
               flag1=true;
           }
       }

/**
 * ruch weza w lewo
 */
       public synchronized void leftMove() {
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
flag2=true;
           }
       }

/**
 * ruch weza w gore
 */
       public synchronized void upMove() {

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
           flag3=true;
       }

    /**
     * ruch weza w dol
     */
       public  synchronized void downMove() {
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
           flag4=true;

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
    public synchronized void  vkRight() {

        moves++;
        pressRight = true;
        if (!left ) {
            right = true;
            up = false;
            down = false;
            left = false;
        }

    }
    /**
     * Klawiatura - ruch w lewo
     */
    public synchronized void vkLeft() {
        pressLeft = true;
        if (moves > 0) {
            moves++;

            if (!right) {
                left = true;
                up = false;
                down = false;
                right = false;
            }

        }

    }

    /**
     * Klawiatura - ruch w gore
     */
    public synchronized void vkUp() {
        pressUp = true;
        if (moves > 0) {

            moves++;
            if (!down || !left || !right) {
                up = true;
                left = false;
                right = false;
                down = false;
            }
        }
    }

    /**
     * Klawiatura - ruch w dol
     */
    public synchronized void vkDown() {
        pressDown = true;
        if (moves > 0) {
            moves++;
            if (!up) {
                down = true;
                up = false;
                left = false;
                right = false;
            }
        }
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
     * @param obj
     */
    public void paint(Graphics obj) {
        repaint();
        countGame = Frame.count;
        gameMode = Frame.modeFlag;
        levelFlag = Frame.levelFlag;


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


        appearanceFlag = Frame.apperanceFlag;
        //wyglad gry, trzy tryby
        if (appearanceFlag == 1) {

            rightHead = new ImageIcon("right.png");

            headR = rightHead.getImage();
            obj.drawImage(headR, snakeLenghtX[0], snakeLenghtY[0], this);


            for (int i = 0; i < lenghtDefaultSnake; i++) {

                if (i == 0 && right) {
                    moves++;
                    rightHead = new ImageIcon("right.png");
                    headR = rightHead.getImage();
                    repaint();

                    obj.drawImage(headR, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();

                }  if (i == 0 && left) {
                    leftHead = new ImageIcon("left.png");
                    headL = leftHead.getImage();
                    repaint();
                    obj.drawImage(headL, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();

                }  if (i == 0 && up) {
                    upHead = new ImageIcon("up.png");
                    headU = upHead.getImage();
                    repaint();
                    obj.drawImage(headU, snakeLenghtX[i], snakeLenghtY[i], this);
                    repaint();

                } if (i == 0 && down) {
                    downHead = new ImageIcon("down.png");

                    headD = downHead.getImage();
                    repaint();
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
                        moves++;
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

            }
         else if (left) {
            repaint();
            leftMove();

            }
        else if (up) {
            repaint();
           upMove();

            }
        else if (down) {
            repaint();

            downMove();

            }

    }



    /**
     * Klawiatura - klawisz spacja - gra wstrzymana
     */
  public  void vkSpace() {
        // System.out.println("FLAG STOP" + Frame.flagStop);
        if (Frame.flagStop == 0) {
            timer.stop();
            if (gameMode == 2) {
                Frame.timerGame.stop();
            }
        } else {
            timer.restart();
            if (gameMode == 2) {
                Frame.timerGame.restart();
            }
        }
    }


}

