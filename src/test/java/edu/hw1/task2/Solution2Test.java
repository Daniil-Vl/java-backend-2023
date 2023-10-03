package edu.hw1.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution2Test {

    @Test
    void numberZero() {
        int num = 0;
        int actual = Solution2.countDigits(num);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void oneDigit() {
        int num = 5;
        int actual = Solution2.countDigits(num);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void twoDigits() {
        int num = 34;
        int actual = Solution2.countDigits(num);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void threeDigits() {
        int num = 544;
        int actual = Solution2.countDigits(num);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void fourDigits() {
        int num = 4666;
        int actual = Solution2.countDigits(num);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void negativeNumber() {
        int num = -4666;
        int actual = Solution2.countDigits(num);
        int expected = 4;
        assertEquals(expected, actual);
    }
}
