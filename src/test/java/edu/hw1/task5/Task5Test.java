package edu.hw1.task5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class Task5Test {

    @Test
    void test1() {
        long num = 11211230;
        assertThat(Task5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void test2() {
        long num = 13001120;
        assertThat(Task5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void test3() {
        long num = 23336014;
        assertThat(Task5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void test4() {
        long num = 11;
        assertThat(Task5.isPalindromeDescendant(num)).isTrue();
    }

    @Test
    void oneDigit() {
        long num = 1;
        assertThat(Task5.isPalindromeDescendant(num)).isFalse();
    }

    @Test
    void nonPalindrome() {
        long num = 1234;
        assertThat(Task5.isPalindromeDescendant(num)).isFalse();
    }

    @Test
    void palindrome() {
        long num = 1221;
        assertThat(Task5.isPalindromeDescendant(num)).isTrue();
    }

}
