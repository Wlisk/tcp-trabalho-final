package exceptions;

public final class TextureNotLoadedException extends Exception {
    public TextureNotLoadedException(String errorMessage) {
        super(errorMessage);
    }
}