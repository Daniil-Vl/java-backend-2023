package edu.hw1.task2;

public class Solution2 {

    private Solution2() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {
        int resultCount = 1;
        int n = number;

        while (n / 10 != 0) {
            resultCount++;
            n /= 10;
        }

        return resultCount;
    }
}
