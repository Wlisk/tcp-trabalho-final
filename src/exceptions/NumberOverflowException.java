package exceptions;

/** Erro em caso um número tenha passado em valor os limites especificados em código. */
public final class NumberOverflowException extends Exception {
    /**
     * @param errorMessage a mensagem de erro a ser propagada
     */
    public NumberOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
