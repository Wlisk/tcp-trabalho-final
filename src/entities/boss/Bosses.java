package entities.boss;

import exceptions.NumberOverflowException;
import scene.TextureId;

public final class Bosses {
    private static int bossNextCounter = 0;
    private static NumberOverflowException err = null;

    private static Boss[] createBosses() {
        Boss[] _bosses = null;

        try {
            final Boss[] _tempBosses = {
                new Boss( 
                    "Aragdhar", 
                    TextureId.BOSS_1,
                    500,
                    600,
                    200,
                    60,
                    20,
                    0.1,
                    1.25,
                    0.8,
                    1,
                    0.2, 
                    0.5,
                    0.2,
                    0.2,
                    "Born at the Hell of Valathur, its protruding fangs drips venon that poison its enemies to death. Is a nightmare that no one wants to confronte."
                ),
                new Boss(
                    "Irmitz", 
                    TextureId.BOSS_2,
                    1000,
                    1000,
                    250,
                    80,
                    30,
                    0.15,
                    1.25,
                    0.80,
                    1,
                    0.25,
                    0.6,
                    0.3,
                    0.2,
                    "The fire itself, burns anyone who gets close. Be caitous, cause his temperament is fiery."
                ),
                new Boss(
                    "Bolrvots", 
                    TextureId.BOSS_3,
                    1500,
                    1400,
                    300,
                    100,
                    40,
                    0.15,
                    1.30,
                    0.8,
                    1,
                    0.3,
                    0.7,
                    0.4,
                    0.2,
                    "An dark elven that has fallen to the dark side. It has betrayed its people and so was condened to eternity."
                ),
                new Boss(
                    "", 
                    TextureId.BOSS_4,
                    2000,
                    2000,
                    500,
                    140,
                    60,
                    0.30,
                    1.35,
                    0.85,
                    1,
                    0.35, 
                    0.8,
                    0.45,
                    0.2,
                    ""
                ),
                new Boss(
                    "", 
                    TextureId.BOSS_5,
                    2500,
                    2500,
                    1000,
                    200,
                    100,
                    0.35,
                    1.5,
                    0.9,
                    1,
                    0.9,
                    0.45,
                    0.5,
                    0.2, 
                    ""
                )
            };

            _bosses = _tempBosses;
        } catch (NumberOverflowException e) {
            err = e;
        }

        return _bosses;
    }

    private final static Boss[] bossList = createBosses();
    
    public static NumberOverflowException getException() { return err; }
    public static int quantity() { return bossList.length; }
    public static int getCurrIndex() { return bossNextCounter; }
    public static Boss[] getList() { return bossList; }
    
    public static Boss getNextBoss() { 
        if(bossNextCounter >= bossList.length) return null;

        return bossList[bossNextCounter++];
    }

    public static void resetBossNextCounter() { bossNextCounter = 0; }
}
