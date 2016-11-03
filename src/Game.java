import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JPanel {


    private Image player;
    private Image bg;



    public Game() throws Exception {
        player = ImageIO.read(new File("img/goalkeeper.png"));
        GoalKeeper keeper = new GoalKeeper();
        switch (keeper.status) {
            case 0 : player = ImageIO.read(new File("img/goalkeeper.png"));
                break;
            case 1 : player = ImageIO.read(new File("img/goalkeeper2.png"));
                break;
            case 2 : player = ImageIO.read(new File("img/goalkeeper3.png"));
                break;
            case 3 : player = ImageIO.read(new File("img/goalkeeper4.png"));
                break;
            case 4 : player = ImageIO.read(new File("img/goalkeeper5.png"));
                break;
        }
        System.out.println(keeper.status);

        bg = ImageIO.read(new File("img/bg.png"));

        setFocusable(true);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
        g.drawImage(player, 450, 320, this);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame f = new JFrame("Fifa over 9000");
                    f.setSize(1024, 768);
                    f.setResizable(false);
                    f.setVisible(true);
                    f.setContentPane(new Game());
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}