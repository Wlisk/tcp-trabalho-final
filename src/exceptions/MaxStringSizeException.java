package exceptions;

/** Erro em caso uma string tenha passado em tamanho os limites especificados em código. */
public final class MaxStringSizeException extends Exception { 
    /**
     * Contrutor com mensagem pronta indicando o tamanho máximo da string
     * @param maxsize o tamanho máximo da string 
     */
    public MaxStringSizeException(int maxsize) {
        super("The max size for the String is " + maxsize);
    }

    /**
     * @param errorMessage a mensagem de erro a ser propagada
     */
    public MaxStringSizeException(String errorMessage) {
        super(errorMessage);
    }
}
