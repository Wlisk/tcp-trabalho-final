package exceptions;

/** Erro em caso um objeto seja nulo. */
public final class NullObjectException extends Exception {
    /**
     * @param errorMessage a mensagem de erro a ser propagada
     */
    public NullObjectException(String errorMessage) {
        super(errorMessage);
    }
}
