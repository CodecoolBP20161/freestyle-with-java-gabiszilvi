import javax.swing.*;

public class BackGround extends Sprite implements Commons {

    private final int START_Y = 0;
    private final int START_X = 0;

    private final String bg = "bg2.png";
    private int width;

    public BackGround() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }
}