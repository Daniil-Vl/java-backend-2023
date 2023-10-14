package edu.hw1.task4;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String incorrectString) {
        StringBuilder stringBuilder = new StringBuilder();

        int pos = 0;

        while (pos + 1 < incorrectString.length()) {
            stringBuilder.append(incorrectString.charAt(pos + 1));
            stringBuilder.append(incorrectString.charAt(pos));
            pos += 2;
        }

        if (incorrectString.length() % 2 != 0) {
            stringBuilder.append(incorrectString.charAt(incorrectString.length() - 1));
        }

        return stringBuilder.toString();
    }
}
