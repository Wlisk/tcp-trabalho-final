package tests.entities.boss;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import entities.boss.Bosses;

public class BossesTest {
    
    @Test
    public void testCorrectQuantity() {
        assertEquals(5, Bosses.quantity(), "A quantidade de chefoes deve ser 5");
    }

    @Test
    public void testResetBossNextCounter() {
        Bosses.resetBossNextCounter();
        assertEquals(0, Bosses.getCurrIndex(), "O contador de próximos chefoes deve ser resetado para 0");
    }

}
