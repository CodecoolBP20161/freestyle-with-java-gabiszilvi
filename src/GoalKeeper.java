import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class GoalKeeper extends Sprite implements Commons{

    // initial coordinates
    private final int START_Y = 140;
    private final int START_X = 480;

    private final String keeper = "goalkeeper.png";
    private int width;

    public GoalKeeper() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(keeper));

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

    // key pressed thing
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A)
        {
            dx = -4;
        }

        if (key == KeyEvent.VK_D)
        {
            dx = 4;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_D)
        {
            dx = 0;
        }
    }
}