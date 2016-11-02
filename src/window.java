/**
 * Created by lorszil on 02/11/16.
 */



import java.awt.*;
import javax.swing.*;
public class window extends JFrame{

    private static void drawWindow(){
        JFrame frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(1024, 768));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                drawWindow();
            }
        });
    }
}