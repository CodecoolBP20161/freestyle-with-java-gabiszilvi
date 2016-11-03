

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

/**
 * Created by lorszil on 03/11/16.
 */
public class Sprite extends  JFrame {

    private BufferedImage image;

    public Sprite() {
        try {
            image = ImageIO.read(new File("img/goalkeeper.png"));
            this.setContentPane(new JLabel(new ImageIcon(image)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics g) {

        g.drawImage(image, 0, 0, null);



    }
}
