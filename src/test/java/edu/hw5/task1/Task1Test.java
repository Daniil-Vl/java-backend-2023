package edu.hw5.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    private static Stream<Arguments> data() {
        return Stream.of(
            // Test case from task
            Arguments.of(
                List.of(
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                ),
                "3ч 40м"
            ),

            Arguments.of(
                List.of(
                    "2022-03-12, 12:00 - 2022-03-13, 08:00",
                    "2022-01-01, 00:00 - 2022-01-01, 18:00"
                ),
                "19ч 0м"
            ),

            // Test case for average time, more than 24 hours
            Arguments.of(
                List.of(
                    "2022-03-12, 00:00 - 2022-03-13, 06:00"
                ),
                "30ч 0м"
            ),

            // Test case for empty list
            Arguments.of(
                List.of(),
                "0ч 0м"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void testGetAverageDuration(List<String> sessions, String expectedAverageTime) {
        assertThat(Task1.getAverageDuration(sessions)).isEqualTo(expectedAverageTime);
    }
}
