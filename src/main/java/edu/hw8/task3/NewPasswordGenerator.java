package edu.hw8.task3;

import java.util.ArrayDeque;
import java.util.Deque;

public class NewPasswordGenerator {
    public static final int MAX_PASSWORD_LENGTH = 6;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private final Deque<String> currentPasswords = new ArrayDeque<>();

    public NewPasswordGenerator() {
        this.currentPasswords.add("");
    }

    public String getNextPassword() {
        String currentPassword = currentPasswords.pollFirst();

        if (currentPassword == null) {
            return null;
        }

        if (currentPassword.length() < MAX_PASSWORD_LENGTH) {
            for (char c : ALPHABET) {
                currentPasswords.add(currentPassword + c);
            }
        }

        return currentPassword;
    }
}
