import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Player extends Sprite implements Commons{

    // initial coordinates
    private final int START_Y = 550;
    private final int START_X = 200;

    private final String player = "player.png";
    private int width;

    public Player() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    // give the position
    public void act() {
        x += dx;
        if (x <= 2)
            x = 2;
        if (x >= BOARD_WIDTH - 2*width)
            x = BOARD_WIDTH - 2*width;
    }

    // key pressed event
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -4;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 4;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }
}