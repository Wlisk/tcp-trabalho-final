package game;
import javax.swing.JPanel;

import control.Keyboard;
import control.Mouse;
import control.MouseMotion;

import java.awt.Graphics;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import tools.Print;

//*********************************************//
// CONTROLS THE OBJECTS DRAWING
//*********************************************//
public class GamePanel extends JPanel {
    public GamePanel() {
        // for keyboard events
        addKeyListener(new Keyboard());
        // for mouse clicks and move events
        addMouseListener(new Mouse());
        // for mouse dragging events
        addMouseMotionListener(new MouseMotion());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Hello World!", 10, 10);
    }
}
