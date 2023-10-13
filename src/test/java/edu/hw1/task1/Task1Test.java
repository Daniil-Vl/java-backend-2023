package edu.hw1.task1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    private static Stream<Arguments> timeActualAndExpected() {
        return Stream.of(
            Arguments.of("01:00", 60),
            Arguments.of("13:56", 836),
            Arguments.of("10:60", -1),
            Arguments.of("10:-50", -1),
            Arguments.of("-10:50", -1),
            Arguments.of("00:00", 0),
            Arguments.of("a:00", -1),
            Arguments.of("10:b", -1),
            Arguments.of("10:", -1),
            Arguments.of(":10", -1),
            Arguments.of(":", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("timeActualAndExpected")
    void testTime(String time, int expected) {
        assertThat(Task1.minutesToSeconds(time)).isEqualTo(expected);
    }

}
