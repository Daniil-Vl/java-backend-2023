package edu.hw1.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution4Test {

    @Test
    void evenLengthString() {
        String uncorrectString = "123456";
        String actual = Solution4.fixString(uncorrectString);
        String expected = "214365";
        assertEquals(expected, actual);
    }

    @Test
    void oddLengthString() {
        String uncorrectString = "badce";
        String actual = Solution4.fixString(uncorrectString);
        String expected = "abcde";
        assertEquals(expected, actual);
    }

    @Test
    void stringWithSeveralWords() {
        String uncorrectString = "hTsii  s aimex dpus rtni.g";
        String actual = Solution4.fixString(uncorrectString);
        String expected = "This is a mixed up string.";
        assertEquals(expected, actual);
    }

    @Test
    void emptyString() {
        String uncorrectString = "";
        String actual = Solution4.fixString(uncorrectString);
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    void stringWithOneCharacter() {
        String uncorrectString = "a";
        String actual = Solution4.fixString(uncorrectString);
        String expected = "a";
        assertEquals(expected, actual);
    }
}
