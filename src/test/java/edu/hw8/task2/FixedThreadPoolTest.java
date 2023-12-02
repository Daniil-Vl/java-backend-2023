package edu.hw8.task2;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {

    private static final int number = 10;
    private static final List<Long> expectedFibonacciSequence = List.of(1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L);

    @Test()
    void parallelFibonacciCalculation() throws Exception {
        try (ThreadPool threadPool = FixedThreadPool.create(4)) {
            threadPool.start();

            long[] fibonacciSequenceNumbers = new long[number];
            for (int i = 0; i < number; i++) {
                int finalI = i;
                threadPool.execute(() -> fibonacciSequenceNumbers[finalI] = getFib(finalI));
            }

            Thread.sleep(1000);

            List<Long> actualFibonacciSequence = Arrays.stream(fibonacciSequenceNumbers).boxed().toList();

            assertThat(actualFibonacciSequence).isEqualTo(expectedFibonacciSequence);
        }
    }

    /**
     * Recursive fibonacci calculator
     */
    private long getFib(int n) {
        if (n < 2) {
            return 1L;
        }
        return getFib(n - 1) + getFib(n - 2);
    }
}
