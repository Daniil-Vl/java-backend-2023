package edu.hw1.task4;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String uncorrectString) {
        StringBuilder stringBuilder = new StringBuilder();

        int pos = 0;

        while (pos + 1 < uncorrectString.length()) {
            stringBuilder.append(uncorrectString.charAt(pos + 1));
            stringBuilder.append(uncorrectString.charAt(pos));
            pos += 2;
        }

        if (uncorrectString.length() % 2 != 0) {
            stringBuilder.append(uncorrectString.charAt(uncorrectString.length() - 1));
        }

        return stringBuilder.toString();
    }
}
