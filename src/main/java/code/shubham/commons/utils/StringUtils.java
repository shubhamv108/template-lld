package code.shubham.commons.utils;

public class StringUtils {

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

    public static String rightPadSpaces(final String value, final int length) {
        return String.format("%1$-" + length + "s", value);
    }
}