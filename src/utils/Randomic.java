package utils;

import java.util.Random;

/**
 * Classe com propriedades e métodos estáticos para pegar numeros randômicos
 * @see java.util.Random
 */
public final class Randomic {
    private static final Random generator = new Random();
    private static int lastRandomInt = 0;
    private static double lastRandomDouble = 0.0;

    /**
     * Retorna um número (double) entre min e max (inclusívo)
     * @param min mínimo valor (double) à se retornar
     * @param max máximo valor (double) à se retornar
     * @return (double) o valor randômico gerado
     */
    public static double between(double min, double max) {
        lastRandomDouble = min + (max - min) * generator.nextDouble();
        return lastRandomDouble;
    }

    /**
     * Retorna um número (int) entre min e max (inclusívo)
     * @param min mínimo valor (int) à se retornar
     * @param max máximo valor (int) à se retornar
     * @return (int) o valor randômico gerado
     */
    public static int between(int min, int max) {
        lastRandomInt = generator.nextInt(max - min + 1) + min;
        return lastRandomInt;
    }

    /**
     * Retorna o último valor (double) gerado anteriormente (se aplicável)
     * @return (double) 
     */
    public static double getLastRandomDouble() { return lastRandomDouble; }

    /**
     * Retorna o último valor (int) gerado anteriormente (se aplicável)
     * @return (int)
     */
    public static int getLastRandomInt() { return lastRandomInt; }
}
