package edu.hw1.task2;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private static Stream<Arguments> numbersForTest() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(5, 1),
            Arguments.of(34, 2),
            Arguments.of(544, 3),
            Arguments.of(4666, 4),
            Arguments.of(-4666, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("numbersForTest")
    void testDigitCount(int num, int expected) {
        assertThat(Task2.countDigits(num)).isEqualTo(expected);
    }
}
