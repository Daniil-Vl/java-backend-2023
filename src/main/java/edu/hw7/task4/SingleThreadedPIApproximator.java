package edu.hw7.task4;

public class SingleThreadedPIApproximator extends AbstractPIApproximator {
    public SingleThreadedPIApproximator(long numberOfPoints) {
        super(numberOfPoints);
    }

    @SuppressWarnings("MagicNumber")
    @Override
    public double approximate() {
        return (double) (4 * getCircleCount(numberOfPoints)) / numberOfPoints;
    }
}
