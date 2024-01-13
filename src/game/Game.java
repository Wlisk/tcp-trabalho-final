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
import scene.textbox.TextBoxes;
import scene.statbox.Statboxes;
import scene.inventory.InventorySlotInst;

public class Game {
    public static final String WINDOW_TITLE = "BOSSFIGHTER";
    public static final double DELAY_BATTLE_START = 2,
                               DELAY_TURN_START = 0.5,
                               DELAY_AFTER_PLAYER_ACTION = 0.5,
                               DELAY_AFTER_BOSS_ACTION = 0.5,
                               DELAY_TURN_END = 0.5,
                               DELAY_BATTLE_END = 1,
                               DELAY_GAME_OVER = 5;


    public static final int 
        WINDOW_WIDTH = 600, 
        WINDOW_HEIGHT = 400, 
        FPS = 60;

    private String playerName = "Test";
    private Player player;

    private Boss currBoss;

    private GameState gameState;
    private Scene scene;
    private int delayTimer;
    private boolean exit;

    public Game() throws NumberOverflowException {
        player = null;

        scene = new Scene(this);
        transitionState(GameState.MAIN_MENU);
        exit = false;

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

    public void gameLoop() throws UnknownTypeException, NumberOverflowException {
        
        while (!Jaylib.WindowShouldClose() && !exit) {
            scene.drawWindow(gameState, player, currBoss);
            setWindowTitle(gameState);

            if (delayTimer <= 0){ // Wait for timer to run out before game logic can progress
                switch(gameState) {
                    case MAIN_MENU: mainMenu(); break;
                    case SELECTING_CLASS: selectingClass(); break;
                    case BATTLE_START: battleStart(); break;
                    case TURN_START: turnStart(); break;    
                    case TURN_PLAYER_CHOOSE: turnPlayerChoose(); break;
                    case TURN_PLAYER_CHOSEN: turnPlayerChosen(); break;
                    case TURN_ENEMY_CHOOSE: turnEnemyChoose(); break;
                    case TURN_ENEMY_CHOSEN: turnEnemyChosen(); break;
                    case TURN_END: turnEnd(); break;
                    case BATTLE_END: battleEnd(); break;
                    case GAME_END: gameEnd(); break;
                    default: break;
                }
            } else {
                delayTimer--;
            }
            
        }

    }

    public void mainMenu(){
        if (Buttons.PLAY_BUTTON.isMousePressed()) 
            transitionState(GameState.SELECTING_CLASS);
        else if (Buttons.EXIT_BUTTON.isMousePressed()) 
            exit = true;
    }

    public void selectingClass() throws UnknownTypeException, NumberOverflowException {
        if (Buttons.getClassButton(ClassType.MAGE).isMousePressed()) {
            newGame(ClassType.MAGE);
            transitionState(GameState.BATTLE_START);
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, delayTimer);
        } else if (Buttons.getClassButton(ClassType.WARRIOR).isMousePressed()) {
            newGame(ClassType.WARRIOR);
            transitionState(GameState.BATTLE_START);
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, delayTimer);
        }  else if (Buttons.getClassButton(ClassType.ARCHER).isMousePressed()) {
            newGame(ClassType.ARCHER);
            transitionState(GameState.BATTLE_START);
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, delayTimer);
        }
    }

    public void battleStart(){
        transitionState(GameState.TURN_START);
    }

    public void turnStart(){
        transitionState(GameState.TURN_PLAYER_CHOOSE);
    }

    public void turnPlayerChoose() throws NumberOverflowException{
        if (Buttons.ATTACK_BUTTON.isMousePressed()) {
            int damage = player.attack(currBoss);
            transitionState(GameState.TURN_PLAYER_CHOSEN);
            if (damage == 0) TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.ATTACK_MISSED, delayTimer); // Attack missed, display miss message
        } else if (Buttons.SPECIAL_BUTTON.isMousePressed() && player.canSuper()) {
            int damage = player.attackSuper(currBoss);
            transitionState(GameState.TURN_PLAYER_CHOSEN);
            if (damage == 0) TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.ATTACK_MISSED, delayTimer);
        }  else if (Buttons.DEFEND_BUTTON.isMousePressed()) {
            player.defend();
            transitionState(GameState.TURN_PLAYER_CHOSEN);
        }
    }

    public void turnPlayerChosen(){
        if (currBoss.getIsDead()){ // Checking if the boss is dead
            transitionState(GameState.BATTLE_END);
        } else { // Boss not dead, simply go to boss' turn
            transitionState(GameState.TURN_ENEMY_CHOOSE);
        }
    }

    public void turnEnemyChoose() throws NumberOverflowException {
        int damage = currBoss.chooseAction(player);
        transitionState(GameState.TURN_ENEMY_CHOSEN);
        if (damage == 0) TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.ATTACK_MISSED, delayTimer);
    }

    public void turnEnemyChosen(){
        if (player.getIsDead()){ // Checking if the player is dead
            transitionState(GameState.BATTLE_END);
        } else {
            transitionState(GameState.TURN_END);
        }
    }

    public void turnEnd(){
        player.updateEndTurn();
        currBoss.updateEndTurn();
        transitionState(GameState.TURN_START);
    }

    public void battleEnd() throws NumberOverflowException {
        if (player.getIsDead()){ // If player dead, display game over message and return to main menu
            transitionState(GameState.GAME_END);
            TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.GAME_OVER_LOSS, delayTimer);
        } else {// Otherwise, boss is dead
            player.receiveExp(currBoss.getExpReward());

            currBoss = Bosses.getNextBoss();
            if (currBoss == null){ // If all bosses dead, display win message and return to main menu
                transitionState(GameState.GAME_END);
                TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.GAME_OVER_WIN, delayTimer);
            } else { // Otherwise, update UI elements for new boss and return to battle start with next boss
                Statboxes.BOSS_STATBOX.setEntity(currBoss);

                transitionState(GameState.BATTLE_START);
                TextBoxes.ALERT_TEXTBOX.newMessage(TextBoxes.BATTLE_START, delayTimer);
            }
        }
    }

    public void gameEnd(){
        transitionState(GameState.MAIN_MENU);
    }

    private void newGame(ClassType playerClass) throws UnknownTypeException, NumberOverflowException {
        player = new Player(playerName, playerClass);
        Statboxes.PLAYER_STATBOX.setEntity(player); // Link UI elements to new player
        InventorySlotInst.INVENTORY_SLOTS.setInventory(player.getInventory());


        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();
        Statboxes.BOSS_STATBOX.setEntity(currBoss); // Link UI elements to current boss

        gameState = GameState.BATTLE_START;
    }

    public void sceneLoad() {
        scene.initializeWindow(WINDOW_TITLE);
        scene.loadTextures();
    }

    public void sceneUnload() {
        scene.unloadTextures();
    }

    private void transitionState(GameState newState){
        delayTimer = (int) (newState.getDelay() * FPS);
        gameState = newState;
    }

    public GameState getGameState() { return gameState; }
    public Scene getScene() { return scene; }
    public Boss getCurrBoss() { return currBoss; }
    public Player getPlayer() { return player; }
}
