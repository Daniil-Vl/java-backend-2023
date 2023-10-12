package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {

    private static final double PROBABILITY_OF_FAULTY_CONNECTION = 0.0001;
    private static final Random RAND = new Random();

    /**
     * Return Stable or Faulty connection
     *
     * @return - StableConnection or with some probability FaultyConnection
     */
    @Override
    public Connection getConnection() {
        Connection result;
        if (RAND.nextDouble() < PROBABILITY_OF_FAULTY_CONNECTION) {
            result = new FaultyConnection();
        } else {
            result = new StableConnection();
        }
        return result;
    }
}
