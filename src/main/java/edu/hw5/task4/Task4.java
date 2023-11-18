package edu.hw5.task4;

import java.util.regex.Pattern;

public class Task4 {

    private Task4() {
    }

    /**
     * Pattern to detect, whether string contains at least one of these symbols "~ ! @ # $ % ^ & * |"
     */
    private static final Pattern SECURITY_PATTERN = Pattern.compile(".*[~!@#$%^&*|].*");

    public static boolean checkPassword(String password) {
        return SECURITY_PATTERN.matcher(password).matches();
    }
}
