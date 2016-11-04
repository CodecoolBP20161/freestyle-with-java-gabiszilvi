import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;


import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, Commons {

    private Dimension d;
    private GoalKeeper goalkeeper;
    private Player player;
    private Ball ball;
    private SoccerDoor soccerdoor;
    private backGround background;

    private boolean ingame = true;


    private Thread animator;

    public Board()
    {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        //setBackground(Color.black);

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
        background = new backGround();

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

    public void drawBG(Graphics g) {
        if (background.isVisible()) {
            g.drawImage(background.getImage(), background.getX(), background.getY(), this);
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

        //g.setColor(Color.black);
        //g.fillRect(0, 0, d.width, d.height);

        if (ingame) {
            drawSoccerDoor(g);
            drawBG(g);
            drawGoalKeeper(g);
            drawPlayer(g);
            drawBall(g);

        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
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
                if (e.isShiftDown()) {
                   if (!ball.isVisible())
                        ball = new Ball(x, y);
                }
            }
        }
    }
}