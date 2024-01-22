package tests.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;
import exceptions.NumberOverflowException;
import game.Game;
import game.GameState;



public class GameTest {

    private Game game;
    
    @Test
    public void testGameInitialization() throws NumberOverflowException {

        game = new Game();

        assertNotNull(game);
        assertNotNull(game.getScene());
        assertNotNull(game.getPlayButton());
        assertNotNull(game.getExitButton());
        assertNotNull(game.getAttackButton());
        assertNotNull(game.getSpecialButton());
        assertNotNull(game.getDefendButton());
        assertNotNull(game.getClassButtons());
        assertNotNull(game.getAlertTextBox());
    }


    @Test
    public void testBattleStart() throws NumberOverflowException {
        game = new Game();

        game.battleStart();
        assertEquals(GameState.TURN_START, game.getGameState());
    }

    @Test
    public void testTurnStart() throws NumberOverflowException {
        game = new Game();
        game.turnStart();
        assertEquals(GameState.TURN_PLAYER_CHOOSE, game.getGameState());
    }


    @Test
    public void testGameEnd() throws NumberOverflowException {
        game = new Game();
        game.gameEnd();
        assertEquals(GameState.MAIN_MENU, game.getGameState());
    }


}
