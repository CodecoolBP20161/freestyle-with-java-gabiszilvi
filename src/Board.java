import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, Commons {

    private Dimension d;
    private GoalKeeper goalkeeper;
    private Player player;
    private Ball ball;
    private SoccerDoor soccerdoor;

    private int goalkeeperX = 150;
    private int goalkeeperY = 5;
    private int direction = -1;

    private boolean ingame = true;
    private String message = "Game Over";

    private Thread animator;

    public Board()
    {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {

        goalkeeper = new GoalKeeper();
        player = new Player();
        ball = new Ball();
        soccerdoor = new SoccerDoor(280, 120);

        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawGoalKeeper(Graphics g)
    {
        if (goalkeeper.isVisible()) {
            g.drawImage(goalkeeper.getImage(), goalkeeper.getX(), goalkeeper.getY(), this);
        }

    }

    public void drawSoccerDoor(Graphics g){
        if (soccerdoor.isVisible()){
            g.drawImage(soccerdoor.getImage(), soccerdoor.getX(), soccerdoor.getY(), this);
        }
    }

    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }


    }

    public void drawBall(Graphics g) {
        if (ball.isVisible())
            g.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);
    }


    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {
            drawSoccerDoor(g);
            drawGoalKeeper(g);
            drawPlayer(g);
            drawBall(g);

        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver()
    {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message))/2,
                BOARD_WIDTH/2);
    }

    private void animationCycle()  {


        // player

        player.act();

        // ball
        if (ball.isVisible()) {
            int ballX = ball.getX();
            int ballY = ball.getY();


            int y = ball.getY();
            y -= 4;
            if (y < 0)
                ball.die();
            else ball.setY(y);
        }

        // goalKeeper

        goalkeeper.act();


    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (ingame) {
            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
        gameOver();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            goalkeeper.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            goalkeeper.keyPressed(e);


            if (ingame) {
                if (e.isAltDown()) {
                   if (!ball.isVisible())
                        ball = new ball(x, y);
                }
            }
        }
    }
}