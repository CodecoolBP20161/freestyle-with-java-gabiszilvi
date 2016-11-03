import javax.swing.ImageIcon;


public class SoccerDoor extends Sprite {

    private final String door = "soccer.png";

    public SoccerDoor(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(door));
        setImage(ii.getImage());

    }

    public void act(int direction) {
        this.x += direction;
    }


}