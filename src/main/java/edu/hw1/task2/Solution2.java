package edu.hw1.task2;

public class Solution2 {
    public static int countDigits(int number) {
        int resultCount = 1;

        while (number / 10 != 0) {
            resultCount++;
            number /= 10;
        }

        return resultCount;
    }
}
