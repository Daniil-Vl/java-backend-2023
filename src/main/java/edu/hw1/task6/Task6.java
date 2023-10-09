package edu.hw1.task6;

import java.util.Arrays;

public class Task6 {

    private int count = 0;

    @SuppressWarnings("MagicNumber")
    public int countK(int num) {

        if (num == 6174) {
            return this.count;
        }

        // Validate num only at the first step of the recursion
        if (this.count == 0) {
            validate(num);
        }

        Integer[] digits = new Integer[4];
        int temp = num;

        for (int i = 0; i < 4; i++) {
            digits[i] = temp % 10;
            temp /= 10;
        }

        Arrays.sort(digits);

        int first = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
        int second = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0];

        int min = Math.min(first, second);
        int max = first + second - min;
        int diff = max - min;

        this.count++;
        return countK(diff);
    }

    /**
     * Checks that num have only 4 digits, and that all digits are not the same
     *
     * @param num - input number
     * @throws IllegalArgumentException if num have more or less than 4 digits or if all digits are the same
     */
    @SuppressWarnings("MagicNumber")
    private void validate(int num) throws IllegalArgumentException {
        if (num <= 1000) {
            throw new IllegalArgumentException("Num must be more than 1000");
        }

        if (num / 10000 != 0) {
            throw new IllegalArgumentException("Num must have no more than 4 digits");
        }

        int[] digits = new int[4];
        int div = 1000;
        for (int index = 0; index < 4; index++) {
            digits[index] = num / div % 10;
            div /= 10;
        }

        if (digits[0] == digits[1] && digits[1] == digits[2] && digits[2] == digits[3]) {
            throw new IllegalArgumentException("A number cannot have all the same digits");
        }
    }
}
