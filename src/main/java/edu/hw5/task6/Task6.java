package edu.hw5.task6;

import java.util.regex.Pattern;

public class Task6 {

    private Task6() {
    }

    /**
     * Checks, if first string is subsequence of second string
     *
     * @param first  - string, which can be subsequence
     * @param second - string, where first can be subsequence
     * @return true, if first is subsequence of second, otherwise returns false
     */
    public static boolean isSubsequence(String first, String second) {
        StringBuilder regularExpressionBuilder = new StringBuilder(".*");

        for (char c : first.toCharArray()) {
            regularExpressionBuilder
                .append(Pattern.quote(String.valueOf(c)))
                .append(".*");
        }

        return Pattern.matches(regularExpressionBuilder.toString(), second);
    }
}
