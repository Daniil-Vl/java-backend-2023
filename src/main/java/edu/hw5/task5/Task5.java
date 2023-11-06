package edu.hw5.task5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {
    }

    /**
     * Pattern to detect, whether number having right format for russian car license plates
     * <p>
     * Correct numbers:
     * А123ВЕ777
     * О777ОО177
     * <p>
     * Incorrect:
     * 123АВЕ777
     * А123ВГ77
     * А123ВЕ7777
     */
    private static final Pattern NUMBER_FORMAT = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");

    public static boolean checkNumber(String number) {
        return NUMBER_FORMAT.matcher(number).matches();
    }
}
