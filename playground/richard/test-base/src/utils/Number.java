package utils;

import utils.exceptions.NumberOverflowException;

public class Number {
    public static final double MIN_PERCENTAGE = 0.0;
    public static final double MAX_PERCENTAGE = 1.0;
    
    public static void limitTo(int min, int max, int number) throws NumberOverflowException {
        if(number < min || number > max)
            throw new NumberOverflowException("Numero precisa ser entre " + min + " e " + max);
    }

    public static void limitTo(double min, double max, double number) throws NumberOverflowException {
        if(number < min || number > max)
            throw new NumberOverflowException("Numero precisa ser entre " + min + " e " + max);
    }

}
