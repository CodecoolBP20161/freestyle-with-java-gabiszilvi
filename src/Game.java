import javax.swing.JFrame;

public class Game extends JFrame implements Commons {

    public Game()
    {
        add(new Board());
        setTitle("Sergio Ramos Penalty Simulator");
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
