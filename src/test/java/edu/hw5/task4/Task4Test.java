package edu.hw5.task4;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task4Test {

    private static Stream<Arguments> passwords() {
        return Stream.of(
            Arguments.of(
                "123~asd",
                true
            ),
            Arguments.of(
                "~asd",
                true
            ),
            Arguments.of(
                "asd~",
                true
            ),
            Arguments.of(
                "asd!",
                true
            ),
            Arguments.of(
                "1234@mail",
                true
            ),
            Arguments.of(
                "123#ad",
                true
            ),
            Arguments.of(
                "123$$dsad",
                true
            ),
            Arguments.of(
                "123%%dsad",
                true
            ),
            Arguments.of(
                "123^^dsad",
                true
            ),
            Arguments.of(
                "123&&dsad",
                true
            ),
            Arguments.of(
                "123||dsad",
                true
            ),
            Arguments.of(
                "123**dsad",
                true
            ),
            Arguments.of(
                "adasd~asdd!adsda||",
                true
            ),

            Arguments.of(
                "",
                false
            ),
            Arguments.of(
                "qwerty123",
                false
            ),
            Arguments.of(
                "a///\\as",
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("passwords")
    void checkPassword(String password, boolean expectedMatch) {
        assertThat(Task4.checkPassword(password)).isEqualTo(expectedMatch);
    }
}
