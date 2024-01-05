package exceptions;

public final class MaxStringSizeException extends Exception { 
    public MaxStringSizeException() {
        super("Max String size reached!");
    }

    public MaxStringSizeException(int maxsize) {
        super("The max size for the String is " + maxsize);
    }

    public MaxStringSizeException(String errorMessage) {
        super(errorMessage);
    }
}
