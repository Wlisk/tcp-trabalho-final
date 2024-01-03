package game;

import com.raylib.Jaylib;

import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import entities.player.Player;
import scene.Scene;
import scene.button.Buttons;
import utils.exceptions.NumberOverflowException;
import utils.exceptions.UnknownTypeException;

public class Game {
    private static final String WINDOW_TITLE = "BOSSFIGHTER";

    public static final int 
        WINDOW_WIDTH = 600, 
        WINDOW_HEIGHT = 400, 
        FPS = 60;

    private String playerName = "Test";
    private Player player;

    private Boss currentBoss;

    private GameState gameState;
    private int score;
    private Scene scene;

    public Game() throws NumberOverflowException {
        player = null;
        score = 0;

        scene = new Scene();
        gameState = GameState.MAIN_MENU;

        Bosses.resetBossNextCounter();
        currentBoss = Bosses.getNextBoss();

        if(Bosses.getException() != null)
            throw Bosses.getException();
    }

    public void gameLoop() throws UnknownTypeException {
        while (!Jaylib.WindowShouldClose() && gameState != GameState.GAME_END) {
            scene.drawWindow(gameState);

            switch(gameState) {
                case MAIN_MENU:
                    if (Buttons.PLAY_BUTTON.isPressed()) 
                        gameState = GameState.SELECTING_CLASS;
                    else if (Buttons.EXIT_BUTTON.isPressed()) 
                        gameState = GameState.GAME_END;
                    break;

                case SELECTING_CLASS:
                    if (Buttons.getClassButton(ClassType.MAGE).isPressed()) {
                        initializeNewGame(ClassType.MAGE);
                        gameState = GameState.BATTLE_START;
                    }
                    else if (Buttons.getClassButton(ClassType.WARRIOR).isPressed()) {
                        initializeNewGame(ClassType.WARRIOR);
                        gameState = GameState.BATTLE_START;
                    }  
                    else if (Buttons.getClassButton(ClassType.ARCHER).isPressed()) {
                        initializeNewGame(ClassType.ARCHER);
                        gameState = GameState.BATTLE_START;
                    }

                    break;
                    
                default: break;
            }
        }

    }

    public void initializeNewGame(ClassType playerClass) throws UnknownTypeException {
        player = new Player(playerName, playerClass);

        Bosses.resetBossNextCounter();
        currentBoss = Bosses.getNextBoss();
        score = 0;
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
    public Boss getCurrentBoss() { return currentBoss; }
    public Player getPlayer() { return player; }
    public int getScore() { return score; }
}
