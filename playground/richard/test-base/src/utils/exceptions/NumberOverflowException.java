package utils.exceptions;

public class NumberOverflowException extends Exception {
    public NumberOverflowException(int maxvalue) {
        super("The max size for the number is " + maxvalue);
    }

    public NumberOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
