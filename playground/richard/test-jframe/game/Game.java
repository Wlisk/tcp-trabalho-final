package game;

import tools.Constant;
import tools.Print;

//*********************************************//
// MANAGES EVRYTHING ABOUT THE GAME
//*********************************************//
public class Game implements Runnable {
    private GamePanel gamePanel;
    //private GameWindow gameWindow;
    private Thread loopThread;
    private final int FPS = 120;
    private final int UPS = 200;

    public Game() {
        gamePanel = new GamePanel();
        new GameWindow(gamePanel);

        // necessário para poder pegar eventos do mouse/teclado dentro da janela de pintura
        gamePanel.requestFocus();

        loopThread = new Thread(this);
        loopThread.start();
    }

    public void update() {
		gamePanel.updateGame();
	}

    @Override
    public void run() {
        final double TIME_PER_FRAME = Constant.BILLION / FPS;
        final double TIME_PER_UPDATE = Constant.BILLION / UPS;
        final int SECOND_MILISECOND = 1000;

        long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

        long currentTime;
        long now;

		while (true) {
			currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / TIME_PER_UPDATE;
			deltaF += (currentTime - previousTime) / TIME_PER_FRAME;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

            now = System.currentTimeMillis();
			if (now - lastCheck >= 1000) {
				lastCheck = now;
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
    }
}