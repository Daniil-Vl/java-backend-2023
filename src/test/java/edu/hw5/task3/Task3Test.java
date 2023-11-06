package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {

    private static Stream<Arguments> dates() {
        return Stream.of(
            Arguments.of(
                "2020-10-10",
                Optional.of(LocalDate.of(2020, 10, 10))
            ),
            Arguments.of(
                "2020-12-2",
                Optional.of(LocalDate.of(2020, 12, 2))
            ),

            Arguments.of(
                "1/3/1976",
                Optional.of(LocalDate.of(1976, 3, 1))
            ),
            Arguments.of(
                "1/3/20",
                Optional.of(LocalDate.of(2020, 3, 1))
            ),

            Arguments.of(
                "tomorrow",
                Optional.of(LocalDate.now().plusDays(1))
            ),
            Arguments.of(
                "today",
                Optional.of(LocalDate.now())
            ),
            Arguments.of(
                "yesterday",
                Optional.of(LocalDate.now().minusDays(1))
            ),

            Arguments.of(
                "1 day ago",
                Optional.of(LocalDate.now().minusDays(1))
            ),
            Arguments.of(
                "2234 day ago",
                Optional.of(LocalDate.now().minusDays(2234))
            ),
            Arguments.of(
                "-10 days ago",
                Optional.of(LocalDate.now().plusDays(10))
            ),

            Arguments.of(
                "2020--1-1",
                Optional.empty()
            ),
            Arguments.of(
                "10 ago days",
                Optional.empty()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("dates")
    void parseDate(String string, Optional<LocalDate> expected) {
        assertThat(Task3.parseDate(string)).isEqualTo(expected);
    }

    @Test
    void testInvalidDate() {
        String date = "2020-50-1";
        assertThrows(DateTimeException.class, () -> Task3.parseDate(date));
    }
}
