package edu.hw5.task7;

import java.util.regex.Pattern;

public class Task7 {
    /**
     * String must contain 2 symbols 0 or 1 and third symbol 0
     */
    private static final Pattern FIRST_PATTERN = Pattern.compile("[01]{2}0[01]*");

    /**
     * String must start and ends with similar character
     */
    private static final Pattern SECOND_PATTERN = Pattern.compile("(^0[01]*0$)|(^1[01]*1$)|([01]?)");

    /**
     * String must contain 1-3 symbols 0 or 1
     */
    private static final Pattern THIRD_PATTERN = Pattern.compile("[01]{1,3}");

    private Task7() {
    }

    public static boolean firstMatch(String string) {
        return FIRST_PATTERN.matcher(string).matches();
    }

    public static boolean secondMatch(String string) {
        return SECOND_PATTERN.matcher(string).matches();
    }

    public static boolean thirdMatch(String string) {
        return THIRD_PATTERN.matcher(string).matches();
    }
}
