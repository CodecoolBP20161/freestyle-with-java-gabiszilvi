import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;


public class Board extends JPanel implements Runnable, Commons {

    private Dimension d;
    private GoalKeeper goalkeeper;
    private Player player;
    private Ball ball;
    private BackGround background;

    private boolean ingame = true;

    private Thread animator;

    public Board()
    {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);

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
        background = new BackGround();

        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    // drawing methods
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

        if (ingame) {
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
            y -= 6;
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
                System.out.println("Interrupted");
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