package Game;

import java.util.ArrayList;
import Entities.*;
import Items.*;
import Scene.*;
import Utils.*;
import com.raylib.Jaylib;

public class Game {
    private final int NUM_BOSSES = 5;
    private Player player;
    private int currentBoss;
    private ArrayList<Boss> bosses;
    private GameState gameState;
    private int score;
    private Scene scene;

    public Game(){
        this.scene = new Scene();
    }
    public static void main(String args[]){
        Game game = new Game();
        game.gameState = GameState.MAIN_MENU;

        game.scene.initializeWindow();
        game.scene.loadTextures();
        game.gameLoop();
        game.scene.unloadTextures();

        Jaylib.CloseWindow();
    }


    public void gameLoop(){
        while (!Jaylib.WindowShouldClose() && gameState != GameState.GAME_END){
            scene.drawWindow(gameState);

            switch(gameState){
                case GameState.MAIN_MENU:
                    if (scene.getPlayButton().isPressed()){
                        gameState = GameState.SELECTING_CLASS;
                    } else if (scene.getExitButton().isPressed()){
                        gameState = GameState.GAME_END;
                    }
                    break;

                case GameState.SELECTING_CLASS:
                    if (scene.getClass1SelectButton().isPressed()){
                        initializeGame(PlayerClass.CLASS1);
                        gameState = GameState.BATTLE_START;
                    } else if (scene.getClass2SelectButton().isPressed()){
                        initializeGame(PlayerClass.CLASS2);
                        gameState = GameState.BATTLE_START;
                    } else if (scene.getClass3SelectButton().isPressed()){
                        initializeGame(PlayerClass.CLASS3);
                        gameState = GameState.BATTLE_START;
                    }
                    break;
                    
                    
            }
        }

    }

    public void initializeGame(PlayerClass playerClass){
        this.player = new Player(playerClass);
        this.bosses = new ArrayList<Boss>(NUM_BOSSES);
        for (int i = 0; i < this.NUM_BOSSES; i++){
            // TODO: INITIALIZE BOSSES
        }

        this.currentBoss = 0;
        this.score = 0;
    }
}
