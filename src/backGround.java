import javax.swing.*;

/**
 * Created by shevah on 04/11/16.
 */
public class backGround extends Sprite implements Commons {

    private final int START_Y = 0;
    private final int START_X = 0;

    private final String bg = "bg.png";
    private int width;

    public backGround() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }
}