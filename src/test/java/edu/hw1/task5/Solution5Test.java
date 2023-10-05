package edu.hw1.task5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution5Test {

    @Test
    void test1() {
        long num = 11211230;
        assertTrue(Solution5.isPalindromeDescendant(num));
    }

    @Test
    void test2() {
        long num = 13001120;
        assertTrue(Solution5.isPalindromeDescendant(num));
    }

    @Test
    void test3() {
        long num = 23336014;
        assertTrue(Solution5.isPalindromeDescendant(num));
    }

    @Test
    void test4() {
        long num = 11;
        assertTrue(Solution5.isPalindromeDescendant(num));
    }

    @Test
    void oneDigit() {
        long num = 1;
        assertFalse(Solution5.isPalindromeDescendant(num));
    }

    @Test
    void nonPalindrome() {
        long num = 1234;
        assertFalse(Solution5.isPalindromeDescendant(num));
    }

    @Test
    void palindrome() {
        long num = 1221;
        assertTrue(Solution5.isPalindromeDescendant(num));
    }

}
