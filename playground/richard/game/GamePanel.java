package game;
import javax.swing.JPanel;

import control.Keyboard;
import control.Mouse;
import tools.Print;

import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import tools.Print;
import java.util.Random;

//*********************************************//
// CONTROLS THE OBJECTS DRAWING
//*********************************************//
public class GamePanel extends JPanel {
    private int x, y;
    private Random random;

    public GamePanel() {
        x = y = 10;
        random = new Random();

        final Mouse mouse_controler = new Mouse(this);
        // configura o tamanho da area de desenho, isto é, diferente
        // do jframe que calcula o tamanho total da janela incluindo bordas da janela e outros
        setPreferredSize(new Dimension(800, 600));
        // for keyboard events
        addKeyListener(new Keyboard(this));
        // for mouse clicks and move events
        addMouseListener(mouse_controler);
        // for mouse dragging events
        addMouseMotionListener(mouse_controler);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Hello World!", x, y);

        g.drawImage(null, x, y, null);
    }

    public void changeX(int delta_x) {
        this.x += delta_x;
    }

    public void changeY(int delta_y) {
        this.y += delta_y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
