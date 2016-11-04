import javax.swing.JFrame;
import java.awt.image.BufferedImage;

public class Game extends JFrame implements Commons {

    private BufferedImage image;

    public Game()
    {
        add(new Board());
        setTitle("Fifa Over 9000");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGTH);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Game();
    }
}
