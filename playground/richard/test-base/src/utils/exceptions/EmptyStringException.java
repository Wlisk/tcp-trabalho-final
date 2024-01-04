package utils.exceptions;

public final class EmptyStringException extends Exception { 
    public EmptyStringException() {
        super("String passed is NULL");
    }

    public EmptyStringException(String errorMessage) {
        super(errorMessage);
    }
}