package game;

//*********************************************//
// MANAGES EVRYTHING ABOUT THE GAME
//*********************************************//
public class Game {
    GamePanel gamePanel;
    GameWindow gameWindow;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        gamePanel.requestFocus();
    }

    public void loop() {
        //while(true) {

        //}
    }
}