package edu.hw7.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(1, 1L),
            Arguments.of(5, 120L),
            Arguments.of(10, 3628800L),
            Arguments.of(12, 479001600L)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void multiThreadFactorial(int number, long expected) {
        assertThat(Task2.multiThreadFactorial(number)).isEqualTo(expected);
    }
}
