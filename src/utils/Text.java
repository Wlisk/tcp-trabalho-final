package utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;

/** Classe com métodos estáticos para manipulação e checagem de strings */
public final class Text {
    /**
     * Checa se uma string é nula ou vazia
     * @param str a string a ser checada
     * @return (boolean) o resultado da checagem
     */
    public static boolean isEmptyString(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * <p> Código Retirado de org.apache.commons.text.WordUtils.wrap() 
     * {@link https://commons.apache.org/proper/commons-text/}
     *
     * <p>Leading spaces on a new line are stripped.
     * Trailing spaces are not stripped.</p>
     *
     * <table border="1">
     *  <caption>Examples</caption>
     *  <tr>
     *   <th>input</th>
     *   <th>wrapLength</th>
     *   <th>newLineString</th>
     *   <th>wrapLongWords</th>
     *   <th>wrapOn</th>
     *   <th>result</th>
     *  </tr>
     *  <tr>
     *   <td>null</td>
     *   <td>*</td>
     *   <td>*</td>
     *   <td>true/false</td>
     *   <td>*</td>
     *   <td>null</td>
     *  </tr>
     *  <tr>
     *   <td>""</td>
     *   <td>*</td>
     *   <td>*</td>
     *   <td>true/false</td>
     *   <td>*</td>
     *   <td>""</td>
     *  </tr>
     *  <tr>
     *   <td>"Here is one line of text that is going to be wrapped after 20 columns."</td>
     *   <td>20</td>
     *   <td>"\n"</td>
     *   <td>true/false</td>
     *   <td>" "</td>
     *   <td>"Here is one line of\ntext that is going\nto be wrapped after\n20 columns."</td>
     *  </tr>
     *  <tr>
     *   <td>"Here is one line of text that is going to be wrapped after 20 columns."</td>
     *   <td>20</td>
     *   <td>"&lt;br /&gt;"</td>
     *   <td>true/false</td>
     *   <td>" "</td>
     *   <td>"Here is one line of&lt;br /&gt;text that is going&lt;br /&gt;
     *   to be wrapped after&lt;br /&gt;20 columns."</td>
     *  </tr>
     *  <tr>
     *   <td>"Here is one line of text that is going to be wrapped after 20 columns."</td>
     *   <td>20</td>
     *   <td>null</td>
     *   <td>true/false</td>
     *   <td>" "</td>
     *   <td>"Here is one line of" + systemNewLine + "text that is going"
     *   + systemNewLine + "to be wrapped after" + systemNewLine + "20 columns."</td>
     *  </tr>
     *  <tr>
     *   <td>"Click here to jump to the commons website - https://commons.apache.org"</td>
     *   <td>20</td>
     *   <td>"\n"</td>
     *   <td>false</td>
     *   <td>" "</td>
     *   <td>"Click here to jump\nto the commons\nwebsite -\nhttps://commons.apache.org"</td>
     *  </tr>
     *  <tr>
     *   <td>"Click here to jump to the commons website - https://commons.apache.org"</td>
     *   <td>20</td>
     *   <td>"\n"</td>
     *   <td>true</td>
     *   <td>" "</td>
     *   <td>"Click here to jump\nto the commons\nwebsite -\nhttp://commons.apach\ne.org"</td>
     *  </tr>
     *  <tr>
     *   <td>"flammable/inflammable"</td>
     *   <td>20</td>
     *   <td>"\n"</td>
     *   <td>true</td>
     *   <td>"/"</td>
     *   <td>"flammable\ninflammable"</td>
     *  </tr>
     * </table>
     * @param str  the String to be word wrapped, may be null
     * @param wrapLength  the column to wrap the words at, less than 1 is treated as 1
     * @param newLineStr  the string to insert for a new line,
     *  {@code null} uses the system property line separator
     * @param wrapLongWords  true if long words (such as URLs) should be wrapped
     * @param wrapOn regex expression to be used as a breakable characters,
     *               if blank string is provided a space character will be used
     * @return a line with newlines inserted, {@code null} if null input
     */
    private static String wrap(final String str,
                              int wrapLength,
                              String newLineStr,
                              final boolean wrapLongWords,
                              String wrapOn) {
        if (str == null) {
            return null;
        }
        if (newLineStr == null) {
            newLineStr = System.lineSeparator();
        }
        if (wrapLength < 1) {
            wrapLength = 1;
        }
        if (StringUtils.isBlank(wrapOn)) {
            wrapOn = " ";
        }
        final Pattern patternToWrapOn = Pattern.compile(wrapOn);
        final int inputLineLength = str.length();
        int offset = 0;
        final StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
        int matcherSize = -1;

        while (offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset,
                    Math.min((int) Math.min(Integer.MAX_VALUE, offset + wrapLength + 1L), inputLineLength)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    matcherSize = matcher.end();
                    if (matcherSize != 0) {
                        offset += matcher.end();
                        continue;
                    }
                    offset += 1;
                }
                spaceToWrapAt = matcher.start() + offset;
            }

            // only last line without leading spaces is left
            if (inputLineLength - offset <= wrapLength) {
                break;
            }

            while (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset;
            }

            if (spaceToWrapAt >= offset) {
                // normal case
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;

            } else // really long word or URL
            if (wrapLongWords) {
                if (matcherSize == 0) {
                    offset--;
                }
                // wrap really long word one line at a time
                wrappedLine.append(str, offset, wrapLength + offset);
                wrappedLine.append(newLineStr);
                offset += wrapLength;
                matcherSize = -1;
            } else {
                // do not wrap really long word, just extend beyond limit
                matcher = patternToWrapOn.matcher(str.substring(offset + wrapLength));
                if (matcher.find()) {
                    matcherSize = matcher.end() - matcher.start();
                    spaceToWrapAt = matcher.start() + offset + wrapLength;
                }

                if (spaceToWrapAt >= 0) {
                    if (matcherSize == 0 && offset != 0) {
                        offset--;
                    }
                    wrappedLine.append(str, offset, spaceToWrapAt);
                    wrappedLine.append(newLineStr);
                    offset = spaceToWrapAt + 1;
                } else {
                    if (matcherSize == 0 && offset != 0) {
                        offset--;
                    }
                    wrappedLine.append(str, offset, str.length());
                    offset = inputLineLength;
                    matcherSize = -1;
                }
            }
        }

        if (matcherSize == 0 && offset < inputLineLength) {
            offset--;
        }

        // Whatever is left in line is short enough to just pass through
        wrappedLine.append(str, offset, str.length());

        return wrappedLine.toString();
    }

    /**
     * Realiza a quebra de linhas de um texto, identificando palavras por espaço {@code ' '}.
     * 
     * <p> Código Retirado de org.apache.commons.text.WordUtils.wrap() 
     * {@link https://commons.apache.org/proper/commons-text/}
     * 
     * @param str string para realizar a quebra de linhas
     * @param wrapLength o tamanho máximo para a quebra de linhas
     * @return (String) o texto com quebras de linhas {@code '\n'}
     */
    public static String wrap(final String str, final int wrapLength) {
        return wrap(str, wrapLength, null, false, " ");
    }

    /**
     * Concantena duas listas de texto criando uma nova lista de texto
     * <p> Retirado de {@link https://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java StackOverflow}
     * @param firstList a primeira lista na ordem
     * @param secondList a segunda lista na ordem
     * @return (String[]) a nova lista de texto concatenada
     */
    public static String[] concat(final String[] firstList, final String[] secondList) {
        final String[] _listExtended = Arrays.copyOf(
            firstList, 
            firstList.length + secondList.length
        );
        System.arraycopy(secondList, 0, _listExtended, firstList.length, secondList.length);

        return _listExtended;
    }
}
