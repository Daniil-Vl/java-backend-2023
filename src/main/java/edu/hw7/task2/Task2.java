package edu.hw7.task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    /**
     * Calculate factorial using parallelStream
     *
     * @param number - number to calculate factorial from
     * @return number factorial
     */
    public static long multiThreadFactorial(int number) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= number; i++) {
            numbers.add(i);
        }

        return numbers.parallelStream().reduce(
            1,
            (a, b) -> a * b
        );
    }
}
