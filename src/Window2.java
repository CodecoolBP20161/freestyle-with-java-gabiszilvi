import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by lorszil on 02/11/16.
 */
public class Game extends Canvas implements Runnable{

    public static final String TITLE = "Game";
    public static final int WIDTH = 1024;
    public static final int HEIGHT = WIDTH / 4 * 3;

    private void stop(){
        System.exit(0);
    }

    @Override
    public void run(){

    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.err.println("Exiting Game");
                game.stop();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
    }

}
