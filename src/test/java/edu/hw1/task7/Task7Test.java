package edu.hw1.task7;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task7Test {

    private static Stream<Arguments> numbersForLeftRotation() {
        return Stream.of(
            Arguments.of(16, 1, 1),
            Arguments.of(17, 2, 6),
            Arguments.of(17, 0, 17),
            Arguments.of(17, 32, 17),
            Arguments.of(17, 32, 17),
            Arguments.of(10, -1, 5)
        );
    }

    private static Stream<Arguments> invalidNumbers() {
        return Stream.of(
            Arguments.of(0, 13, 0),
            Arguments.of(-10, 2, 0)
        );
    }

    private static Stream<Arguments> numbersForRightRotation() {
        return Stream.of(
            Arguments.of(8, 1, 4),
            Arguments.of(6, 2, 5),
            Arguments.of(5, -1, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("numbersForLeftRotation")
    void testLeftRotation(int num, int shift, int expected) {
        assertThat(Task7.rotateLeft(num, shift)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("numbersForRightRotation")
    void testRightRotation(int num, int shift, int expected) {
        assertThat(Task7.rotateRight(num, shift)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("invalidNumbers")
    void testInvalidNumbers(int num, int shift, int expected) {
        assertThrows(IllegalArgumentException.class, () -> Task7.rotateLeft(num, shift));
    }
}
