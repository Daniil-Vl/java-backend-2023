package edu.hw1.task4;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    @Test
    void evenLengthString() {
        String uncorrectString = "123456";
        String actual = Task4.fixString(uncorrectString);
        String expected = "214365";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void oddLengthString() {
        String uncorrectString = "badce";
        String actual = Task4.fixString(uncorrectString);
        String expected = "abcde";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void stringWithSeveralWords() {
        String uncorrectString = "hTsii  s aimex dpus rtni.g";
        String actual = Task4.fixString(uncorrectString);
        String expected = "This is a mixed up string.";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void emptyString() {
        String uncorrectString = "";
        String actual = Task4.fixString(uncorrectString);
        String expected = "";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void stringWithOneCharacter() {
        String uncorrectString = "a";
        String actual = Task4.fixString(uncorrectString);
        String expected = "a";
        assertThat(actual).isEqualTo(expected);
    }
}
