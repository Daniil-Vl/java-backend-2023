package edu.hw5.task8;

import java.util.regex.Pattern;

public class Task8 {
    /**
     * odd length strings
     */
    private static final Pattern FIRST_PATTERN = Pattern.compile("(0(00|01|10|11)*)|(1(00|01|10|11)*)");

    /**
     * String starts with 0 and has an odd length, or starts with 1 and has an even length
     */
    private static final Pattern SECOND_PATTERN = Pattern.compile("(0(00|01|10|11)*)|(1[01](00|01|10|11)*)");

    /**
     * String in which the number of 0 is a multiple of 3
     */
    private static final Pattern THIRD_PATTERN = Pattern.compile("(1*01*01*01*)*");

    /**
     * Strings in which each odd character is equal to 1
     */
    private static final Pattern FIFTH_PATTERN = Pattern.compile("(1[01])*1?");

    private Task8() {
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

    public static boolean fifthMatch(String string) {
        return FIFTH_PATTERN.matcher(string).matches();
    }

}
