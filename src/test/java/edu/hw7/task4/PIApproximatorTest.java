package edu.hw7.task4;

import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.data.Offset;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PIApproximatorTest {
    private static final Logger LOGGER = LogManager.getLogger();

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            arguments(10_000_000),
            arguments(100_000_000),
            arguments(1_000_000_000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void testApproximation(long numberOfPoints) {
        SingleThreadedPIApproximator singleThreadedPIApproximator = new SingleThreadedPIApproximator(numberOfPoints);
        MultiThreadedPIApproximator twoThreadedPIApproximator = new MultiThreadedPIApproximator(numberOfPoints, 2);
        MultiThreadedPIApproximator fourThreadedPIApproximator = new MultiThreadedPIApproximator(numberOfPoints, 4);
        MultiThreadedPIApproximator eightThreadedPIApproximator = new MultiThreadedPIApproximator(numberOfPoints, 8);
        MultiThreadedPIApproximator sixteenThreadedPIApproximator = new MultiThreadedPIApproximator(numberOfPoints, 16);

        LOGGER.info("Approximation with %d points\n".formatted(numberOfPoints));

        double singleThreadedPI = getApproximationResults(singleThreadedPIApproximator);
        double twoThreadedPI = getApproximationResults(twoThreadedPIApproximator);
        double fourThreadedPI = getApproximationResults(fourThreadedPIApproximator);
        double eightThreadedPI = getApproximationResults(eightThreadedPIApproximator);
        double sixteenThreadedPI = getApproximationResults(sixteenThreadedPIApproximator);

        assertThat(singleThreadedPI).isCloseTo(Math.PI, Offset.offset(0.01));
        assertThat(twoThreadedPI).isCloseTo(Math.PI, Offset.offset(0.01));
        assertThat(fourThreadedPI).isCloseTo(Math.PI, Offset.offset(0.01));
        assertThat(eightThreadedPI).isCloseTo(Math.PI, Offset.offset(0.01));
        assertThat(sixteenThreadedPI).isCloseTo(Math.PI, Offset.offset(0.01));
    }

    private double getApproximationResults(AbstractPIApproximator approximator) {
        long startTIme = System.nanoTime();
        double resultPI = approximator.approximate();
        long endTime = System.nanoTime();

        if (approximator instanceof MultiThreadedPIApproximator) {
            LOGGER.info("Number of threads - " + ((MultiThreadedPIApproximator) approximator).getNumberOfThreads());
        } else {
            LOGGER.info("Number of threads - " + 1);
        }
        LOGGER.info("Result PI approximation - " + resultPI);
        LOGGER.info("Error rate - " + Math.abs(Math.PI - resultPI));
        LOGGER.info("Approximation time - %d milliseconds\n".formatted((endTime - startTIme) / 1000000));

        return resultPI;
    }

}
