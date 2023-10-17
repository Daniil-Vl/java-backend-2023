package edu.hw2.task3;

public class FaultyConnectionManager implements ConnectionManager {

    private final double probabilityOfConnectionException;

    @SuppressWarnings("MagicNumber")
    public FaultyConnectionManager() {
        this.probabilityOfConnectionException = 0.6;
    }

    public FaultyConnectionManager(double probabilityOfConnectionException) {
        this.probabilityOfConnectionException = probabilityOfConnectionException;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(this.probabilityOfConnectionException);
    }
}
