package edu.hw1.task2;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(long number) {
        int resultCount = 1;
        long n = number;

        while (n / 10 != 0) {
            resultCount++;
            n /= 10;
        }

        return resultCount;
    }
}
