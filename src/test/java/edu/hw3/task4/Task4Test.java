package edu.hw3.task4;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task4Test {

    private static Stream<Arguments> arabicNumbers() {
        return Stream.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(101, "CI"),
            Arguments.of(101, "CI"),
            Arguments.of(549, "DXLIX"),
            Arguments.of(3999, "MMMCMXCIX")
        );
    }

    @ParameterizedTest
    @MethodSource("arabicNumbers")
    void testConversionToRoman(int num, String expected) {
        assertThat(Task4.convertToRoman(num)).isEqualTo(expected);
    }

    @Test
    void testIllegalNumberConversion() {
        int num = 4000;
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(num));
    }
}
