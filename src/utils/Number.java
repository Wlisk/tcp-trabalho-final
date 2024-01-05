package utils;

import exceptions.NumberOverflowException;

/**
 * Class with static properties and methods to manage number limitations 
 * throwing errors in case the limits are passed.
 * <p>
 * Notice that it's not intended to be instantiated.
 * @see exceptions.NumberOverflowException
 */
public final class Number {
    /** Minimum value for a decimal percentage */
    public static final double MIN_PERCENTAGE = 0.0;
    /** Maximum value for a decimal percentage */
    public static final double MAX_PERCENTAGE = 1.0;
    
    /**
     * Checks if a (int)number has passed the limits specified (inclusive)
     * @param min minimum value of the (int)number can be
     * @param max maximum value of the (int)number can be
     * @param number to verify the limits for
     * @throws NumberOverflowException  if the (int)number is outside the limit specified
     */
    public static void limitTo(int min, int max, int number) throws NumberOverflowException {
        if(number < min || number > max)
            throw new NumberOverflowException("Numero precisa ser entre " + min + " e " + max);
    }

    /**
     * Checks if a (double)number has passed the limits specified (inclusive)
     * @param min minimum value of the (double)number can be
     * @param max maximum value of the (double)number can be
     * @param number to verify the limits for
     * @throws NumberOverflowException if the (double)number is outside the limit specified
     */
    public static void limitTo(double min, double max, double number) throws NumberOverflowException {
        if(number < min || number > max)
            throw new NumberOverflowException("Numero precisa ser entre " + min + " e " + max);
    }

}
