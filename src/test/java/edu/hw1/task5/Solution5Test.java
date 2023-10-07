package edu.hw1.task5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class Solution5Test {

    @Test
    void test1() {
        long num = 11211230;
        assertThat(Solution5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void test2() {
        long num = 13001120;
        assertThat(Solution5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void test3() {
        long num = 23336014;
        assertThat(Solution5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void test4() {
        long num = 11;
        assertThat(Solution5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void oneDigit() {
        long num = 1;
        assertThat(Solution5.isPalindromeDescendant(num)).isFalse();
    }

    @Test
    void nonPalindrome() {
        long num = 1234;
        assertThat(Solution5.isPalindromeDescendant(num)).isFalse();
    }

    @Test
    void palindrome() {
        long num = 1221;
        assertThat(Solution5.isPalindromeDescendant(num)).isTrue();
    }

}
