package game;
import javax.swing.JFrame;

//*********************************************//
// CONTROLS THE WINDOWS OF THE GAME
//*********************************************//
public class GameWindow extends JFrame {
    private static int WIDTH = 600;
    private static int HEIGHT = 400;

    public GameWindow(GamePanel gamePanel) {
        // tamanho da janela inteira, incluindo bordas e outros itens (como minimizar, fechar, etc.)
        setSize(WIDTH, HEIGHT);
        // o que acontece quando clicar no icone de fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // adiciona a janela de desenho (panel) ao jframe
        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        // ajusta o tamanho da janela de acordo com o 'preferredSize' dos componentes 
        // incluidos no jframe (neste caso gamePanel apenas)
        pack();

        // deve ser o ultimo a ser chamado, deixa a janela visivel
        setVisible(true);
    }
}
