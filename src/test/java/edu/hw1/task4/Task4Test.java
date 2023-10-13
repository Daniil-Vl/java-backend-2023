package edu.hw1.task4;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task4Test {

    private static Stream<Arguments> incorrectStrings() {
        return Stream.of(
            Arguments.of("123456", "214365"),
            Arguments.of("badce", "abcde"),
            Arguments.of("hTsii  s aimex dpus rtni.g", "This is a mixed up string."),
            Arguments.of("a", "a"),
            Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectStrings")
    void testRepairingIncorrectWords(String incorrectString, String expected) {
        assertThat(Task4.fixString(incorrectString)).isEqualTo(expected);
    }
}
