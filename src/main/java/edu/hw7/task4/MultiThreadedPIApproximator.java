package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadedPIApproximator extends AbstractPIApproximator {
    private final long numberOfThreads;

    public MultiThreadedPIApproximator(long numberOfPoints, long numberOfThreads) {
        super(numberOfPoints);
        this.numberOfThreads = numberOfThreads;
    }

    public long getNumberOfThreads() {
        return numberOfThreads;
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public double approximate() {
        Thread[] threads = new Thread[(int) numberOfThreads];
        List<Long> circlePoints = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> circlePoints.add(getCircleCount(numberOfPoints / numberOfThreads)));
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return (double) (4 * circlePoints.stream().reduce(0L, Long::sum)) / numberOfPoints;
    }
}
