package tests.entities.boss;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import entities.boss.Boss;
import entities.boss.StateType;
import exceptions.NumberOverflowException;
import scene.TextureId;

public class BossTest {
    
    @Test
    public void testNewBoss() throws NumberOverflowException {
        Boss boss = new Boss( 
                    "Aragdhar", 
                    TextureId.BOSS_1,
                    500,
                    600,
                    200,
                    150,
                    20,
                    0.1,
                    1.25,
                    0.8,
                    1,
                    0.2, 
                    0.5,
                    0.2,
                    0.2,
                    0, 0, "Born at the Hell of Valathur, its protruding fangs drips venon that poison its enemies to death. Is a nightmare that no one wants to confront."
                );

        assertEquals(StateType.BASE, boss.getCurrState());
        assertEquals(500, boss.getExpReward());
    }

}
