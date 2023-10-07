package edu.hw1.task2;

public class Task2 {

    private Task2() {
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
