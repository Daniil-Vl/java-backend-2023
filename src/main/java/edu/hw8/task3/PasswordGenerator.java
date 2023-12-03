package edu.hw8.task3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PasswordGenerator {
    public static final int MAX_PASSWORD_LENGTH = 5;
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    private final Deque<String> currentPasswords = new ArrayDeque<>();
    private final static int BATCH_SIZE = 36;

    public PasswordGenerator() {
        this.currentPasswords.add("");
    }

    /**
     * Returns batches with 36 passwords
     *
     * @return batch of passwords
     */
    public List<String> getNextPasswordBatch() {
        String currentPassword = currentPasswords.pollFirst();

        if (currentPassword == null) {
            return null;
        }

        if (currentPassword.length() < MAX_PASSWORD_LENGTH) {
            List<String> result = new ArrayList<>(BATCH_SIZE);
            for (char c : ALPHABET) {
                String temp = currentPassword + c;
                currentPasswords.add(temp);
                result.add(temp);
            }
            return result;
        }

        return List.of(currentPassword);
    }
}
