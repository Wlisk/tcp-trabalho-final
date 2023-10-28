package control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    @Override
    public void keyTyped(KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_A:
                break;
            case KeyEvent.VK_D:
                break;
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_SPACE:
                break;
            default: break;
        }
    }
    
}
