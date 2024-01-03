package utils;

import java.util.Random;

public class Randomic {
    private static final Random generator = new Random();
    private static int lastRandomInt = 0;
    private static double lastRandomDouble = 0.0;

    public static double between(double min, double max) {
        lastRandomDouble = min + (max - min) * generator.nextDouble();
        return lastRandomDouble;
    }

    public static int between(int min, int max) {
        lastRandomInt = generator.nextInt(max - min + 1) + min;
        return lastRandomInt;
    }
}
