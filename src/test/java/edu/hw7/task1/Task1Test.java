package edu.hw7.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    private static Stream<Arguments> data() {
        return Stream.of(
            Arguments.of(100, 10000),
            Arguments.of(1000, 100000),
            Arguments.of(100000, 10000000),
            Arguments.of(200000, 20000000),
            Arguments.of(300000, 30000000)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void multiThreadCount(int number, int expected) {
        Task1 task1 = new Task1();
        assertThat(task1.multiThreadCount(number)).isEqualTo(expected);
    }
}
