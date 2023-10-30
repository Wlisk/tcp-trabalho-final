package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.GamePanel;
import tools.Print;

public class Mouse implements MouseListener, MouseMotionListener {
    private final GamePanel gamePanel;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Print.line("mouse clicked"); // mouse pressionado e solto
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        Print.line("mouse in panel"); // entrou na janela designada
    }

    @Override
    public void mouseExited(MouseEvent event) {
        Print.line("mouse out panel"); // saiu da janela designada
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Print.line("mouse pressed"); // mouse pressionado
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Print.line("mouse released"); // mouse solto depois de pressionado
    }
    
    /* ------------------------------------------------- */
    /* Métodos de move and drag com o mouse */
    @Override
    public void mouseDragged(MouseEvent event) {
        Print.line("mouse dragged"); // arrastar (mover) mouse enquanto pressionado
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        //Print.line("mouse moved");
        this.gamePanel.setPos(event.getX(), event.getY());
    }
}
