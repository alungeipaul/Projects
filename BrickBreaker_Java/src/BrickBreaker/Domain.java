package BrickBreaker;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.io.File;
import java.util.Random;

public class Domain extends JPanel implements KeyListener, ActionListener {
    Random rand = new Random();
    int rightBorderForResize = 527;
    int barLength = 160;
    boolean test = false;
    boolean soundz = false;
    File breakBR = new File("break.WAV");
    File winner = new File("win.WAV");
    File lost = new File("aww.WAV");
    private boolean play = false;
    private int Score = 0;
    private int nrOfBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int playerCoord = 210;
    private int ballCoordX = rand.nextInt(640) + 20;
    private int ballCoordY = 380;
    private int ballXDirection = -1;
    private int ballYDirection = -2;
    private int x = 0;
    private MapGenerate map;

    public Domain() {
        map = new MapGenerate(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    static void playSound(File Sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {
        }
    }

    public void paint(Graphics g) {
        //background - set background color & dimension in panel
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        //drawing map
        map.drawBrick((Graphics2D) g);

        //borders - set left, right and top borders & color
        g.setColor(Color.green);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(690, 0, 3, 592);

        //scores
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Score: " + Score, 590, 30);

        //bar - set starting position & color
        if (Score % 50 == 0 && Score != 0 && test == false && Score != 100) {
            barLength -= 40;
            test = true;
            rightBorderForResize += 40;
        } else if (Score % 95 == 0 && Score != 0 && test == true) {
            barLength -= 20;
            test = false;
            rightBorderForResize += 20;
        }
        g.setColor(Color.green);
        g.fillRect(playerCoord, 550, barLength, 8);

        //ball - set starting position & color
        g.setColor(Color.green);
        g.fillOval(ballCoordX, ballCoordY, 20, 20);

        //start text
        if (!play && nrOfBricks == 21) {
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press SPACE to Start", 200, 240);
        } else {
            repaint();
        }

        if (nrOfBricks <= 0) {
            if (!soundz) {
                playSound(winner);
                soundz = true;
            }
            barLength = 160;
            rightBorderForResize = 527;
            test = false;
            play = false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Winner Winner Chicken Dinner: Score: " + Score, 50, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart. ", 230, 350);

        }

        if (ballCoordY > 570) {
            if (!soundz) {
                playSound(lost);
                soundz = true;
            }
            rightBorderForResize = 527;
            barLength = 160;
            play = false;
            test = false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + Score, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart. ", 230, 350);
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        timer.start(); //start time;
        if (play) {
            if (new Rectangle(ballCoordX, ballCoordY, 20, 20).intersects(new Rectangle(playerCoord, 550, barLength, 8))) {
                x++;
                if (x == 3) {
                    ballYDirection = -5;
                    x = 0;
                } else ballYDirection = -2;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWIdth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWIdth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballCoordX, ballCoordY, 20, 20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            playSound(breakBR);
                            map.setBrickValue(0, i, j);
                            nrOfBricks--;
                            Score += 5;

                            if (ballCoordX + 10 <= brickRect.x || ballCoordX + 1 >= brickRect.x + brickRect.width) {
                                ballXDirection = -ballXDirection;
                            } else {
                                ballYDirection = -ballYDirection;
                            }

                            break A;
                        }
                    }
                }
            }

            ballCoordX += ballXDirection; //movement
            ballCoordY += ballYDirection; //movement
            if (ballCoordX < 0) //left border
                ballXDirection = -ballXDirection;
            if (ballCoordY < 0) //top border
                ballYDirection = -ballYDirection;
            if (ballCoordX > 670) //right border
                ballXDirection = -ballXDirection;
        }

        repaint(); //recall paint method and draw all the elements back
        //procedure needs to be recalled in order to 'repaint' the bar after left or right movement
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == keyEvent.VK_SPACE && !play)
            play = true;
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            //play = true;
            if (play)
                if (playerCoord >= rightBorderForResize)
                    playerCoord = rightBorderForResize;
                else
                    moveRight();
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            //play = true;
            if (play)
                if (playerCoord <= 6)
                    playerCoord = 6;
                else
                    moveLeft();
            //play = true;
        }

        if (keyEvent.getKeyCode() == keyEvent.VK_ENTER) {
            if (!play) {
                //play = true;
                soundz = false;
                ballCoordX = rand.nextInt(640) + 20;
                ;
                ballCoordY = 380;
                ballXDirection = -1;
                ballYDirection = -2;
                playerCoord = 310;
                Score = 0;
                nrOfBricks = 21;
                map = new MapGenerate(3, 7);
                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerCoord += 20;
    }

    public void moveLeft() {
        play = true;
        playerCoord -= 20;
    }


}
















