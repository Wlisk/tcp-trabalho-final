package tests.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import game.GameState;

public class GameStateTest {
    
    @Test
    public void testGetDelay() {
        assertEquals(0, GameState.MAIN_MENU.getDelay());
        assertEquals(0, GameState.SELECTING_CLASS.getDelay());
        assertEquals(2, GameState.BATTLE_START.getDelay());
        assertEquals(0.5, GameState.TURN_START.getDelay());
        assertEquals(0.2, GameState.TURN_PLAYER_CHOOSE.getDelay());
        assertEquals(1, GameState.TURN_PLAYER_CHOSEN.getDelay());
        assertEquals(0, GameState.TURN_ENEMY_CHOOSE.getDelay());
        assertEquals(1, GameState.TURN_ENEMY_CHOSEN.getDelay());
        assertEquals(0, GameState.TURN_END.getDelay());
        assertEquals(1, GameState.BATTLE_END.getDelay());
        assertEquals(5, GameState.GAME_END.getDelay());
    }

    @Test
    public void testToString() {
        assertEquals("Main Menu", GameState.MAIN_MENU.toString());
        assertEquals("Class Selection", GameState.SELECTING_CLASS.toString());
    }

}
