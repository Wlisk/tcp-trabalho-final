package game;

import com.raylib.Jaylib;

import config.Config;
import entities.Entity;
import entities.boss.Boss;
import entities.boss.Bosses;
import entities.player.ClassType;
import entities.player.Player;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import items.Inventory;
import items.Item;
import items.weapon.Weapons;
import scene.DrawableInventory;
import scene.Scene;
import scene.box.TextBox;
import scene.button.*;
import java.util.HashMap;
import utils.Number;

import static config.Config.FPS;

/**
 * Classe para gerenciar a lógica do jogo.
 * <p>
 * Além disso, faz conexão entre inputs do usuário e desenhos na tela.
 */
public class Game {
    private final String playerName = "Unknown";
    private Player player;
    private Boss currBoss;

    private GameState gameState;
    private int delayTimer;
    private boolean exit;

    private final Scene scene;

    // UI elements
    private final Button playButton, exitButton, attackButton, specialButton, defendButton;
    private final HashMap<ClassType, Button> classButtons;
    private final TextBox alertTextBox;

    /**
     * Contrutor do Game, inicializa o jogo
     * @throws NumberOverflowException erro gerado por
     *   {@link entities.boss.Bosses#getException() Bosses} e 
     *   {@link items.weapon.Weapons#getException() Weapons}
     * @see exceptions.NumberOverflowException
     */
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

        alertTextBox = new TextBox();
    }

    /** Inicializa os gráficos e a janela do jogo */
    public void sceneLoad() {
        scene.initializeWindow(Config.GAME_TITLE);
    }

    /** Descarrega da memória todas as texturas carregadas */
    public void sceneUnload() {
        scene.unloadTextures();
    }

    // Only used in setWindowTitle to improve performance in multiple calls
    private static GameState gameStateCache = null;
    /**
     * Seta o título da janela do jogo
     * @param gameState 
     */
    private static void setWindowTitle(final GameState gameState) {
        if(gameStateCache == gameState) return;
        gameStateCache = gameState;
        
        String _stateString = gameState.toString();
        String _titleString = (_stateString.length() > 0) ? 
            (Config.GAME_TITLE + ": " + _stateString) : Config.GAME_TITLE;

        Jaylib.SetWindowTitle( _titleString );
    }

    /**
     * Cria um novo jogo dado (escolhido) a classe de jogador
     * @param playerClass a classe de jogador
     * @throws UnknownTypeException gerado por 
     *   {@link entities.player.Player#Player(String, ClassType)}
     */
    private void newGame(final ClassType playerClass) 
    throws UnknownTypeException
    {
        player = new Player(playerName, playerClass);

        Bosses.resetBossNextCounter();
        currBoss = Bosses.getNextBoss();

        gameState = GameState.BATTLE_START;
    }

    /**
     * Realiza a transição entre estados de jogo com delay
     * @param newState o novo estado de jogo
     */
    private void transitionState(final GameState newState) {
        delayTimer = (int) (newState.getDelay() * FPS);
        gameState = newState;
    }

    /** Realiza o gerenciamento dos click dos botões no menu inicial */
    private void mainMenu() {
        if(playButton.isMousePressed()) 
            transitionState(GameState.SELECTING_CLASS);
        else if(exitButton.isMousePressed()) 
            exit = true;
    }

    /**
     * Realiza a transição de estados, selecionado a classe de jogador
     * @throws UnknownTypeException gerado por 
     *   {@link game.Game#newGame(ClassType)}
     */
    private void classSelectedTransition(final ClassType classType) 
    throws UnknownTypeException
    {
        newGame(classType);
        transitionState(GameState.BATTLE_START);
        alertTextBox.newMessage(Config.TEXTBOX_TEXT_BATTLE, delayTimer);
    }

    /**
     * Realiza o gerenciamento dos click dos botões no menu de seleção 
     * de classe do jogador
     * @throws UnknownTypeException gerado por 
     *   {@link game.Game#classSelectedTransition(ClassType)}
     */
    private void selectingClass() throws UnknownTypeException {
        if(classButtons.get(ClassType.MAGE).isMousePressed())
            classSelectedTransition(ClassType.MAGE);

        else if(classButtons.get(ClassType.WARRIOR).isMousePressed()) 
            classSelectedTransition(ClassType.WARRIOR);

        else if(classButtons.get(ClassType.ARCHER).isMousePressed())
            classSelectedTransition(ClassType.ARCHER);
    }

    /** Realiza atransição para o ínicio dos turnos Player x Boss */
    public void battleStart() {
        transitionState(GameState.TURN_START);
    }

    /** 
     * Realiza a transição para a batalha Player x Boss, 
     * turno inicial do jogador
     */
    public void turnStart() {
        transitionState(GameState.TURN_PLAYER_CHOOSE);
    }

    /** Realiza a transição de fim de jogo, indo para menu incial */
    public void gameEnd() {
        transitionState(GameState.MAIN_MENU);
    }

    /**
     * Realiza a transição após escolha do jogador
     * <p>
     * Ou chefão derrotado ou vez do chefão escolher sua ação
     */
    private void turnPlayerChosen() {
        if(currBoss.getIsDead()) 
            transitionState(GameState.BATTLE_END);
        // Boss not dead, simply go to boss'es turn
        else transitionState(GameState.TURN_ENEMY_CHOOSE);
    }

    /**
     * Realiza a escolha da ação do chefão e a 
     * transição para o próximo estado (ENEMY_CHOOSEN)
     */
    private void turnEnemyChoose() {
        int damage = currBoss.executeAction(player);
        transitionState(GameState.TURN_ENEMY_CHOSEN);

        if(damage == Entity.ATTACK_MISSED && !currBoss.getIsDefending()) 
            alertTextBox.newMessage(
                Config.TEXTBOX_TEXT_MISS, 
                delayTimer,
                Config.TEXTBOX_COLOR_BOSS
            );
    }

    /**
     * Realiza a transição após escolha do chefão
     * <p>
     * Ou jogador derrotado ou finaliza este turno (reiniciando os turno)
     */
    private void turnEnemyChosen() {
        if(player.getIsDead()) 
            transitionState(GameState.BATTLE_END);
        // player not dead, continue to next turn
        else transitionState(GameState.TURN_END);
    }

    /**
     * Realiza as atualizações de fim de turno do jogador e do chefão
     * <p>
     * buffs e debuffs overtime ocorrem neste método, 
     * como também transiciona para o próximo estado
     */
    private void turnEnd() {
        player.updateEndTurn();
        currBoss.updateEndTurn();
        transitionState(GameState.TURN_START);
    }

    /**
     * Realiza o fim da batalha Player x Boss
     * <p>
     * Se o jogador tiver sido derrotado, fim de jogo
     * <p>
     * Caso contrário, chefão foi derrotado, o jogador 
     * recebe suas recompensas e verifica se o jogador derrotou
     * todos os chefões do jogo
     * @throws UnknownTypeException gerado por 
     *   {@link entities.boss.Boss#getDroppedItems(ClassType)}
     */
    private void battleEnd() throws UnknownTypeException {
        // If player dead, display game over message and return to main menu
        if(player.getIsDead()) { 
            transitionState(GameState.GAME_END);
            alertTextBox.newMessage(Config.TEXTBOX_TEXT_LOSE, delayTimer);
        } 
        // Otherwise, boss is dead
        else {
            player.receiveExp(currBoss.getExpReward());
            player.addItemsInventory(currBoss.getDroppedItems(player.getClassType()));

            currBoss = Bosses.getNextBoss();

            // If all bosses dead, display win message and return to main menu
            if(currBoss == null) { 
                transitionState(GameState.GAME_END);
                alertTextBox.newMessage(Config.TEXTBOX_TEXT_WIN, delayTimer);
            }
            // Otherwise, update UI elements for new boss and 
            // return to battle start with next boss 
            else { 
                transitionState(GameState.BATTLE_START);
                alertTextBox.newMessage(Config.TEXTBOX_TEXT_BATTLE, delayTimer);
            }
        }
    }

    /**
     * Realiza as escolhas das ações do jogador seja de combate, 
     * seja de usar itens do inventário
     * <p>
     * O jogador pode escolher ações de combate (atacar e defender)
     * <p>
     * O jogador pode escolher usar consumíveis ou trocar de equipamento
     */
    private void turnPlayerChoose() {
        final boolean _attack = attackButton.isMousePressed();
        final boolean _special = specialButton.isMousePressed();
        final boolean _defend = defendButton.isMousePressed();

        final DrawableInventory _dInventory = player.getInventory();

        // subtract because first N slots are currently equipped items
        final int inventoryLeftClicked = 
            _dInventory.checkLeftClickedIndex() - Inventory.NUM_EQUIPABLE_SLOTS; 
        final int inventoryRightClicked = 
            _dInventory.checkRightClickedIndex() - Inventory.NUM_EQUIPABLE_SLOTS;

        final Item _itemLeftClicked = player.getItemInventory(inventoryLeftClicked);

        // Player chooses to attack
        if(_attack) { 
            final int _damage = player.attack(currBoss);
            transitionState(GameState.TURN_PLAYER_CHOSEN);

            // Attack missed, display miss message
            if(_damage == Entity.ATTACK_MISSED) 
                alertTextBox.newMessage(
                    Config.TEXTBOX_TEXT_MISS, 
                    delayTimer,
                    Config.TEXTBOX_COLOR_PLAYER
                ); 
        } 

        // Player chooses to atack with super
        else if(_special && player.canSuper()) {
            final int _damage = player.attackSuper(currBoss);
            transitionState(GameState.TURN_PLAYER_CHOSEN);

            // Attack missed, display miss message
            if(_damage == Entity.ATTACK_MISSED) 
                alertTextBox.newMessage(
                    Config.TEXTBOX_TEXT_MISS, 
                    delayTimer,
                    Config.TEXTBOX_COLOR_PLAYER
                );
        }  

        // Player chooses to defend
        else if(_defend) { 
            player.setDefend();
            transitionState(GameState.TURN_PLAYER_CHOSEN);
        } 

        // Player left clicked item in inventory
        else if(
            inventoryLeftClicked != Number.NOT_FOUND 
            && _itemLeftClicked != null
        ) { 
            switch (_itemLeftClicked.getItemType()) {
                // If armor or weapon swapped, return to turn start since player can still act
                case WEAPON:
                case ARMOR:
                    player.equipInventory(inventoryLeftClicked);
                    transitionState(GameState.TURN_START); 
                    break;
                // Using a consumable skips turn, switching armors/weapons does not
                case CONSUMABLE:
                    player.useConsumable(inventoryLeftClicked);
                    transitionState(GameState.TURN_PLAYER_CHOSEN); 
                default: break;
            }
        } 

        // Player right clicked item in inventory 
        else if(
            inventoryRightClicked != Number.NOT_FOUND 
            && _dInventory.getItem(inventoryRightClicked) != null
        ) { 
            _dInventory.remove(inventoryRightClicked);
            transitionState(GameState.TURN_START);
        }
    }

    /**
     * Realiza o loop do jogo
     * @throws UnknownTypeException gerado por 
     *   {@link game.Game#selectingClass()}
     *   {@link game.Game#battleEnd()}
     */
    public void gameLoop() throws UnknownTypeException {
        while (!Jaylib.WindowShouldClose() && !exit) {
            scene.drawWindow(gameState, player, currBoss);
            setWindowTitle(gameState);

            // Wait for timer to run out before game logic can progress
            if(delayTimer <= 0) { 
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
            } 
            else delayTimer--;
        }
    }

    /**
     * Retorna o estado de jogo atual 
     * @return (GameState) o estado de jogo 
     * @see game.GameState
     */
    public GameState getGameState() { return gameState; }

    /**
     * Retorna o chefão atual (intância)
     * @return (Boss) o chefão atual (intância)
     * @see entities.boss.Boss
     */
    public Boss getCurrBoss() { return currBoss; }

    /**
     * Retorna o jogador (intância)
     * @return (Player) o jogador (intância)
     * @see entities.player.Player
     */
    public Player getPlayer() { return player; }

    /**
     * Retorna o botão (instância) de iniciar o jogo
     * @return (Button) 
     * @see scene.button.Button
     */
    public Button getPlayButton() { return playButton; }

    /**
     * Retorna o botão (instância) de saída do jogo
     * @return (Button) 
     * @see scene.button.Button
     */
    public Button getExitButton() { return exitButton; }

    /**
     * Retorna o botão (instância) de ação de ataque do jogador
     * @return (Button) 
     * @see scene.button.Button
     */
    public Button getAttackButton() { return attackButton; }

    /**
     * Retorna o botão (instância) de ação de ataque especial do jogador
     * @return (Button) 
     * @see scene.button.Button
     */
    public Button getSpecialButton() { return specialButton; }

    /**
     * Retorna o botão (instância) de ação de defesa do jogador
     * @return (Button) 
     * @see scene.button.Button
     */
    public Button getDefendButton() { return defendButton; }

    /**
     * Retorna a hash que liga as classes do jogador com seus botões
     * @return (HashMap) a hash de ligação
     * @see entities.player.ClassType
     * @see scene.button.Button
     */
    public HashMap<ClassType, Button> getClassButtons() { return classButtons; }

    /**
     * Retorna a instância da caixa de mensagem do jogo
     * @return (TextBox) instância da caixa de mensagem
     * @see scene.box.TextBox 
     */
    public TextBox getAlertTextBox() { return alertTextBox; }

    /**
     * Retorna a instância da cena do jogo para fins de teste
     * @return (Scene) a cena do jogo (instância)
     */
    public Scene getScene() { return scene; }
}
