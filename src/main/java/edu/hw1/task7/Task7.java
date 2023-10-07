package edu.hw1.task7;

public class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int num, int shift) {
        int bitNumber = getBitNumber(num);
        int leftMostDiv = Math.round(Math.round(Math.pow(2, bitNumber)));
        return ((num << shift) % leftMostDiv) | (num >> (bitNumber - shift));
    }

    public static int rotateRight(int num, int shift) {
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
