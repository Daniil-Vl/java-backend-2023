package edu.hw9.task1;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double average = 0;
    private double sum = 0;
    private double count = 0;

    public void push(double[] nums) {
        DoubleSummaryStatistics tempStats = Arrays.stream(nums).summaryStatistics();

        synchronized (this) {
            min = Math.min(min, tempStats.getMin());
            max = Math.max(max, tempStats.getMax());
            count += tempStats.getCount();
            sum += tempStats.getSum();
            average = sum / count;
        }
    }

    public synchronized double getMin() {
        return min;
    }

    public synchronized double getMax() {
        return max;
    }

    public synchronized double getAverage() {
        return average;
    }

    public synchronized double getSum() {
        return sum;
    }

    public synchronized double getCount() {
        return count;
    }
}
