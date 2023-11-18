package edu.hw5.task5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task5Test {

    private static Stream<Arguments> numbers() {
        return Stream.of(
            Arguments.of(
                "А123ВЕ777",
                true
            ),
            Arguments.of(
                "О777ОО177",
                true
            ),
            Arguments.of(
                "О000ОО000",
                true
            ),

            Arguments.of(
                "123АВЕ777",
                false
            ),
            Arguments.of(
                "А123ВГ77",
                false
            ),
            Arguments.of(
                "А123ВЕ7777",
                false
            ),
            Arguments.of(
                "а12фи123",
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("numbers")
    void checkNumber(String number, boolean expectedMatch) {
        assertThat(Task5.checkNumber(number)).isEqualTo(expectedMatch);
    }
}
