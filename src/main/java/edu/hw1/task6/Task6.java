package edu.hw1.task6;

import java.util.Arrays;
import java.util.Collections;

public class Task6 {

    private int count = 0;

    @SuppressWarnings("MagicNumber")
    public int countK(int num) {

        if (num / 10000 != 0 || num / 1000 == 0) {
            throw new IllegalArgumentException("Number must have 4 digits");
        }

        if (num == 6174) {
            return this.count;
        }

        Integer[] increasingOrder = new Integer[4];
        Integer[] decreasingOrder = new Integer[4];
        int temp = num;

        for (int i = 0; i < 4; i++) {
            increasingOrder[i] = temp % 10;
            decreasingOrder[i] = temp % 10;
            temp /= 10;
        }

        Arrays.sort(increasingOrder);
        Arrays.sort(decreasingOrder, Collections.reverseOrder());

        int first = increasingOrder[0] * 1000 + increasingOrder[1] * 100 + increasingOrder[2] * 10 + increasingOrder[3];
        int second =
            decreasingOrder[0] * 1000 + decreasingOrder[1] * 100 + decreasingOrder[2] * 10 + decreasingOrder[3];

        int min = Math.min(first, second);
        int max = first + second - min;
        int diff = max - min;

        this.count++;
        return countK(diff);
    }
}
