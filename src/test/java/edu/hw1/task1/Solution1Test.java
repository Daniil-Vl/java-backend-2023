package edu.hw1.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Solution1Test {
    @Test
    @DisplayName("Tests from task №1")
    void firstTest() {
        String time = "01:00";
        int actual = Solution1.minutesToSeconds(time);
        int expected = 60;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test from task №2")
    void secondTest() {
        String time = "13:56";
        int actual = Solution1.minutesToSeconds(time);
        int expected = 836;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test from task №3")
    void thirdTest() {
        String time = "10:60";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void negativeMinutes() {
        String time = "10:-50";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void negativeHours() {
        String time = "-10:50";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void zeroHoursAndMinutes() {
        String time = "00:00";
        int actual = Solution1.minutesToSeconds(time);
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void throwsNumberFormatException() {
        String time = "a:00";
        Assertions.assertThrows(NumberFormatException.class, () -> Solution1.minutesToSeconds(time));
    }

    @Test
    void timeWithoutHoursOrMinutes() {
        String time = ":10";
        Assertions.assertThrows(NumberFormatException.class, () -> Solution1.minutesToSeconds(time));
    }

}
