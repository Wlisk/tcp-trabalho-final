package game;

import tools.Print;

//*********************************************//
// MANAGES EVRYTHING ABOUT THE GAME
//*********************************************//
public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread loopThread;
    private final int FPS = 120;
    private final double TIME_PER_FRAME = 1_000_000_000 / FPS;
    private final int SECOND_MILISECOND = 1000;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        // necessário para poder pegar eventos do mouse/teclado dentro da janela de pintura
        gamePanel.requestFocus();
    }

    public void loop() {
        loopThread = new Thread(this);
        loopThread.start();
    }

    @Override
    public void run() {
        long last_frame_check = System.nanoTime();
        long now = last_frame_check;
        int frames = 0;
        long last_fps_check = System.currentTimeMillis();

        while(true) {
            now = System.nanoTime();

            if((now - last_frame_check) >= TIME_PER_FRAME) {
                last_frame_check = now;

                this.gamePanel.repaint();
                ++frames;
            }

            
            now = System.currentTimeMillis();

            if((now - last_fps_check) >= SECOND_MILISECOND) {
                Print.line("FPS: " + frames);
                last_fps_check = now;
                frames = 0;
            }
        }
    }
}