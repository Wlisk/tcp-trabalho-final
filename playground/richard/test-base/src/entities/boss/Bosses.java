package entities.boss;

//import entities.boss.Boss;
import utils.exceptions.NumberOverflowException;
//import utils.Number;

public final class Bosses {
    private static int bossNextCounter = 0;
    private static NumberOverflowException err = null;

    private static Boss[] getBosses() {
        Boss[] _bosses = null;

        try {
            final Boss[] _tempBosses = {
                new Boss(
                    "Aragdhar", 
                    500,
                    0.3, 0.2, 
                    "Born at the Hell of Valathur, its protruding fangs drips venon that poison its enemies to death. Is a nightmare that no one wants to confronte."
                ),
                new Boss(
                    "Irmitz", 
                    1000,
                    0.35, 0.25, 
                    "The fire itself, burns anyone who gets close. Be caitous, cause his temperament is fiery."
                ),
                new Boss(
                    "Bolrvots", 
                    1500,
                    0.4, 0.3, 
                    "An dark elven that has fallen to the dark side. It has betrayed its people and so was condened to eternity."
                ),
                new Boss(
                    "", 
                    2000,
                    0.5, 0.3, 
                    ""
                ),
                new Boss(
                    "", 
                    2500,
                    0.6, 0.4, 
                    ""
                )
            };

            _bosses = _tempBosses;
        } catch (NumberOverflowException e) {
            err = e;
        }

        return _bosses;
    }

    private final static Boss[] bossList = getBosses();
    
    public static Boss[] getBossList() { return bossList; }
    public static NumberOverflowException getException() { return err; }
    public static int getQuantity() { return bossList.length; }
    
    public static Boss getNextBoss() { 
        if(bossNextCounter >= bossList.length) return null;

        return bossList[bossNextCounter++];
    }

    public static void resetBossNextCounter() { bossNextCounter = 0; }
}
