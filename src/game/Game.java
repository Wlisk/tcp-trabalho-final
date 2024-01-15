package game;

import com.raylib.Jaylib;

import entities.Entity;
import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import entities.player.Player;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.weapon.Weapons;
import scene.Scene;
import scene.button.*;
import scene.textbox.*;
import java.util.HashMap;

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
    private Scene scene;
    private int delayTimer;
    private boolean exit;

    // UI elements
    private Button playButton, exitButton, attackButton, specialButton, defendButton;
    private HashMap<ClassType, Button> classButtons;
    private TextBox alertTextBox;

    public Game() throws NumberOverflowException {
        player = null;

        scene = new Scene(this);
        transitionState(GameState.MAIN_MENU);
        exit = false;

        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();

        if(Bosses.getException() != null)
            throw Bosses.getException();
        if(Weapons.getException() != null)
            throw Weapons.getException();

        playButton = Buttons.newPlayButton();
        exitButton = Buttons.newExitButton();
        attackButton = Buttons.newAttackButton();
        specialButton = Buttons.newSpecialButton();
        defendButton = Buttons.newDefendButton();
        classButtons = Buttons.newClassButtons();

        alertTextBox = TextBoxes.newAlertTextBox();
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

            if(delayTimer <= 0){ // Wait for timer to run out before game logic can progress
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
        if(playButton.isMousePressed()) 
            transitionState(GameState.SELECTING_CLASS);
        else if(exitButton.isMousePressed()) 
            exit = true;
    }

    public void selectingClass() throws UnknownTypeException, NumberOverflowException {
        if(classButtons.get(ClassType.MAGE).isMousePressed()) {
            newGame(ClassType.MAGE);
            transitionState(GameState.BATTLE_START);
            alertTextBox.newMessage(TextBoxes.BATTLE_START, delayTimer);
        } else if(classButtons.get(ClassType.WARRIOR).isMousePressed()) {
            newGame(ClassType.WARRIOR);
            transitionState(GameState.BATTLE_START);
            alertTextBox.newMessage(TextBoxes.BATTLE_START, delayTimer);
        }  else if(classButtons.get(ClassType.ARCHER).isMousePressed()) {
            newGame(ClassType.ARCHER);
            transitionState(GameState.BATTLE_START);
            alertTextBox.newMessage(TextBoxes.BATTLE_START, delayTimer);
        }
    }

    public void battleStart(){
        transitionState(GameState.TURN_START);
    }

    public void turnStart(){
        transitionState(GameState.TURN_PLAYER_CHOOSE);
    }

    public void turnPlayerChoose() throws NumberOverflowException{
        boolean attack = attackButton.isMousePressed(),
                special = specialButton.isMousePressed(),
                defend = defendButton.isMousePressed();
        int inventoryLeftClicked = player.getInventory().checkLeftClickedIndex() - 2, // -2 because first two slots are currently equipped items
            inventoryRightClicked = player.getInventory().checkRightClickedIndex() - 2;

        if(attack) { // Player chooses attack
            int damage = player.attack(currBoss);
            transitionState(GameState.TURN_PLAYER_CHOSEN);
            if(damage == 0) alertTextBox.newMessage(TextBoxes.ATTACK_MISSED, delayTimer); // Attack missed, display miss message
        } else if(special && player.canSuper()) { // Player chooses super
            int damage = player.attackSuper(currBoss);
            transitionState(GameState.TURN_PLAYER_CHOSEN);
            if(damage == 0) alertTextBox.newMessage(TextBoxes.ATTACK_MISSED, delayTimer);
        }  else if(defend) { // Player chooses defend
            player.setDefend();
            transitionState(GameState.TURN_PLAYER_CHOSEN);
        } else if(inventoryLeftClicked >= 0 && player.getItemInventory(inventoryLeftClicked) != null) { // Player left clicks item in inventory
            switch (player.getItemInventory(inventoryLeftClicked).getItemType()){
                case WEAPON:
                case ARMOR:
                    player.equipInventory(inventoryLeftClicked);
                    transitionState(GameState.TURN_START); // If armor or weapon swapped, return to turn start since player can still act
                    break;
                case CONSUMABLE:
                    player.useConsumable(inventoryLeftClicked);
                    transitionState(GameState.TURN_PLAYER_CHOSEN); // Using a consumable skips turn, switching armors/weapons does not
                    break;
            }
        } else if(inventoryRightClicked >= 0 && player.getInventory().getItem(inventoryRightClicked) != null) { // Player right clicks item in inventory
            player.getInventory().remove(inventoryRightClicked);
            transitionState(GameState.TURN_START);
        }
    }

    public void turnPlayerChosen(){
        if(currBoss.getIsDead()){ // Checking if the boss is dead
            transitionState(GameState.BATTLE_END);
        } else { // Boss not dead, simply go to boss' turn
            transitionState(GameState.TURN_ENEMY_CHOOSE);
        }
    }

    public void turnEnemyChoose() throws NumberOverflowException {
        int damage = currBoss.executeAction(player);
        transitionState(GameState.TURN_ENEMY_CHOSEN);
        if(damage == Entity.ATTACK_MISSED) 
            alertTextBox.newMessage(TextBoxes.ATTACK_MISSED, delayTimer);
    }

    public void turnEnemyChosen(){
        if(player.getIsDead()){ // Checking if the player is dead
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

    public void battleEnd() throws NumberOverflowException, UnknownTypeException {
        if(player.getIsDead()){ // If player dead, display game over message and return to main menu
            transitionState(GameState.GAME_END);
            alertTextBox.newMessage(TextBoxes.GAME_OVER_LOSS, delayTimer);
        } else {// Otherwise, boss is dead
            player.receiveExp(currBoss.getExpReward());
            player.addItemsInventory(currBoss.getDroppedItems(player.getClassType()));

            currBoss = Bosses.getNextBoss();
            if(currBoss == null){ // If all bosses dead, display win message and return to main menu
                transitionState(GameState.GAME_END);
                alertTextBox.newMessage(TextBoxes.GAME_OVER_WIN, delayTimer);
            } else { // Otherwise, update UI elements for new boss and return to battle start with next boss
                transitionState(GameState.BATTLE_START);
                alertTextBox.newMessage(TextBoxes.BATTLE_START, delayTimer);
            }
        }
    }

    public void gameEnd(){
        transitionState(GameState.MAIN_MENU);
    }

    private void newGame(ClassType playerClass) throws UnknownTypeException, NumberOverflowException {
        player = new Player(playerName, playerClass);

        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();

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
    public Button getPlayButton() { return playButton; }
    public Button getExitButton() { return exitButton; }
    public Button getAttackButton() { return attackButton; }
    public Button getSpecialButton() { return specialButton; }
    public Button getDefendButton() { return defendButton; }
    public HashMap<ClassType, Button> getClassButtons() { return classButtons; }
    public TextBox getAlertTextBox() { return alertTextBox; }
}
