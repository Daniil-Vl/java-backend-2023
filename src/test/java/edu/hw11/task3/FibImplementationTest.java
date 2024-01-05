package edu.hw11.task3;

import edu.hw11.FibImplementation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Test for third task
 */
class FibImplementationTest {

    private static Method fibMethod;

    private static Stream<Arguments> provideArgumentsForFibonacci() {
        return Stream.of(
            arguments(1, 1L),
            arguments(2, 1L),
            arguments(3, 2L),
            arguments(4, 3L),
            arguments(5, 5L),
            arguments(6, 8L),
            arguments(7, 13L),
            arguments(8, 21L),
            arguments(9, 34L),
            arguments(10, 55L)
        );
    }

    @BeforeAll
    static void init() throws NoSuchMethodException {
        Class<?> type = FibImplementation.buildClass();
        fibMethod = type.getMethod("fib", int.class);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForFibonacci")
    void fib(int num, long expected) throws InvocationTargetException, IllegalAccessException {
        assertThat(fibMethod.invoke(null, num)).isEqualTo(expected);
    }
}
