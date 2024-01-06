package entities.boss;

import exceptions.NumberOverflowException;

public final class Bosses {
    private static int bossNextCounter = 0;
    private static NumberOverflowException err = null;
    private static final float BASE_SCALE = 1.0f;

    private static Boss[] createBosses() {
        Boss[] _bosses = null;

        try {
            final Boss[] _tempBosses = {
                new Boss( 
                    "Aragdhar", 
                    500,
                    0.3, 0.2, 
                    0.45f, "resources/sprites/bosses/boss1.png",
                    "Born at the Hell of Valathur, its protruding fangs drips venon that poison its enemies to death. Is a nightmare that no one wants to confronte."
                ),
                new Boss(
                    "Irmitz", 
                    1000,
                    0.35, 0.25, 
                    BASE_SCALE, "",
                    "The fire itself, burns anyone who gets close. Be caitous, cause his temperament is fiery."
                ),
                new Boss(
                    "Bolrvots", 
                    1500,
                    0.4, 0.3, 
                    BASE_SCALE, "",
                    "An dark elven that has fallen to the dark side. It has betrayed its people and so was condened to eternity."
                ),
                new Boss(
                    "", 
                    2000,
                    0.5, 0.3, 
                    BASE_SCALE, "",
                    ""
                ),
                new Boss(
                    "", 
                    2500,
                    0.6, 0.4, 
                    BASE_SCALE, "",
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
