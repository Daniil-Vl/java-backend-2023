package edu.hw5.task6;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task6Test {

    private static Stream<Arguments> strings() {
        return Stream.of(
            Arguments.of(
                "abc",
                "achfdbaabgabcaabg",
                true
            ),
            Arguments.of(
                "abcde",
                "alkjb;ljkcoijdpipepoiabiopid",
                true
            ),
            Arguments.of(
                "abcde",
                "abcde",
                true
            ),

            Arguments.of(
                "abcde",
                "abcdasdf",
                false
            ),
            Arguments.of(
                "abc",
                "acb",
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("strings")
    void isSubsequence(String first, String second, boolean expected) {
        assertThat(Task6.isSubsequence(first, second)).isEqualTo(expected);
    }
}
