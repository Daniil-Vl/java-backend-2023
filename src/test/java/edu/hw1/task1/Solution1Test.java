package edu.hw1.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Solution1Test {
    @Test
    @DisplayName("Tests from task №1")
    void firstTest() {
        String time = "01:00";
        int actual = Solution1.minutesToSeconds(time);
        int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test from task №2")
    void secondTest() {
        String time = "13:56";
        int actual = Solution1.minutesToSeconds(time);
        int expected = 836;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test from task №3")
    void thirdTest() {
        String time = "10:60";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void negativeMinutes() {
        String time = "10:-50";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void negativeHours() {
        String time = "-10:50";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void zeroHoursAndMinutes() {
        String time = "00:00";
        int actual = Solution1.minutesToSeconds(time);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void hoursNotInt() {
        String time = "a:00";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void withoutHours() {
        String time = ":10";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void minutesNotInt() {
        String time = "10:b";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void withoutMinutes() {
        String time = "10:";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void withoutHoursAndMinutes() {
        String time = ":";
        int actual = Solution1.minutesToSeconds(time);
        int expected = -1;
        assertEquals(expected, actual);
    }

}
