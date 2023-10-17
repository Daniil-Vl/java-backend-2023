package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private static final Random RAND = new Random();
    private final static Logger LOGGER = LogManager.getLogger();
    private final double probabilityOfConnectionException;

    @SuppressWarnings("MagicNumber")
    public FaultyConnection() {
        this.probabilityOfConnectionException = 0.6;
    }

    public FaultyConnection(double probabilityOfConnectionException) {
        this.probabilityOfConnectionException = probabilityOfConnectionException;
    }

    @Override
    public void execute(String command) {
        if (RAND.nextDouble() <= probabilityOfConnectionException) {
            throw new ConnectionException(
                "Exception in faulty connection, when trying to execute command - " + command);
        } else {
            LOGGER.info("Execute command - " + command);
        }
    }

    @Override
    public void close() {
        LOGGER.info("Faulty connection successfully closed");
    }
}
