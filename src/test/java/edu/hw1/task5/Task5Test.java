package edu.hw1.task5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5Test {

    private static Stream<Arguments> palindromesDescendant() {
        return Stream.of(
            Arguments.of(11211230),
            Arguments.of(13001120),
            Arguments.of(23336014),
            Arguments.of(11),
            Arguments.of(1221),
            Arguments.of(112233),
            Arguments.of(5656)
        );
    }

    private static Stream<Arguments> notPalindromesDescendant() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(1234)
        );
    }

    @ParameterizedTest
    @MethodSource("palindromesDescendant")
    void testPalindromes(long num) {
        assertThat(Task5.isPalindromeDescendant(num)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("notPalindromesDescendant")
    void testNotPalindromes(long num) {
        assertThat(Task5.isPalindromeDescendant(num)).isFalse();
    }

}
