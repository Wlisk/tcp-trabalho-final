package utils;

/** Classe com métodos estáticos para checar ou verificar strings */
public final class Text {
    /**
     * Checa se uma string é nula ou vazia
     * @param str a string a ser checada
     * @return (boolean) o resultado da checagem
     */
    public static boolean stringIsEmpty(String str) {
        return (str == null || str.length() == 0);
    }
}
