package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {
    public static final int MAX_PASSWORD_LENGTH = 4;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private List<String> currentPasswords = new ArrayList<>(List.of(""));
    private int currentPasswordLength = 0;

    private List<String> generate(String currPassword) {
        List<String> result = new ArrayList<>();
        for (char c : ALPHABET) {
            result.add(currPassword + c);
        }
        return result;
    }

    /**
     * Create list of passwords if can
     * This method cannot create new batch of passwords
     * if and only if length of passwords in current batch more than max password length (5)
     *
     * @return list with new batch of passwords, if cannot create new batch returns null
     */
    public List<String> generateNextPasswordsBatch() {
        if (currentPasswordLength > MAX_PASSWORD_LENGTH) {
            return null;
        }

        List<String> newBatchOfPasswords = new ArrayList<>();
        for (String currPassword : currentPasswords) {
            newBatchOfPasswords.addAll(generate(currPassword));
        }

        currentPasswords = newBatchOfPasswords;
        currentPasswordLength++;
        return newBatchOfPasswords;
    }
}
