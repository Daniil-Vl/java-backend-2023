package edu.hw1.task6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task6Test {

    private static Stream<Arguments> validNumbers() {
        return Stream.of(
            Arguments.of(6621, 5),
            Arguments.of(6554, 4),
            Arguments.of(1234, 3),
            Arguments.of(1234, 3),
            Arguments.of(6174, 0),
            Arguments.of(2221, 5)
        );
    }

    private static Stream<Arguments> invalidNumbers() {
        return Stream.of(
            Arguments.of(12345),
            Arguments.of(123),
            Arguments.of(1000)
        );
    }

    @ParameterizedTest
    @MethodSource("validNumbers")
    void testValidNumbers(int num, int expected) {
        assertThat(Task6.countK(num)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("invalidNumbers")
    void testInvalidNumbers(int num) {
        assertThrows(IllegalArgumentException.class, () -> Task6.countK(num));
    }

}
