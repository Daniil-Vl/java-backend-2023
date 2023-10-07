package edu.hw1.task7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Solution7Test {

    @Test
    void rotateLeft1() {
        int num = 16;
        int shift = 1;
        int actual = Solution7.rotateLeft(num, shift);
        int expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void rotateLeft2() {
        int num = 17;
        int shift = 2;
        int actual = Solution7.rotateLeft(num, shift);
        int expected = 6;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void rotateLeftZeroShift() {
        int num = 17;
        int shift = 0;
        int actual = Solution7.rotateLeft(num, shift);
        int expected = 17;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void rotateLeft32() {
        int num = 17;
        int shift = 32;
        int actual = Solution7.rotateLeft(num, shift);
        int expected = 17;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void rotateLeftZero() {
        int num = 0;
        int shift = 13;
        int actual = Solution7.rotateLeft(num, shift);
        int expected = 0;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void rotateRight1() {
        int num = 8;
        int shift = 1;
        int actual = Solution7.rotateRight(num, shift);
        int expected = 4;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void rotateRight2() {
        int num = 6;
        int shift = 2;
        int actual = Solution7.rotateRight(num, shift);
        int expected = 5;
        assertThat(actual).isEqualTo(expected);
    }
}
