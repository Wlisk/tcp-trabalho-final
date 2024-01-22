package utils;

import exceptions.NumberOverflowException;

/**
 * Classe com propriedades e métodos estáticos para gerenciar números 
 * no geral.
 * <p>
 * Além disso, gerencia limitação de números gerando erros (throw) 
 * em caso dos limites impostos serem passados.
 * @see exceptions.NumberOverflowException
 */
public final class Number {
    /** Valor (double) mínimo para uma porcentagem em decimal */
    public static final double MIN_DPERCENTAGE = 0.0;
    /** Valor (double) máximo para uma porcentagem em decimal */
    public static final double MAX_DPERCENTAGE = 1.0;
    
    /**
     * Checa se um número (int) passou dos límites especificados 
     * @param min valor mínimo que um número (int) pode chegar (inclusivo)
     * @param max valor máximo que um número (int) pode chegar (inclusivo)
     * @param number o número a ser checado/verificado
     * @throws NumberOverflowException  se o número (int) está fora dos limites especificados
     */
    public static void limitTo(int min, int max, int number) throws NumberOverflowException {
        if(number < min || number > max)
            throw new NumberOverflowException("Numero precisa ser entre " + min + " e " + max);
    }

    /**
     * Checa se um número (double) passou dos límites especificados 
     * @param min valor mínimo que um número (double) pode chegar (inclusivo)
     * @param max valor máximo que um número (double) pode chegar (inclusivo)
     * @param number o número a ser checado/verificado
     * @throws NumberOverflowException  se o número (double) está fora dos limites especificados
     */
    public static void limitTo(double min, double max, double number) throws NumberOverflowException {
        if(number < min || number > max)
            throw new NumberOverflowException("Numero precisa ser entre " + min + " e " + max);
    }

    /**
     * Retorna um valor qualquer de porcentagem em sua representação 
     * decimal de porcentagem (double)
     * @param percent o valor de porcentagem a ser convertido
     * @return (double) o valor em decimal da porcentagem
     */
    public static double dPercentage(double percent) {
        return percent * 0.01;
    }

    /** Usado quando índice numérico não encontrado */
    public static final int NOT_FOUND = -1;
}
