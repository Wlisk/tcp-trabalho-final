package utils;

/** Classe com métodos estáticos para trabalhar com objetos no geral */
public class Objects {
    /**
     * Converte um objeto qualquer em sua representação booleana, 
     * sendo verdadeiro caso o objeto seja diferente de null
     * @param obj o objeto a ser convertido
     * @return (boolean) o resultado da conversão
     */
    public static boolean toBool(Object obj) {
        return (obj == null) ? false : true;
    }
}
