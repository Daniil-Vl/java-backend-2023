package edu.hw3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    private static Stream<Arguments> getLists() {
        return Stream.of(
            Arguments.of(
                new String[] {"a", "bb", "a", "bb"},
                new HashMap<>(Map.of("bb", 2, "a", 2))
            ),
            Arguments.of(
                new Integer[] {1, 1, 2, 2},
                new HashMap<>(Map.of(1, 2, 2, 2))
            ),
            Arguments.of(
                new String[] {"this", "and", "that", "and"},
                new HashMap<>(Map.of("that", 1, "and", 2, "this", 1))
            ),
            Arguments.of(
                new String[] {"код", "код", "код", "bug"},
                new HashMap<>(Map.of("код", 3, "bug", 1))
            ),

            Arguments.of(
                new String[] {},
                new HashMap<>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getLists")
    void testFreqDict(Object[] array, HashMap<Object, Integer> expectedFreqDict) {
        assertThat(Task3.freqDict(array)).isEqualTo(expectedFreqDict);
    }
}
