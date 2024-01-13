package exceptions;

/** Erro para caso uma string for vazia ou nula. */
public final class EmptyStringException extends Exception { 
    /**
     * @param errorMessage a mensagem de erro a ser propagada
     */
    public EmptyStringException(String errorMessage) {
        super(errorMessage);
    }
}