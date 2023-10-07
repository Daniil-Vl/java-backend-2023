package edu.hw1.task6;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task6Test {

    @Test
    void fiveTimes() {
        Task6 sol = new Task6();
        int num = 6621;
        int actual = sol.countK(num);
        int expected = 5;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void fourTimes() {
        Task6 sol = new Task6();
        int num = 6554;
        int actual = sol.countK(num);
        int expected = 4;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void threeTimes() {
        Task6 sol = new Task6();
        int num = 1234;
        int actual = sol.countK(num);
        int expected = 3;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void zeroTimes() {
        Task6 sol = new Task6();
        int num = 6174;
        int actual = sol.countK(num);
        int expected = 0;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void numberHaveMoreThan4Digits() {
        Task6 sol = new Task6();
        int num = 12345;
        assertThrows(IllegalArgumentException.class, () -> sol.countK(num));
    }

    @Test
    void numberHaveLessThan4Digits() {
        Task6 sol = new Task6();
        int num = 123;
        assertThrows(IllegalArgumentException.class, () -> sol.countK(num));
    }
}
