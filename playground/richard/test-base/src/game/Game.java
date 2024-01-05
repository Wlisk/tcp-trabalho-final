package game;

import com.raylib.Jaylib;

import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import entities.player.Player;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import scene.Scene;
import scene.button.Buttons;

public class Game {
    public static final String WINDOW_TITLE = "BOSSFIGHTER";

    public static final int 
        WINDOW_WIDTH = 600, 
        WINDOW_HEIGHT = 400, 
        FPS = 60;

    private String playerName = "Test";
    private Player player;

    private Boss currBoss;

    private GameState gameState;
    private int score;
    private Scene scene;

    public Game() throws NumberOverflowException {
        player = null;
        score = 0;

        scene = new Scene();
        gameState = GameState.MAIN_MENU;

        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();

        if(Bosses.getException() != null)
            throw Bosses.getException();
    }

    private static GameState gameStateCache = null;
    private static void setWindowTitle(GameState gameState) {
        if(gameStateCache == gameState) return;
        gameStateCache = gameState;
        
        String _stateString = gameState.toString();

        String _titleString = (_stateString.length() > 0) ? 
            (WINDOW_TITLE + ": " + _stateString) : WINDOW_TITLE;

        Jaylib.SetWindowTitle( _titleString );
    }

    public void gameLoop() throws UnknownTypeException {
        while (!Jaylib.WindowShouldClose() && gameState != GameState.GAME_END) {
            scene.drawWindow(gameState);
            setWindowTitle(gameState);

            switch(gameState) {
                case MAIN_MENU:
                    if (Buttons.PLAY_BUTTON.isMousePressed()) 
                        gameState = GameState.SELECTING_CLASS;
                    else if (Buttons.EXIT_BUTTON.isMousePressed()) 
                        gameState = GameState.GAME_END;
                    break;

                case SELECTING_CLASS:
                    if (Buttons.getClassButton(ClassType.MAGE).isMousePressed()) {
                        newGame(ClassType.MAGE);
                    }
                    else if (Buttons.getClassButton(ClassType.WARRIOR).isMousePressed()) {
                        newGame(ClassType.WARRIOR);
                    }  
                    else if (Buttons.getClassButton(ClassType.ARCHER).isMousePressed()) {
                        newGame(ClassType.ARCHER);
                    }
                    break;

                case BATTLE_START:
                    break;
                    
                default: 
                    break;
            }
        }

    }

    public void newGame(ClassType playerClass) throws UnknownTypeException {
        player = new Player(playerName, playerClass);

        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();
        score = 0;

        gameState = GameState.BATTLE_START;
    }

    public void sceneLoad() {
        scene.initializeWindow(WINDOW_TITLE);
        scene.loadTextures();
    }

    public void sceneUnload() {
        scene.unloadTextures();
    }

    public GameState getGameState() { return gameState; }
    public Scene getScene() { return scene; }
    public Boss getCurrBoss() { return currBoss; }
    public Player getPlayer() { return player; }
    public int getScore() { return score; }
}
