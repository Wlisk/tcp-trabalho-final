package utils;

import java.util.Random;

/**
 * Class with static properties and methods to get random numbers
 * @see java.util.Random
 */
public final class Randomic {
    /** 
     * Store an instance of the Random class so it does not need to 
     * be instantiated every single calling
     */
    private static final Random generator = new Random();
    /** Caches the last randomic (int)number generated */
    private static int lastRandomInt = 0;
    /** Caches the last randomic (double)number generated */
    private static double lastRandomDouble = 0.0;

    /**
     * Gets a random (double)number between min and max (inclusive)
     * @param min minimum (double)value to get
     * @param max maximum (double)value to get
     * @return the generated (double)number
     */
    public static double between(double min, double max) {
        lastRandomDouble = min + (max - min) * generator.nextDouble();
        return lastRandomDouble;
    }

    /**
     * Gets a random (int)number between min and max (inclusive)
     * @param min minimum (int)value to get
     * @param max maximum (int)value to get
     * @return the generated (int)number
     */
    public static int between(int min, int max) {
        lastRandomInt = generator.nextInt(max - min + 1) + min;
        return lastRandomInt;
    }
}
