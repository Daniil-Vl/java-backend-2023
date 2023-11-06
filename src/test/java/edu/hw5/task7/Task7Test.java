package edu.hw5.task7;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {

    private static Stream<Arguments> first() {
        return Stream.of(
            Arguments.of(
                "000",
                true
            ),
            Arguments.of(
                "11011",
                true
            ),

            Arguments.of(
                "11111",
                false
            ),
            Arguments.of(
                "00",
                false
            ),
            Arguments.of(
                "0010",
                false
            ),
            Arguments.of(
                "O00",
                false
            ),
            Arguments.of(
                "A001",
                false
            ),
            Arguments.of(
                "",
                false
            )
        );
    }

    private static Stream<Arguments> second() {
        return Stream.of(
            Arguments.of(
                "10101",
                true
            ),
            Arguments.of(
                "00100",
                true
            ),
            Arguments.of(
                "",
                true
            ),
            Arguments.of(
                "011010",
                true
            ),
            Arguments.of(
                "1",
                true
            ),

            Arguments.of(
                "1010",
                false
            ),
            Arguments.of(
                "0101",
                false
            ),
            Arguments.of(
                "a123a",
                false
            )
        );
    }

    private static Stream<Arguments> third() {
        return Stream.of(
            Arguments.of(
                "1",
                true
            ),
            Arguments.of(
                "11",
                true
            ),
            Arguments.of(
                "111",
                true
            ),
            Arguments.of(
                "010",
                true
            ),

            Arguments.of(
                "",
                false
            ),
            Arguments.of(
                "10100101",
                false
            ),
            Arguments.of(
                "aaa",
                false
            ),
            Arguments.of(
                "aaaaaa",
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("first")
    void firstMatch(String s, boolean expected) {
        assertThat(Task7.firstMatch(s)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("second")
    void secondMatch(String s, boolean expected) {
        assertThat(Task7.secondMatch(s)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("third")
    void thirdMatch(String s, boolean expected) {
        assertThat(Task7.thirdMatch(s)).isEqualTo(expected);
    }
}
