package game;

import com.raylib.Jaylib;

import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import entities.player.Player;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import scene.Scene;
import scene.bars.Bars;
import scene.button.Buttons;
import scene.textbox.TextBoxes;
import scene.statbox.Statboxes;
import scene.inventory.InventorySlotInst;

public class Game {
    public static final String WINDOW_TITLE = "BOSSFIGHTER";
    public static final int BATTLE_START_DELAY = 2;

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
    private int battleStartTimer;

    public Game() throws NumberOverflowException {
        player = null;
        score = 0;

        scene = new Scene(this);
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
            scene.drawWindow(gameState, player, currBoss);
            setWindowTitle(gameState);

            switch(gameState) {
                case MAIN_MENU:
                    mainMenu();
                    break;
                case SELECTING_CLASS:
                    selectingClass();
                    break;
                case BATTLE_START:
                    battleStart();
                    break;
                case TURN_START:
                    turnStart();
                    break;    
                    
                default: 
                    break;
            }
        }

    }

    public void mainMenu(){
        if (Buttons.PLAY_BUTTON.isMousePressed()) 
            gameState = GameState.SELECTING_CLASS;
        else if (Buttons.EXIT_BUTTON.isMousePressed()) 
            gameState = GameState.GAME_END;
    }

    public void selectingClass() throws UnknownTypeException {
        if (Buttons.getClassButton(ClassType.MAGE).isMousePressed()) {
            newGame(ClassType.MAGE);
            battleStartTimer = BATTLE_START_DELAY * FPS; 
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, battleStartTimer);
            gameState = GameState.BATTLE_START;
        } else if (Buttons.getClassButton(ClassType.WARRIOR).isMousePressed()) {
            newGame(ClassType.WARRIOR);
            battleStartTimer = BATTLE_START_DELAY * FPS; 
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, battleStartTimer);
            gameState = GameState.BATTLE_START;
        }  else if (Buttons.getClassButton(ClassType.ARCHER).isMousePressed()) {
            newGame(ClassType.ARCHER);
            battleStartTimer = BATTLE_START_DELAY * FPS; 
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, battleStartTimer);
            gameState = GameState.BATTLE_START;
        }
    }

    public void battleStart(){
        if (battleStartTimer-- == 0){
            gameState = GameState.TURN_START;
        }
    }

    public void turnStart(){
        
    }


    public void newGame(ClassType playerClass) throws UnknownTypeException {
        player = new Player(playerName, playerClass);
        Statboxes.PLAYER_STATBOX.setPlayer(player); // Link UI elements to new player
        Bars.PLAYER_HEALTHBAR.setEntity(player);
        Bars.PLAYER_MANABAR.setEntity(player);
        InventorySlotInst.INVENTORY_SLOTS.setInventory(player.getInventory());


        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();
        Statboxes.BOSS_STATBOX.setBoss(currBoss); // Link UI elements to current boss
        Bars.BOSS_HEALTHBAR.setEntity(currBoss);
        Bars.BOSS_MANABAR.setEntity(currBoss);
        score = 0;

        battleStartTimer = BATTLE_START_DELAY * FPS; 
        TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, battleStartTimer);
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
