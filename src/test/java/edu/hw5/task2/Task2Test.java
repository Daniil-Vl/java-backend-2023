package edu.hw5.task2;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task2Test {

    /**
     * stream of arguments of years and all friday 13
     */
    private static Stream<Arguments> years() {
        return Stream.of(
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),

            Arguments.of(
                2024,
                List.of(
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                )
            ),

            Arguments.of(
                2026,
                List.of(
                    LocalDate.of(2026, 2, 13),
                    LocalDate.of(2026, 3, 13),
                    LocalDate.of(2026, 11, 13)
                )
            )
        );
    }

    private static Stream<Arguments> datesForNearestFridays13() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(2024, 9, 20),
                LocalDate.of(2024, 12, 13)
            ),
            Arguments.of(
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 11, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("years")
    void getFridays13(int year, List<LocalDate> expectedDates) {
        assertThat(Task2.getFridays13(year)).isEqualTo(expectedDates);
    }

    @ParameterizedTest
    @MethodSource("datesForNearestFridays13")
    void findNearestFriday13(LocalDate date, LocalDate expected) {
        assertThat(Task2.findNearestFriday13(date)).isEqualTo(expected);
    }
}
