package edu.hw1.task3;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {

    private static Stream<Arguments> nestableArrays() {
        return Stream.of(
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {0, 6}),
            Arguments.of(new int[] {3, 1}, new int[] {4, 0})
        );
    }

    private static Stream<Arguments> notNestableArrays() {
        return Stream.of(
            Arguments.of(new int[] {9, 9, 8}, new int[] {8, 9}),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {2, 3}),
            Arguments.of(new int[] {1, 4}, new int[] {2, 3, 5}),
            Arguments.of(new int[] {1, 4}, new int[] {2, 3, 5}),
            Arguments.of(new int[] {1, 4}, new int[] {2, 3}),
            Arguments.of(new int[] {1, 4}, new int[] {2, 3}),
            Arguments.of(new int[] {}, new int[] {2, 3}),
            Arguments.of(new int[] {1, 2, 3, 4}, new int[] {}),
            Arguments.of(new int[] {}, new int[] {})
        );
    }

    @ParameterizedTest
    @MethodSource("nestableArrays")
    void testNestableArrays(int[] inner, int[] outer) {
        assertThat(Task3.isNestable(inner, outer)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("notNestableArrays")
    void testNotNestableArrays(int[] inner, int[] outer) {
        assertThat(Task3.isNestable(inner, outer)).isFalse();
    }

}
