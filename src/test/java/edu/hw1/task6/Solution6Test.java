package edu.hw1.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution6Test {

    @Test
    void fiveTimes() {
        Solution6 sol = new Solution6();
        int num = 6621;
        int actual = sol.countK(num);
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void fourTimes() {
        Solution6 sol = new Solution6();
        int num = 6554;
        int actual = sol.countK(num);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void threeTimes() {
        Solution6 sol = new Solution6();
        int num = 1234;
        int actual = sol.countK(num);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    void zeroTimes() {
        Solution6 sol = new Solution6();
        int num = 6174;
        int actual = sol.countK(num);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void numberHaveMoreThan4Digits() {
        Solution6 sol = new Solution6();
        int num = 12345;
        assertThrows(IllegalArgumentException.class, () -> sol.countK(num));
    }

    @Test
    void numberHaveLessThan4Digits() {
        Solution6 sol = new Solution6();
        int num = 123;
        assertThrows(IllegalArgumentException.class, () -> sol.countK(num));
    }
}
