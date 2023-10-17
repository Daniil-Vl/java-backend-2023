package edu.hw2.task3;

public class StableConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return new StableConnection();
    }
}
