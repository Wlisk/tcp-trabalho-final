package game;
import javax.swing.JFrame;

//*********************************************//
// CONTROLS THE WINDOWS OF THE GAME
//*********************************************//
public class GameWindow extends JFrame {
    private static int WIDTH = 600;
    private static int HEIGHT = 400;

    public GameWindow(GamePanel gamePanel) {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setLocationRelativeTo(null);

        // deve ser o ultimo a ser chamado
        setVisible(true);
    }
}
