package edu.hw3.task4;

import java.util.HashMap;
import java.util.Map;

public class Task4 {
    private static final int MAX_ALLOWED_NUMBER = 3999;

    private static final HashMap<Integer, String> HUNDREDS = new HashMap<>(Map.of(
        0, "",
        1, "C",
        2, "CC",
        3, "CCC",
        4, "CD",
        5, "D",
        6, "DC",
        7, "DCC",
        8, "DCCC",
        9, "CM"
    ));

    private static final HashMap<Integer, String> DOZENS = new HashMap<>(Map.of(
        0, "",
        1, "X",
        2, "XX",
        3, "XXX",
        4, "XL",
        5, "L",
        6, "LX",
        7, "LXX",
        8, "LXXX",
        9, "XC"
    ));

    private static final HashMap<Integer, String> UNITS = new HashMap<>(Map.of(
        0, "",
        1, "I",
        2, "II",
        3, "III",
        4, "IV",
        5, "V",
        6, "VI",
        7, "VII",
        8, "VIII",
        9, "IX"
    ));

    private Task4() {
    }

    /**
     * Convert number from arabic format to roman
     *
     * @param num from 0 to 3999 inclusively
     */
    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(Integer num) throws IllegalArgumentException {
        if (num > MAX_ALLOWED_NUMBER || num < 1) {
            throw new IllegalArgumentException("Num should lie in [1, 3999]");
        }

        // Convert thousands, hundreds, dozens and units to roman independently and then concat resulting strings
        return "M".repeat(num / 1000)
            + HUNDREDS.get(num / 100 % 10)
            + DOZENS.get(num / 10 % 10)
            + UNITS.get(num % 10);
    }
}
