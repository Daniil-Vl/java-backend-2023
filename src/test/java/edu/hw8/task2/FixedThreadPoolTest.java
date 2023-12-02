package edu.hw8.task2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class FixedThreadPoolTest {

    private static final int number = 30;

    private static final List<Long> expectedFibonacciSequence = List.of(
        1L,
        1L,
        2L,
        3L,
        5L,
        8L,
        13L,
        21L,
        34L,
        55L,
        89L,
        144L,
        233L,
        377L,
        610L,
        987L,
        1597L,
        2584L,
        4181L,
        6765L,
        10946L,
        17711L,
        28657L,
        46368L,
        75025L,
        121393L,
        196418L,
        317811L,
        514229L,
        832040L
    );

    @Test
    void singleThreadFibonacciSequenceCalculations() throws InterruptedException {
        long[] fibNums = new long[number];
        fibNums[0] = 1L;
        fibNums[1] = 1L;

        for (int i = 2; i < number; i++) {
            fibNums[i] = fibNums[i - 1] + fibNums[i - 2];
        }

        // Sleep before all calculations end
        Thread.sleep(2000);

        List<Long> result = new ArrayList<>();
        for (long fibNum : fibNums) {
            result.add(fibNum);
        }

        assertThat(result).isEqualTo(expectedFibonacciSequence);
    }

    @Test
    void parallelFibonacciSequenceCalculations()
        throws Exception {
        long[] fibNums = new long[number];
        int k = 8;
        fibNums[0] = 1L;
        fibNums[1] = 1L;
        ThreadPool threadPool = FixedThreadPool.create(k);
        threadPool.start();

        // Parallel computing scheme:
        // 1) Compute first 8 numbers synchronously
        // 2) After that start calculations in different thread using this formula
        // F(n + k) = F(n + 1) * F(K) + F(n) * F(k - 1)
        // Stackoverflow answer - https://stackoverflow.com/a/16464865
        for (int i = 0; i < number; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                if (finalI < k) {
                    fibNums[finalI] = fib(finalI);
                } else {
                    int n = finalI - k;
                    while (!canContinue(fibNums)) {
                    }
                    fibNums[n + k] = fibNums[n + 1] * fibNums[k - 1] +
                        fibNums[n] * fibNums[k - 2];
                }
            });
        }

        // Sleep before all calculations end
        Thread.sleep(2000);

        List<Long> result = new ArrayList<>();
        for (long fibNum : fibNums) {
            result.add(fibNum);
        }

        assertThat(result).isEqualTo(expectedFibonacciSequence);

        threadPool.close();
    }

    /**
     * Checks, that first 8 fibonacci numbers already calculated, so we can start to parallelize calculations
     */
    private boolean canContinue(long[] nums) {
        for (int i = 0; i < 8; i++) {
            if (nums[i] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recursive fibonacci calculator
     */
    private long fib(int n) {
        if (n < 2) {
            return 1L;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
