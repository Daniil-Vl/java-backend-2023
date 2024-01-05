package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractPIApproximator {
    protected final long numberOfPoints;
    protected final int radius = 1;

    public AbstractPIApproximator(long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    protected long getCircleCount(long numberOfPoints) {
        Random random = ThreadLocalRandom.current();
        long circleCount = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            double x = random.nextDouble(-1.0, 1.0);
            double y = random.nextDouble(-1.0, 1.0);

            if (x * x + y * y <= radius) {
                circleCount++;
            }
        }

        return circleCount;
    }

    public abstract double approximate();

}
