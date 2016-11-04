import javax.swing.ImageIcon;

public class Ball extends Sprite {

    private String ball = "ball.png";
    private final int H_SPACE = 16;
    private final int V_SPACE = 1;

    public Ball() {
    }

    public Ball(int x, int y) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
        setImage(ii.getImage());
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}