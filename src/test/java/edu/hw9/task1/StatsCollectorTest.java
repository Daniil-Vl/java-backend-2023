package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StatsCollectorTest {
    @Test
    void givenOneArrayOfDoubles_whenCalcStats_thenReturnStats() {
        double[] nums = new Random().doubles(100, Double.MIN_VALUE, Double.MAX_VALUE).toArray();
        int expectedCount = nums.length;
        double expectedSum = Arrays.stream(nums).sum();
        double expectedAverage = expectedSum / expectedCount;
        double expectedMin = Arrays.stream(nums).min().orElseThrow();
        double expectedMax = Arrays.stream(nums).max().orElseThrow();

        StatsCollector collector = new StatsCollector();
        CompletableFuture<Void> task = collector.push(nums);
        task.join();

        assertThat(collector.getCount()).isEqualTo(expectedCount);
        assertThat(collector.getSum()).isEqualTo(expectedSum);
        assertThat(collector.getAverage()).isEqualTo(expectedAverage);
        assertThat(collector.getMin()).isEqualTo(expectedMin);
        assertThat(collector.getMax()).isEqualTo(expectedMax);
    }

    @Test
    void givenBatchOfArrays_whenMultipleThreadsPushesDoubles_thenReturnStats() {
        Random random = new Random();
        StatsCollector collector = new StatsCollector();

        List<List<Double>> batches = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            batches.add(
                random.doubles(random.nextInt(100), Double.MIN_VALUE, Double.MAX_VALUE).boxed().toList()
            );
        }

        // Invoke collector push in many threads
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        try (
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        ) {
            for (List<Double> batch : batches) {
                tasks.add(
                    CompletableFuture.runAsync(() -> {
                        collector.push(batch.stream().mapToDouble(Double::doubleValue).toArray()).join();
                    }, executorService)
                );
            }
        }

        // Calc statistics in main thread
        DoubleSummaryStatistics expectedStatistics =
            batches.stream().flatMap(Collection::stream).mapToDouble(Double::doubleValue).summaryStatistics();

        // Block until all threads are finish statistics collection
        CompletableFuture.allOf(tasks.stream().filter(Objects::nonNull).toArray(CompletableFuture[]::new));

        assertThat(collector.getCount()).isEqualTo(expectedStatistics.getCount());
        assertThat(collector.getSum()).isEqualTo(expectedStatistics.getSum());
        assertThat(collector.getAverage()).isEqualTo(expectedStatistics.getAverage());
        assertThat(collector.getMin()).isEqualTo(expectedStatistics.getMin());
        assertThat(collector.getMax()).isEqualTo(expectedStatistics.getMax());
    }
}
