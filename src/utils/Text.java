package utils;

/** 
 * Class with static methods to check or to verify strings 
 * <p>
 * Notice that it's not intended to be instantiated.
 */
public final class Text {
    /**
     * Checks if a string is empty/null or not
     * @param str the string to check for (reference)
     * @return the result of the checking
     */
    public static boolean stringIsEmpty(String str) {
        return (str == null || str.length() == 0);
    }
}
