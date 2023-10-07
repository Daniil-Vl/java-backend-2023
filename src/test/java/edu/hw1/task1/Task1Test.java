package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Tests from task №1")
    void firstTest() {
        String time = "01:00";
        int actual = Task1.minutesToSeconds(time);
        int expected = 60;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test from task №2")
    void secondTest() {
        String time = "13:56";
        int actual = Task1.minutesToSeconds(time);
        int expected = 836;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test from task №3")
    void thirdTest() {
        String time = "10:60";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void negativeMinutes() {
        String time = "10:-50";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void negativeHours() {
        String time = "-10:50";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void zeroHoursAndMinutes() {
        String time = "00:00";
        int actual = Task1.minutesToSeconds(time);
        int expected = 0;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void hoursNotInt() {
        String time = "a:00";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void withoutHours() {
        String time = ":10";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void minutesNotInt() {
        String time = "10:b";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void withoutMinutes() {
        String time = "10:";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void withoutHoursAndMinutes() {
        String time = ":";
        int actual = Task1.minutesToSeconds(time);
        int expected = -1;
        assertThat(actual).isEqualTo(expected);
    }

}
