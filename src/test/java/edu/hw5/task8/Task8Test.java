package edu.hw5.task8;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task8Test {

    private static Stream<Arguments> first() {
        return Stream.of(
            Arguments.of(
                "101",
                true
            ),
            Arguments.of(
                "10111",
                true
            ),
            Arguments.of(
                "1",
                true
            ),
            Arguments.of(
                "0",
                true
            ),

            Arguments.of(
                "00",
                false
            ),
            Arguments.of(
                "aba",
                false
            )
        );
    }

    private static Stream<Arguments> second() {
        return Stream.of(
            Arguments.of(
                "000",
                true
            ),
            Arguments.of(
                "10",
                true
            ),
            Arguments.of(
                "1000",
                true
            ),
            Arguments.of(
                "100011",
                true
            ),
            Arguments.of(
                "00011",
                true
            ),

            Arguments.of(
                "ab",
                false
            ),
            Arguments.of(
                "00",
                false
            ),
            Arguments.of(
                "1",
                false
            )
        );
    }

    private static Stream<Arguments> third() {
        return Stream.of(
            Arguments.of(
                "11011101110",
                true
            ),
            Arguments.of(
                "000",
                true
            ),
            Arguments.of(
                "011101110011101110",
                true
            ),

            Arguments.of(
                "0000",
                false
            ),
            Arguments.of(
                "110111001110",
                false
            ),
            Arguments.of(
                "11011011",
                false
            )
        );
    }

    private static Stream<Arguments> fifth() {
        return Stream.of(
            Arguments.of(
                "101010",
                true
            ),
            Arguments.of(
                "",
                true
            ),
            Arguments.of(
                "101",
                true
            ),

            Arguments.of(
                "0101010",
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("first")
    void firstMatch(String string, boolean expected) {
        assertThat(Task8.firstMatch(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("second")
    void secondMatch(String string, boolean expected) {
        assertThat(Task8.secondMatch(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("third")
    void thirdMatch(String string, boolean expected) {
        assertThat(Task8.thirdMatch(string)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("fifth")
    void fifthMatch(String string, boolean expected) {
        assertThat(Task8.fifthMatch(string)).isEqualTo(expected);
    }
}
