package control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GamePanel;
import static constant.Constants.Directions.*;


public class Keyboard implements KeyListener {
    private final GamePanel gamePanel;
    private final int DELTA_VALUE = 5;

    public Keyboard(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        // para quando a tecla é apertada (ou segurada)
        switch(event.getKeyCode()) {
            case KeyEvent.VK_A:
                //gamePanel.changeX(-DELTA_VALUE);
                gamePanel.setDirection(LEFT);
                break;
            case KeyEvent.VK_D:
                //gamePanel.changeX(DELTA_VALUE);
                gamePanel.setDirection(RIGHT);
                break;
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_W:
                // negativo, pois y começa do topo na pos (0,0) e vai para baixo
                // somar a y significa descer, subtrair significa subir
                //gamePanel.changeY(-DELTA_VALUE);
                gamePanel.setDirection(UP);
                break;
            case KeyEvent.VK_S:
                //gamePanel.changeY(DELTA_VALUE);
                gamePanel.setDirection(DOWN);
                break;
            default: break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        // para quando a tecla é solta
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
            }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // para quando é necessária?
    }
    
}
