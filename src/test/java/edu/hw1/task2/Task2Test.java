package edu.hw1.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void numberZero() {
        long num = 0;
        int actual = Task2.countDigits(num);
        int expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void oneDigit() {
        long num = 5;
        int actual = Task2.countDigits(num);
        int expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void twoDigits() {
        long num = 34;
        int actual = Task2.countDigits(num);
        int expected = 2;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void threeDigits() {
        long num = 544;
        int actual = Task2.countDigits(num);
        int expected = 3;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void fourDigits() {
        long num = 4666;
        int actual = Task2.countDigits(num);
        int expected = 4;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void negativeNumber() {
        long num = -4666;
        int actual = Task2.countDigits(num);
        int expected = 4;
        assertThat(actual).isEqualTo(expected);
    }
}
