package edu.hw1.task2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    void numberZero() {
        int num = 0;
        int actual = Task2.countDigits(num);
        int expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void oneDigit() {
        int num = 5;
        int actual = Task2.countDigits(num);
        int expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void twoDigits() {
        int num = 34;
        int actual = Task2.countDigits(num);
        int expected = 2;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void threeDigits() {
        int num = 544;
        int actual = Task2.countDigits(num);
        int expected = 3;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void fourDigits() {
        int num = 4666;
        int actual = Task2.countDigits(num);
        int expected = 4;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void negativeNumber() {
        int num = -4666;
        int actual = Task2.countDigits(num);
        int expected = 4;
        assertThat(actual).isEqualTo(expected);
    }
}
