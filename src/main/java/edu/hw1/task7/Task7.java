package edu.hw1.task7;

public class Task7 {

    private static final String NON_POSITIVE_INPUT_NUMBER_EXCEPTION_MESSAGE = "Num must be positive";

    private Task7() {
    }

    public static int rotateLeft(int num, int shift) {
        if (num <= 0) {
            throw new IllegalArgumentException(NON_POSITIVE_INPUT_NUMBER_EXCEPTION_MESSAGE);
        }

        if (shift < 0) {
            return rotateRight(num, -shift);
        }

        int bitNumber = getBitNumber(num);
        int leftMostDiv = Math.round(Math.round(Math.pow(2, bitNumber)));
        return ((num << shift) % leftMostDiv) | (num >> (bitNumber - shift));
    }

    public static int rotateRight(int num, int shift) {
        if (num <= 0) {
            throw new IllegalArgumentException(NON_POSITIVE_INPUT_NUMBER_EXCEPTION_MESSAGE);
        }

        if (shift < 0) {
            return rotateLeft(num, -shift);
        }

        int bitNumber = getBitNumber(num);
        int leftMostDiv = Math.round(Math.round(Math.pow(2, bitNumber)));
        return (num >> shift) | ((num << (bitNumber - shift)) % leftMostDiv);
    }

    private static int getBitNumber(int num) {
        int count = 1;
        int temp = num;
        while (temp / 2 != 0) {
            count++;
            temp /= 2;
        }
        return count;
    }
}
