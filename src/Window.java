import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {


    private BufferedImage image;

    public Window() {
        try {
            image = ImageIO.read(new File("img/bg.png"));
            this.setContentPane(new JLabel(new ImageIcon(image)));
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.setSize(image.getWidth(),image.getHeight());
        this.setLayout(new FlowLayout());
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Fifa Over 9000");
        this.setVisible(true);

    }

}

