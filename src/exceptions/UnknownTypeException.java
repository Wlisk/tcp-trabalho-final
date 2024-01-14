package exceptions;

/** Erro em caso um tipo desconhecido de enum seja passado/verificado. */
public final class UnknownTypeException extends Exception {
    /**
     * @param errorMessage a mensagem de erro a ser propagada
     */
    public UnknownTypeException(String errorMessage) {
        super(errorMessage);
    }
}
