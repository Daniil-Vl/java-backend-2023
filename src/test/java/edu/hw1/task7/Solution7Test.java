package edu.hw1.task7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution7Test {

    @Test
    void rotateLeft1() {
        int num = 16;
        int shift = 1;
        int actual = Solution7.rotateLeft(num, shift);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void rotateLeft2() {
        int num = 17;
        int shift = 2;
        int actual = Solution7.rotateLeft(num, shift);
        int expected =  6;
        assertEquals(expected, actual);
    }

    @Test
    void rotateLeftZeroShift() {
        int num = 17;
        int shift = 0;
        int actual = Solution7.rotateLeft(num, shift);
        int expected =  17;
        assertEquals(expected, actual);
    }

    @Test
    void rotateLeft32() {
        int num = 17;
        int shift = 32;
        int actual = Solution7.rotateLeft(num, shift);
        int expected =  17;
        assertEquals(expected, actual);
    }

    @Test
    void rotateLeftZero() {
        int num = 0;
        int shift = 13;
        int actual = Solution7.rotateLeft(num, shift);
        int expected =  0;
        assertEquals(expected, actual);
    }

    @Test
    void rotateRight1() {
        int num = 8;
        int shift = 1;
        int actual = Solution7.rotateRight(num, shift);
        int expected =  4;
        assertEquals(expected, actual);
    }

    @Test
    void rotateRight2() {
        int num = 6;
        int shift = 2;
        int actual = Solution7.rotateRight(num, shift);
        int expected =  5;
        assertEquals(expected, actual);
    }
}
