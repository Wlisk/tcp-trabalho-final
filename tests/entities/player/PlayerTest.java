package tests.entities.player;

import entities.player.ClassType;
import entities.player.Player;
import exceptions.UnknownTypeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;

    @Test
    void testPlayerInitialization() {
        try {
            player = new Player("TestPlayer", ClassType.WARRIOR);

            assertEquals("TestPlayer", player.getName());
            assertEquals(1, player.getLevel());
            assertEquals(0, player.getExp());
            assertEquals(1000, player.getMaxHP());
            assertEquals(1000, player.getCurrHP());
            assertEquals(70, player.getMaxMP());
            assertEquals(70, player.getCurrMP());
            assertEquals(160, player.getBaseDamage());
            assertEquals(200, player.getCurrDamage());
            assertEquals(72, player.getBaseDefense());
            assertEquals(152, player.getCurrDefense());
            assertNotNull(player.getInventory());
            assertNotNull(player.getClassType());
        } catch (UnknownTypeException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testPlayerLevelUp() {
        try {
            player = new Player("TestPlayer", ClassType.MAGE);

            assertEquals(1, player.getLevel());
            assertEquals(0, player.getExp());
            player.receiveExp(200);
            assertEquals(2, player.getLevel());
            assertEquals(100, player.getExp());
            assertEquals(770, player.getMaxHP());
            assertEquals(770, player.getCurrHP());
            assertEquals(110, player.getMaxMP());
            assertEquals(110, player.getCurrMP());
            assertEquals(198, player.getBaseDamage());
            assertEquals(248, player.getCurrDamage());
        } catch (UnknownTypeException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testPlayerReceiveExp() {
        try {
            player = new Player("TestPlayer", ClassType.ARCHER);

            assertEquals(1, player.getLevel());
            assertEquals(0, player.getExp());
            player.receiveExp(50);
            assertEquals(1, player.getLevel());
            assertEquals(50, player.getExp());
            player.receiveExp(150);
            assertEquals(2, player.getLevel());
            assertEquals(100, player.getExp());
        } catch (UnknownTypeException e) {
            fail("Exception not expected");
        }
    }



    
}

